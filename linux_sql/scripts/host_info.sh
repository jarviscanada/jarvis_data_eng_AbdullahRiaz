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

#Assign hostname and lscpu value to respective variables
hostname=$(hostname -f)
lscpu_out=$(lscpu)

#Retrieve hardware specification variables
cpu_number=$(echo "$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" | egrep "^Architecture" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep "^Model name:" | awk '{print $3,$4,$5,$6,$7}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out" | egrep "^L2 cache:" | awk '{print $3}' | sed 's/K//' | xargs)
total_mem=$(cat /proc/meminfo | egrep "^MemTotal:" | awk '{print $2}' | xargs)

#Current time in UTC format
timestamp=$(vmstat -t | awk '{print $18, $19}' | tail -n1 | xargs)

#PSQL command: Inserts hardware specifications data into host_info table through an SQL command (INSERT INTO...)
insert_stmt="INSERT INTO host_info(
				 hostname,
				 cpu_number,
				 cpu_architecture,
				 cpu_model,
				 cpu_mhz,
				 L2_cache,
				 total_mem,
				 timestamp
				 )
	      VALUES(
				'$hostname',
				 $cpu_number,
				'$cpu_architecture',
				'$cpu_model',
				 $cpu_mhz,
				 $l2_cache,
				 $total_mem,
				'$timestamp'
				);" 

#Assign the associated value to the environmental variable for PostgresQL
export PGPASSWORD=$psql_password

#Insert data into a database via psql client
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?

