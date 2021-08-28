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


*** findById, remove using entity manager in repository is easy
> Whenever we change the data (update, remove), we require a transaction or else
> javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread 
> is thrown

* Use @Transactional on the repository to make it transactional
   The methods inside are run as transactions
  
* After running  unit test for operation that changes data like remove or deleteById,
add @DirtiesContext so that after test, original state is brought back.
  
* Entity Manager Playground

1. Because the whole class is @Transactional, even if we update an entity after
   persist, changes are saved.
   EM continues to manage the entity unless the transaction completes.
   Tracks all changes and persists them.
   em.flush() acts like a breakpoint and persists all changes upto this point to the db.
   em.detach(entity) no longer lets EM track the entity beyond this point.
   em.clear() clears out all that EM is tracking at this point.
   
   After updating an entity, we can refresh it with the Database contents using
   em.refresh(entity).
   
   EM is an interface to PersistenceContext
   All entities saved by EM are saved to PersistenceContext.
   
2. JPQL (Java Persistence Query Language) :
   * We can query from Entities (like SQL queries from db tables)
   * These queries are then converted to SQL queries.
   * Ex- select c from Course c
   * Query can return raw type or be cast to our type
   
   * JPA and Hibernate Annotations
   1. @Table(name = "tableName")
      Used when tableName is different from entityName
      Ex- @Table(name = "CourseDetails") -> course_details
      // map table name to entity name
      
   2. @Column(name="fullname", nullable = false)
      private String name;
      
      creates db table column fullname whereas enitity field is 
      name.
      > nullable: false: DatabaseIntegrityViolationException thrown
      > if null value is passed to this field/column
      
      Other Attributes: 
      unique() default false
      insertable() default true
      updatable() default true
      int length() default 255
      int precision, scale, etc
      
   3. Hibernate solutions for creation time
   
      @UpdateTimestamp
      private LocalDateTime lastUpdatedDate;
      
      @CreatedTimestamp
      private LocalDateTime createdDate;
      
   4. @NamedQuery and @NamedQueries :
      To be used instead of hard coding the queries so that
      they are reusable.
      
      On the entity, we add the query
   
      * one @NamedQuery per entity else Duplicate Exception is thrown
      * for multiple named queries, use @NamedQueries
      * Ex- @NamedQueries(
           value={
              @NamedQuery(name, query),
              @NamedQuery(name, query),
           }
        )
        
      * called using EM.createNamedQuery(name);
   
   5. Native Queries :
      Situations where native query needs to be fired (direct SQL) like mass update or
      use db specific feature not provided by JPA.
      EM.createNativeQuery()
      em.createNativeQuery("select * from course where id = ?", Course.class);
      query.setParameter(1, value) // positional Parameter

      em.createNativeQuery("select * from course where id = :id", Course.class);
      query.setParameter("id", value) // named Parameter
      
      
      
### Relationships with JPA and Hibernate

   ## One-to-One Relationship
   Student - related - Passport
   1. Create Passport_id in student or student_id in passport
      (table that has foreign id owns the relationship)
      @OneToOne jpa annotation
      ex - in Student Entity 
      @OneToOne
      private Passport passport;
      
   2. Can be unidirectional or bidirectional

   >SELECT * FROM STUDENT s , Passport p where p.id = s.passport_id;

   ID  	NAME  	PASSPORT_ID  	ID  	NUMBER  
   2001	Tony	4001	4001	E123456
   2002	Cap	4002	4002	N123456
   2003	Nat	4003	4003	I123456
   (3 rows, 2 ms)

   3. Hibernate is lazy, it will delay as long as it can before writing to db
   4. Eager Fetch vs Lazy Fetch
      * Any 1-1 relation is by default EAGER_FETCHED (while getting students, 
        student is left outer joined with passport and fetched)
        > Hibernate:
        select
        student0_.id as id1_3_0_,
        student0_.name as name2_3_0_,
        student0_.passport_id as passport3_3_0_,
        passport1_.id as id1_1_1_,
        passport1_.number as number2_1_1_
        from
        student student0_
        left outer join
        passport passport1_
        on student0_.passport_id=passport1_.id
        where
        student0_.id=?
        
      * Student => Student{id=2001, name='Tony', passport=Passport{id=4001, number='E123456'}}
        2021-08-28 13:00:39.335  INFO 19718 --- [           main] c.s.j.h.r.StudentRepositoryTests         : Passport details => Passport{id=4001, number='E123456'}
        
      * LAZY_FETCHING for better performance
        On Entity Student,
        @OneToOne(fetch = FetchType.LAZY)
        private Passport passport;
        
      * Without @Transactional, we might get err like lazyInitializer exception
         could not initialize proxy no Session found (Hibernate Session)
        
      * FE lazy fetch to hibernate (TODO)



    5. Bi directional 1-1 is not recommended (leads to duplicacy of info)
        * One owning side of relationship
        * mapped by is applied on the non-owning side of relationship, ex -
        * we can access each from other but extra column for id is created only on owning side
        * In DB, Student has foreign key but passport does not have (using mapped by )
        
