### Hibernate & JPA with Spring Boot

### World before JPA

1. Spring JDBC to JPA
2. JPA with Hibernate :
    1. Entities & Annotations
    2. @MantToOne, @ManyToMany, @OneToOne
    3. Inheritance Hierarchies
    4. Entity Manager & Persistence Context
    5. JPQL, Native and Criteria Queries
    6. Transaction Management & Performance
    7. Caching with First and Second Level Caches
3. Basics of Spring, SpringBoot and JUnit

> ### JPA : Java Persistence API

* In JDBC we used to write Query for everything.
* In JPA, we define our Java Classes (Entities) and how to map them to SQL Tables (Relations)
* JPA would create our queries for us (focus more on Java instead of SQL)
* Relations between Objects need to be defined
* Query by JPQL using entities/CRITERIA QUERY using java /native queries using sql

### Spring Boot Quick Intro

Goals:
1. Enable building production ready applications quickly
2. Provide common non-functional features
1. Embedded Servers, metrics, health checks, external configurations

ZERO Code generations
Neither app server nor web server => works with embedded servers

Features:
1. Quick Starter Projects using Auto Configuration
1. Web (Spring MVC, Spring Core, Spring Validation, Logging Framework)
2. JPA (Spring Boot Starter JPA)
2. Embedded Servers - Tomcat, Jetty or Undertow
Package server with application JAR
3. Prod ready features :
1. metrics & health checks
2. externalized configs


> Life Before Spring Boot :
springboottutorial.com

Spring Boot Starters :
* spring-boot-starter-web-services: SOAP WEB SERVICES
* spring-boot-starter-web: WEB & RESTful applications
* spring-boot-starter-test: Unit & Integration Testing
* spring-boot-starter-jdbc: Traditional JDBC
* spring-boot-starter-hateoas: Add HATEOAS
* spring-boot-starter-security: Authentication & Authorization using Spring Security
* spring-boot-starter-data-jpa: Spring Data JPA with Hibernate
* spring-boot-starter-cache: Enable Spring's caching mechanism
* spring-boot-starter-data-rest: Expose Simple REST Services using Spring Data REST


* spring-boot-starter-actuator: Use advanced features like monitoring & tracing
* spring-boot-starter-undertow
* spring-boot-starter-jety
* spring-boot-starter-tomcat

* spring-boot-starter-logging: Logging using logbacks
* spring-boot-starter-log4j2: Logging using Log4j2

... and many more!

### Spring Boot Actuator

Add dependency spring-boot-starter-actuator in pom.xml


### Dev help Tool:

spring-boot-devtools : Helps with auto-reloading


# Introduction to JPA and Hibernate : 
With the latest versions of Spring Boot (2.3+),
the H2 database name is randomly generated each
time we restart the server.

We can find the database name and URL from the console log.

To Make DB url constant
> spring.datasource.url=jdbc:h2:mem:testdb
> spring.data.jpa.repositories.bootstrap-mode=default


>@Entity helps identify entity bean

> @Id\
> @GeneratedValue :\
> Hibernate auto generates the id

* Provide a no arg constructor (make it protected or arg constructor would override it)

* In the repository class, autowired EntityManager using @Autowired

* Implement implements CommandLineRunner to run some code during startup
* Running scripts from the data.sql or import.sql file from resources
* logging.level.org.hibernate.type = debug to see sql queries
* logging.level.org.hibernate.type = trace to see dynamic values that are getting passed in query




