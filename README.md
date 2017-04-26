### Learn what you can do with Spring Boot

Spring Boot offers a fast way to build applications. It looks at your classpath and at beans you have configured, makes reasonable assumptions about what you're missing, and adds it. With Spring Boot you can focus more on business features and less on infrastructure.

For example:

- Got Spring MVC? There are several specific beans you almost always need, and Spring Boot adds them automatically. A Spring MVC app also needs a servlet container, so Spring Boot automatically configures embedded Tomcat.
- Got Jetty? If so, you probably do NOT want Tomcat, but instead embedded Jetty. Spring Boot handles that for you.
- Got Thymeleaf? There are a few beans that must always be added to your application context; Spring Boot adds them for you.

These are just a few examples of the automatic configuration Spring Boot provides. At the same time, Spring Boot doesn't get in your way. For example, if Thymeleaf is on your path, Spring Boot adds a `SpringTemplateEngine` to your application context automatically. But if you define your own `SpringTemplateEngine` with your own settings, then Spring Boot won't add one. This leaves you in control with little effort on your part.

NOTE: Spring Boot doesn't generate code or make edits to your files. Instead, when you start up your application, Spring Boot dynamically wires up beans and settings and applies them to your application context.

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
```
where `******` is my password.

### Links:
<http://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion>