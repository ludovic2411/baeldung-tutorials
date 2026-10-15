# Baeldung tutorials projects
Project hosting tutorials about Spring based on Baeldung's tutorials

## Setup

 Using environment variables for secrets

To export environment variables and cleaning them up after use using Powershell files.
However, you may be prevented from running scripts files. You can disable prevents methods with the following command
in Powershell:

```shell
Set-ExecutionPolicy RemoteSigned -Scope Process
```

Connect to postgres in container:

````dockerfile
docker exec -it postgres-db psql -U postgres -d testdb
````

Show tables

````postgresql
\dt
````

describe table

````postgresql
\d+ <table>
````

Apply database migration with Maven and Flyway

````
mvn clean install
````

Run the application. Db service will be setup by spring-docker-compose dependency, no need to do a docker compose up

````
mvn spring-boot:run
````

You can also run the app as it should on CI Github action integration:

````
docker compose -f compose.ci.yaml up
````

Container may stay in use, find the right network:

````
docker network ls
````

Disconnect the network and the spring-boot service:

````
docker network disconnect baeldung-tutorials_default spring-boot-app
````

Before doing:
````
docker compose down
````