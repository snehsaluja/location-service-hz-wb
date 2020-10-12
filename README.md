# location-service-hz-wb
PoC on **Write-Behind Cache** using Hazelcast. PostgreSQL is being used as a DB.

Steps to run location-service-hz-wb:

1. Download and Install PostgreSQL from : https://www.postgresql.org
2. Open PGAdmin create a new DB name **locationservice**. Set the password as **root** (for PoC purpose only).
3. Flyway script will take care of the data input.
4. Start the service.