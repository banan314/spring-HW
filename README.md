### Frontend
To run frontend, go to `frontend/src/main/attendance` and then follow the instructions in [README](./frontend/src/main/attendance/README.md). That is, 
```
cd frontend/src/main/attendance
npm start
```

### Learn what you can do with Spring Boot

Spring Boot offers a fast way to build applications. It looks at your classpath and at beans you have configured, makes reasonable assumptions about what you're missing, and adds it. With Spring Boot you can focus more on business features and less on infrastructure.

### Create a simple web application
Now you can create a web controller for a simple web application.

`src/main/java/hello/HelloController.java`

If you are using Maven, execute:

```
$ mvn package && java -jar target/{project_id}-0.1.0.jar
$ curl localhost:8080
Greetings from Spring Boot!
```

## Integration with database
To integrate your application with PostgreSQL database, you have to supply 
configuration file to Spring framework and put it somewhere in `/src/main/resources` probably.

It doesn't have to be YAML, but I suggest this format and used it in follwing way (*application.yml*):
```yaml
#
# [ Database Configuration Section ]
#

spring:
  datasource:
    #platform: postgres
    url: jdbc:postgresql://localhost:5432/mydb
    username: postgres
    password: *********
    driver-class-name: org.postgresql.Driver
    # Keep the connection alive if idle for a long time (needed in production)
    test-while-idle: true
    validation-query: SELECT 1
    # ===============================
    # = JPA / HIBERNATE
    # ===============================
    # Show or not log for each sql query
  jpa:
    hibernate:
      ddl-auto: create
      naming:
        implicit-strategy: org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyHbmImpl
        physical-strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

hw.spring.jwt.secret: ########       
```
where `******` is my password. You also have to provide secret string for JSON Web Tokens (`######`).

### Links:
<http://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion>