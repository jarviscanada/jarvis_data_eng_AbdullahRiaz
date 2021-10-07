--Query to group hosts by hardware info
SELECT cpu_number,
       host_id,
       total_mem,
       row_number() over(partition by cpu_number order by total_mem desc)
FROM host_info
INNER JOIN host_usage on host_info.id=host_usage.host_id;

--Function to round timestamps to 5 minute intervals
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

--Query to find average used memory as a percentage
SELECT host_id,
       round5(hu.timestamp) as rts,
       avg(hi.total_mem-memory_free)/avg(hi.total_mem)*100 as avg_used_memory_percentage
FROM host_info hi
INNER JOIN host_usage hu on hi.id=hu.host_id
GROUP BY host_id, rts
ORDER BY rts;

--Query to detect host failure
SELECT host_id,
       round5(timestamp) as rts,
       count(host_id) as num_data_points
FROM host_usage
GROUP BY host_id, rts
HAVING count(host_id)<3
ORDER BY rts;
