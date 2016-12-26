# Kotlin Spring Boot Application Scaffold 
You are using it at your own risk. It is playground project. Very quickie. 
For production it need a little bit more. :)

## About:
- Kotlin
- Spring Boot
- REST JAX-RS and Jersey Proxy Client
- Sprind Data JPA with H2 Database
- Spring Data NoSQL with Apache Cassandra (from Docker for App, embedded for Tests)
- Spring Data REST based on JPA
- Tests with Spock
- Gradle Kotlin Script with multi modules


#### Endpoints JAX-RS for Apache Cassandra
- GET http://localhost:8080/rest/books/
- GET http://localhost:8080/rest/books/1
- POST http://localhost:8080/rest/books/ { payload }
- PUT http://localhost:8080/rest/books/1 { payload }
- DELETE http://localhost:8080/rest/books/1
- DELETE http://localhost:8080/rest/books/
- GET http://localhost:8080/rest/books/find?title=Hobbit

#### Spring Data REST with H2 Database

- Exposing Spring Data Repositories to the world
- With all features like: pagingm sorting, even custom queries
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

#### Apache Cassandra and Docker

- Apache Cassandra 2.x.x 
```
docker run --name cassandradb-2.1.16 -p "7191:7191" -p "7000:7000" -p "7001:7001" -p "9160:9160" -p "9042:9042" -d cassandra:2.1.16
```
- Apache Cassandra 3.x.x will be probably supported for Spring Boot 1.5.0 RELEASE
```
docker run --name cassandradb -p "7191:7191" -p "7000:7000" -p "7001:7001" -p "9160:9160" -p "9042:9042" -d cassandra:latest
```
- Script
```sql
CREATE KEYSPACE IF NOT EXISTS booksdb WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
CREATE TABLE IF NOT EXISTS books (id TimeUUID PRIMARY KEY, title text, author text, cover text);
CREATE INDEX IF NOT EXISTS booktitleindex ON books(title);
CREATE INDEX IF NOT EXISTS bookauthorindex ON books(author);
```

#### Intellij IDEA
- Gradle Kotlin Script :"This sample should work against the latest Kotlin plugin, 
1.1-M02-12 at the time of this writing, from the Early Access Preview 1.1 channel accessible 
via the Configure Kotlin Plugin Updates action." https://github.com/gradle/gradle-script-kotlin/tree/master/samples
- Running Spock tests inside Eclipse might be difficult

