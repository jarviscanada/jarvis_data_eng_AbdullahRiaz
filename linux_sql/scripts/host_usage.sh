#! /bin/bash

#Setup arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Check # of args to validate
if [ $# -ne 5 ]; then
  echo 'Invalid Arguments'
  exit 1
fi

#Assign hostname and vmstat value in MB to respective variables
vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)

#Retrieve usage variables
memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_mb" | awk '{print $15}' | tail -n1 | xargs)
cpu_kernel=$(echo "$vmstat_mb" | awk '{print $14}' | tail -n1 | xargs)
disk_io=$(vmstat -d | awk '{print $10}' | tail -n1 | xargs)
disk_available=$(df -BM | awk '{print $4}' | head -n6 | tail -n1 | sed 's/M//' | xargs)

#Current time in UTC format
timestamp=$(vmstat -t | awk '{print $18, $19}' | tail -n1 | xargs)

#Subquery to find matching id in host_info table
host_id="(SELECT id FROM host_info
          WHERE hostname='$hostname')";

#PSQL command: Inserts hardware usage data into host_info table through an SQL command (INSERT INTO...)
insert_stmt="INSERT INTO host_usage(
			 timestamp,
			 host_id,
			 memory_free,
			 cpu_idle,
			 cpu_kernel,
			 disk_io,
			 disk_available
		        )
 	      VALUES(
			'$timestamp',
			 $host_id,
			 $memory_free,
			'$cpu_idle',
			'$cpu_kernel',
			 $disk_io,
			 $disk_available
			);"

#Assign the associated value to the environmental variable for PostgresQL
export PGPASSWORD=$psql_password 

#Insert data into a database via psql client
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
