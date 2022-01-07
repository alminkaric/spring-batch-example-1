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

## TODO:
- write some junit tests (for validators and dbwriter)
- add javadoc
- improve the code (refactor, add final and static keywords, add more comments)

## How to run the application
- clone the project into desired folder
- run `mvn clean install` in the terminal (java 8 and maven are required for that)
- run `mvn spring-boot:run -Dspring-boot.run.arguments="--csvPath=users.csv"` to run  the Spring application with the path to the csv file (application will automatically parse the `users.csv` file and save the records in the h2 database)
- open `http://localhost:8081/h2-console` - H2 Console for querying the in-memory tables.

### H2 Config
- `testdb` - Database.
- `sa` - User
- `password` - Password.

### Example of the output in the console
```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/C:/Users/akaric/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/C:/m2/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
07:10:54.439 [main] INFO com.techprimers.springbatchexample1.SpringBatchExample1Application - Going to run spring application with arguments: --csvPath=users.csv
07:10:54.718 [restartedMain] INFO com.techprimers.springbatchexample1.SpringBatchExample1Application - Going to run spring application with arguments: --csvPath=users.csv

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.3.4.RELEASE)

2022-01-07 07:10:55.225  INFO 29916 --- [  restartedMain] c.t.s.SpringBatchExample1Application     : Starting SpringBatchExample1Application on wfnotebook80 with PID 29916 (C:\Users\akaric\Documents\apps\spring-batch-example-1\target\classes started by akaric in C:\Users\akaric\Documents\apps\spring-batch-example-1)
2022-01-07 07:10:55.227  INFO 29916 --- [  restartedMain] c.t.s.SpringBatchExample1Application     : No active profile set, falling back to default profiles: default
2022-01-07 07:10:55.302  INFO 29916 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2022-01-07 07:10:55.303  INFO 29916 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2022-01-07 07:10:55.303  INFO 29916 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2022-01-07 07:10:55.303  INFO 29916 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2022-01-07 07:10:56.303  INFO 29916 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2022-01-07 07:10:56.304  INFO 29916 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFERRED mode.
2022-01-07 07:10:56.394  INFO 29916 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 80ms. Found 1 JPA repository interfaces.
2022-01-07 07:10:57.315  INFO 29916 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8081 (http)
2022-01-07 07:10:57.330  INFO 29916 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-01-07 07:10:57.331  INFO 29916 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.38]
2022-01-07 07:10:57.541  INFO 29916 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-01-07 07:10:57.541  INFO 29916 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2238 ms
2022-01-07 07:10:57.606  INFO 29916 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2022-01-07 07:10:57.837  INFO 29916 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2022-01-07 07:10:57.848  INFO 29916 --- [  restartedMain] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
2022-01-07 07:10:57.977  INFO 29916 --- [  restartedMain] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2022-01-07 07:10:58.047  INFO 29916 --- [         task-1] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2022-01-07 07:10:58.121  INFO 29916 --- [         task-1] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.4.21.Final
2022-01-07 07:10:58.280  WARN 29916 --- [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2022-01-07 07:10:58.326  INFO 29916 --- [         task-1] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.0.Final}
2022-01-07 07:10:58.504  INFO 29916 --- [         task-1] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
2022-01-07 07:10:58.743  WARN 29916 --- [  restartedMain] o.s.b.a.batch.JpaBatchConfigurer         : JPA does not support custom isolation levels, so locks may not be taken when launching Jobs
2022-01-07 07:10:58.745  INFO 29916 --- [  restartedMain] o.s.b.c.r.s.JobRepositoryFactoryBean     : No database type set, using meta data indicating: H2
2022-01-07 07:10:58.777  INFO 29916 --- [  restartedMain] o.s.b.c.l.support.SimpleJobLauncher      : No TaskExecutor has been set, defaulting to synchronous executor.
2022-01-07 07:10:58.881  INFO 29916 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2022-01-07 07:10:58.957  INFO 29916 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8081 (http) with context path ''
2022-01-07 07:10:58.959  INFO 29916 --- [  restartedMain] DeferredRepositoryInitializationListener : Triggering deferred initialization of Spring Data repositories…
2022-01-07 07:10:59.571  INFO 29916 --- [         task-1] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2022-01-07 07:10:59.582  INFO 29916 --- [         task-1] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2022-01-07 07:10:59.796  INFO 29916 --- [  restartedMain] DeferredRepositoryInitializationListener : Spring Data repositories initialized!
2022-01-07 07:10:59.807  INFO 29916 --- [  restartedMain] c.t.s.SpringBatchExample1Application     : Started SpringBatchExample1Application in 5.071 seconds (JVM running for 8.191)
2022-01-07 07:10:59.876  INFO 29916 --- [  restartedMain] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=ETL-Load]] launched with the following parameters: [{time=1641535859811, dest=users.csv}]
2022-01-07 07:10:59.924  INFO 29916 --- [  restartedMain] o.s.batch.core.job.SimpleStepHandler     : Executing step: [ETL-file-load]
LineDTO [empId=12, firstName=John, lastName=Smith, deptId=9999, salaryDate=2021-10-31, salary=4500.00, rawLine=12,John,Smith,9999,2021-10-31,4500.00] has been filtered because it is invalid
LineDTO [empId=11, firstName=Bill, lastName=Bones, deptId=1003, salaryDate=2021-08-31, salary=4800, rawLine=11,Bill,Bones,1003,2021-08-31,4800,00] has been filtered because it is invalid
LineDTO [empId=10, firstName=Joe, lastName=Doe, deptId=1001, salaryDate=2021-07-31, salary=, rawLine=10,Joe,Doe,1001,2021-07-31,] has been filtered because it is invalid
LineDTO [empId=13, firstName=Mike, lastName=Smith, deptId=1002, salaryDate=2021/11/31, salary=4800.00, rawLine=13,Mike,Smith,1002,2021/11/31,4800.00] has been filtered because it is invalid
### number of errors 4
### lines with errors: 
### 12,John,Smith,9999,2021-10-31,4500.00
### 11,Bill,Bones,1003,2021-08-31,4800,00
### 10,Joe,Doe,1001,2021-07-31,
### 13,Mike,Smith,1002,2021/11/31,4800.00
### Average salary per employee:
### Bill Bones: 4262.625EUR
### Joe Doe: 5225.0EUR
### John Smith: 4537.5EUR
### Mike Smith: 5488.875EUR
2022-01-07 07:11:00.167  INFO 29916 --- [  restartedMain] o.s.batch.core.step.AbstractStep         : Step: [ETL-file-load] executed in 243ms
2022-01-07 07:11:00.173  INFO 29916 --- [  restartedMain] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=ETL-Load]] completed with the following parameters: [{time=1641535859811, dest=users.csv}] and the following status: [COMPLETED] in 272ms
JobExecution: COMPLETED
Batch is Running...
2022-01-07 07:11:00.174  INFO 29916 --- [  restartedMain] c.t.s.SpringBatchExample1Application     : Parsing is finished with status=COMPLETED
```

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