*       @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
        private Student student;

    6. One to Many relationship
        * by default fetch type lAZY on ONE side ex-Course.getReviews()
        * can be changed to EAGER
        * ending with *toMany : default is lazy fetching
        * on MANY side it is eager by default
    

### Many-to-Many 
Course <-> Student

Creation of JOIN Table

CourseId : StudentId


* Bi-directional mapping will create 2 join tables
To fix this, we make one side the Owning side!
  
### Inheritance Hierarchies with JPA and Hibernate

####  Single Table, Table per class, Join Table, Mapped Superclass

    Methods to map entities having inheritance relations


Example : Employee
            1. Part Time Employee
            2. Full Time Employee

1. Single Table Method

Both Part time & Full time employees would be stored in single table.
on the Employee entity (abstract class), we have
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
(default is also Single Table)

> SELECT * FROM EMPLOYEE;
DTYPE  	ID  	NAME  	SALARY  	HOURLY_WAGE  
EmployeeFullTime	1	Jack	10000.00	null
EmployeePartTime	2	Jill	null	50.00
(2 rows, 2 ms)
 DTYPE > Type of subclass
@DiscriminatorColumn(name="EmployeeType") changes DTYPE column name to EmployeeType
 
Problem: lot of nullable columns
Advantage: Performance (no joins needed)

2. Table Per Class
Creates a table per concrete Entity Class
   * How we insert/retrieve employee data will not change due to inheritance type used
    
Therefore, two tables are created
FULL_TIME_EMPLOYEE
PART_TIME_EMPLOYEE

Employees are retrieved using Union of these two tables.
Problem: Common columns are repeated, large no of subclasses can cause
performance issues

3. JOINED

Creates 3 tables
Employee (Superclass)
FullTimeEmployee(subclass 1)
PartTimeEmployee(subclass 2)

SELECT * FROM EMPLOYEE;
ID  	NAME  
1	Jack
2	Jill

SELECT * FROM EMPLOYEE_FULL_TIME;
SALARY  	ID  
10000.00	1

SELECT * FROM EMPLOYEE_PART_TIME;
HOURLY_WAGE  	ID  
50.00	2


To get the data, it performs a join on them (3 tables).
Low performance if no of subclasses are more.

4. Mapped SuperClass

A Class cannot be both same Entity and MappedSuperClass
Mapping will apply for subclass
Parent has no table in database
Mappings are like entity.

JPQL

BETWEEN 100 and 1000
IS NULL
upper, lower, trim, length

JOINS => JOIN, LEFT JOIN, CROSS JOIN
JOIN => Select c,s from Course c JOIN c.students s
LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
CROSS JOIN => Select c, s from Course c, Student s

Criteria API
Create Criteria Query

1. Use criteria builder to create a criteria query returning the
expected result object
   
2. Define roots for tables which are involved in query
3. Define Predicates etc using Criteria Builder
4. Add Predicates etc to the Criteria Query
5. Build the TypedQuery using EM and criteria query


### Transaction Management
* Maintain ACID Properties
  
Transactions in parallel > 
* Dirty Read : Trans B Reading a write from trans A before trans A committed.
* Phantom Read : Getting different rows when read multiple times (say one new was added in between)
* Non-repeatable Read : Read from a trans that is being updated and so different values are read.

* 4 Isolation Levels
                    Dirty Read | NR Read | P Read | 
Read Uncommitted    Possible    Possible    Possible
Read Committed      Solved      Possible    Possible
Repeatable Read     Solved      Solved      Possible
Serializable         Solved      Solved      Solved
  
@Transactional(isolation = )

* Implementation: Points to Ponder

JPA transaction : for a DB
org.springframework transaction : multi db, mq etc


### Caching

PersistenceContext -> 1st level caching -> 2nd level caching -> DB

1st level cache : Within boundary of a single transaction
2nd level cache : Common info for all users across the app





   
   

   
   



