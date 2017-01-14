# Kotlin Spring Boot Application Scaffold 
You are using it at your own risk. It is playground project. Very quickie. 
For production it need a little bit more. :)

## About:
- Kotlin
- Spring Boot
- REST JAX-RS and Jersey Proxy Client
- HATEOAS Example
- Sprind Data JPA with H2 Database
- Spring Data NoSQL with Apache Cassandra (from Docker for App, embedded for Tests)
- Spring Data REST
- Tests with Spock
- Gradle Kotlin Script with multi modules

#### How to run it?
- gradle clean bootRepackage
- docker start cassandradb-2.1.16
- java -jar kotlinboot-server-0.0.1-SNAPSHOT.jar 

#### Endpoints JAX-RS for Apache Cassandra
- GET http://localhost:8080/rest/books/
- GET http://localhost:8080/rest/books/1
- POST http://localhost:8080/rest/books/ { BookDto }
- PUT http://localhost:8080/rest/books/1 { BookDto }
- DELETE http://localhost:8080/rest/books/1
- DELETE http://localhost:8080/rest/books/
- GET http://localhost:8080/rest/books/find?title=Hobbit

#### Spring Data REST with H2 Database (JPA)

- Exposing Spring Data Repositories to the world
- With all features like: paging sorting, even custom queries like:
```
http://localhost:8080/authors/search/findByFirstName?name=Steven
```
- Create Entity, Repository and Endpoints will be created by Spring

```json
// http://localhost:8080/authors/
{
  "_embedded" : {
    "authors" : [ {
      "firstName" : "Steven",
      "lastName" : "Erikson",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/authors/1"
        },
        "author" : {
          "href" : "http://localhost:8080/authors/1"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/authors"
    },
    "profile" : {
      "href" : "http://localhost:8080/profile/authors"
    },
    "search" : {
      "href" : "http://localhost:8080/authors/search"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 1,
    "totalPages" : 1,
    "number" : 0
  }
}
```
#### HATEOAS Example
```json
{
	"id": "56a915f0-cb9b-11e6-8d84-59dd97b77ed0",
	"title": "title",
	"author": "author",
	"cover": "cover",
	"fullName": "author title",
	"links": [
		{
			"rel": "self",
			"href": "http://localhost:8080/rest/books/hateoas/56a915f0-cb9b-11e6-8d84-59dd97b77ed0"
		}
	]
}
```

#### Apache Cassandra and Docker (for easy setup)

- Apache Cassandra 2.x.x (Used for this project)
```
docker run --name cassandradb-2.1.16 -p "7191:7191" -p "7000:7000" -p "7001:7001" -p "9160:9160" -p "9042:9042" -d cassandra:2.1.16
```
- Apache Cassandra 3.x.x works with Spring Boot 2.0.0 RELEASE
```
docker run --name cassandradb -p "7191:7191" -p "7000:7000" -p "7001:7001" -p "9160:9160" -p "9042:9042" -d cassandra:latest
```
You may need to add this to build.gradle.kts for Apache Cassandra 3.x.x
```kotlin
buildscript {
    val springBootVersion = "2.0.0.BUILD-SNAPSHOT"
	var springSnapshot: String by extra
	springSnapshot = "https://repo.spring.io/snapshot"
	var springMilestone: String by extra
	springMilestone = "https://repo.spring.io/milestone"

	repositories {
		maven{setUrl(springSnapshot)}
		maven{setUrl(springMilestone)}
	}
dependencies {	
	testCompile("org.cassandraunit:cassandra-unit-spring:3.1.1.0")
	}	
}
```
- Script
```sql
CREATE KEYSPACE IF NOT EXISTS booksdb WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
CREATE TABLE IF NOT EXISTS booksdb.books (id TimeUUID PRIMARY KEY, title text, author text, cover text);
CREATE INDEX IF NOT EXISTS booktitleindex ON booksdb.books(title);
CREATE INDEX IF NOT EXISTS bookauthorindex ON booksdb.books(author);
```
You can connect to Cassandra inside docker with http://dbeaver.jkiss.org/download/enterprise/ 
Using cassandra/cassandra login/pass to localhost:9042

- Database inside Docker? [Be warned.](http://patrobinson.github.io/2016/11/07/thou-shalt-not-run-a-database-inside-a-container/)

#### Intellij IDEA
- Gradle Kotlin Script :"This sample should work against the latest Kotlin plugin, 
1.1-M02-12 at the time of this writing, from the Early Access Preview 1.1 channel accessible 
via the Configure Kotlin Plugin Updates action." https://github.com/gradle/gradle-script-kotlin/tree/master/samples
- Running Spock tests inside Eclipse might be difficult

#### References 
- https://github.com/sdeleuze/spring-boot-kotlin-demo
- https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-cassandra
- http://stackoverflow.com/questions/36975997/cassandra-3-5-compatablity-with-spring-data-1-4
- https://www.javacodegeeks.com/2015/01/building-a-hateoas-api-with-jax-rs-and-spring.html
- [Introducing Kotlin support in Spring Framework 5.0!](https://spring.io/blog/2017/01/04/introducing-kotlin-support-in-spring-framework-5-0)
