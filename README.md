# Spring Boot with Spring Batch Example 1

## Run with terminal
- `mvn spring-boot:run -Dspring-boot.run.arguments="--csvPath=users.csv"` runs the Spring application with the path to the csv file

## Load CSV to DB
- `http://localhost:8081/load` - Trigger point for Spring Batch
- `http://localhost:8081/h2-console` - H2 Console for querying the in-memory tables.

## H2 Config
- `testdb` - Database.
- `sa` - User
- `password` - Password.
