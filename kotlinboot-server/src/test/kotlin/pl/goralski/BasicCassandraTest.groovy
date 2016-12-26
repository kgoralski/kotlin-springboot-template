package pl.goralski

import org.cassandraunit.spring.CassandraDataSet
import org.cassandraunit.spring.CassandraUnit
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import pl.goralski.dao.BookRepository
import spock.lang.Specification

import javax.inject.Inject

@SpringBootTest(["spring.data.cassandra.port=9142",
        "spring.datacassandra.keyspace-name=booksdb"])
@EnableAutoConfiguration
@ComponentScan
@ContextConfiguration
@TestExecutionListeners([CassandraUnitDependencyInjectionTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class])
@CassandraDataSet(value = "setup.cql", keyspace = "booksdb")
@CassandraUnit
class BasicCassandraTest extends Specification {

    @Inject
    BookRepository repository


    def "Repository should return empty list"() {
        given:
        repository.deleteAll()

        when:
        def actual = repository.findAll()

        then:
        actual == []
    }

}