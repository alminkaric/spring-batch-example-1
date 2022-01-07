# Spring Boot with Spring Batch Example 1
This is a small CSV parser implemented with Java 8 and Spring Boot version 2.3.4. It parses csv with the following data:

*users.csv*
```
emp_id,fist_name,last_name,dep_id,salary_date,salary
10,Joe,Doe,1001,2021-11-30,5000.00
12,John,Smith,9999,2021-10-31,4500.00
13,Mike,Smith,1002,2021-07-31,5100.50
10,Joe,Doe,1001,2021-08-31,5200.00
13,Mike,Smith,1002,2021-09-30,5400.00
11,Bill,Bones,1003,2021-07-31,4450.50
13,Mike,Smith,1002,2021-10-31,5555.00
12,John,Smith,1003,2021-07-31,4350.00
11,Bill,Bones,1003,2021-10-31,4500.00
12,John,Smith,1003,2021-08-31,4800.00
10,Joe,Doe,1001,2021-10-31,5400.00
12,John,Smith,1003,2021-09-30,4600.00
13,Mike,Smith,1002,2021-08-31,5900.00
12,John,Smith,1003,2021-11-30,4400.00
11,Bill,Bones,1003,2021-08-31,4800,00
10,Joe,Doe,1001,2021-07-31,
11,Bill,Bones,1003,2021-09-30,4300.00
13,Mike,Smith,1002,2021/11/31,4800.00
10,Joe,Doe,1001,2021-09-30,5300.00
11,Bill,Bones,1003,2021-11-30,3800.00
```

## How to run the application
- clone the project into desired folder
- run `mvn clean install` in the terminal (java 8 and maven are required for that)
- run `mvn spring-boot:run -Dspring-boot.run.arguments="--csvPath=users.csv"` to run  the Spring application with the path to the csv file (application will automatically parse the `users.csv` file and save the records in the h2 database)
- open `http://localhost:8081/h2-console` - H2 Console for querying the in-memory tables.

### H2 Config
- `testdb` - Database.
- `sa` - User
- `password` - Password.

---
# Original task
### Objective
Build small Spring Boot application to parse CSV input file,
validate each line, print error lines and average salary for each employee to the console
and store average salary for each employee into DB.

### Requirements
* Java version 8 or higher
* Application should be executable JAR file with CSV filename input as parameter
* A few jUnit tests
* Use any in-memory DB, e.g. H2

### Validation
* the only valid date format is YYYY-MM-DD
* salary is float
* all values are mandatory, if value is missing or is not in correct format then record is ignored and reported in output as error
* there are only 3 departments in the company (1001 - Sales, 1002 - IT, 1003 - HR), any other department is invalid and such record should be ignored and reported in output as error

### CSV input file example
Each line contains following:
* `emp_id` - employee id
* `first_name` - first name
* `last_name` - last name
* `dep_id` - department id
* `salary_date` - last day in month for which the salary applies, e.g. `2021-11-30` means that salary is for November 2021
* `salary` - salary amount in EUR
```
emp_id,fist_name,last_name,dep_id,salary_date,salary
10,Joe,Doe,1001,2021-11-30,5000.00
12,John,Smith,9999,2021-10-31,4500.00
13,Mike,Smith,1002,2021-07-31,5100.50
10,Joe,Doe,1001,2021-08-31,5200.00
13,Mike,Smith,1002,2021-09-30,5400.00
11,Bill,Bones,1003,2021-07-31,4450.50
13,Mike,Smith,1002,2021-10-31,5555.00
12,John,Smith,1003,2021-07-31,4350.00
11,Bill,Bones,1003,2021-10-31,4500.00
12,John,Smith,1003,2021-08-31,4800.00
10,Joe,Doe,1001,2021-10-31,5400.00
12,John,Smith,1003,2021-09-30,4600.00
13,Mike,Smith,1002,2021-08-31,5900.00
12,John,Smith,1003,2021-11-30,4400.00
11,Bill,Bones,1003,2021-08-31,4800,00
10,Joe,Doe,1001,2021-07-31,
11,Bill,Bones,1003,2021-09-30,4300.00
13,Mike,Smith,1002,2021/11/31,4800.00
10,Joe,Doe,1001,2021-09-30,5300.00
11,Bill,Bones,1003,2021-11-30,3800.00
```

### Console output
This is how the output from CSV processing should look like.
```
Number of errors: 4
Lines with error:
12,John,Smith,9999,2021-10-31,4500.00
13,Mike,Smith,1002,2021/11/31,4800.00
11,Bill,Bones,1003,2021-08-31,4800,00
10,Joe,Doe,1001,2021-07-31,

Average salary per employee:
Joe Doe: 1111.1 EUR
Bill Bones: 2222.2 EUR
John Smith: 3333.3 EUR
Mike Smith: 4444.4 EUR
```

### Result
Source code with instructions of how to build and run the application.