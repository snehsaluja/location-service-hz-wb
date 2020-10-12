# location-service-hz-postgres
PoC Location Service on Hazelcast with PostgreSQL

Steps to run location-service-hz-postgres:

1. Download and Install Apache-ignite 2.8.1 from : https://ignite.apache.org/download.cgi
2. Go to the bin folder of Ignite installation directory `{ignite}/bin` from the command shell and start an Ignite-node: `./ignite.sh` 
3. Download and Install PostgreSQL from : https://www.postgresql.org
4. Open PGAdmin create a new DB name **locationservice**. Set the password as **root** (for PoC purpose only).
5. Flyway script will take care of the data input.
6. Start the service.
7. You can execute the below endpoints in curl/Postman:
* GET http://localhost:9000/api/countries?population=800000
* GET http://localhost:9000/api/cities?population=800000
* GET http://localhost:9000/api/cities/1024 (1024 is the city ID for Mumbai)