#! /bin/sh

#CLI arguments
cmd=$1
db_username=$2
db_password=$3

#Start docker
sudo systemctl status docker || systemctl start docker

#check container status
docker container inspect jrvs-psql
container_status=$?

#User switch case
case $cmd in
  #Create container operation
  create)

  #Check if container already exists
  if [ $container_status -eq 0 ]; then
	echo 'Container already exists'
	exit 1
  fi

  #check # of CLI arguments
  if [ $# -ne 3 ]; then
	echo 'Create requires username and password'
	exit 1
  fi

  #Create container
	docker volume create pgdata
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
	exit $?
	;;

  #start|stop operation
  start|stop)

  if [ $container_status -ne 0 ]; then
	echo 'Container has not been created'
	exit 1
  fi

  #Start or stop the container
  docker container $cmd jrvs-psql
  exit $?
  ;;

  #If anything other than "start", "stop" or "create"
  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac
