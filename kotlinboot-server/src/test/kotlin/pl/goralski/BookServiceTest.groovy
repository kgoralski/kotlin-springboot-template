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
import pl.goralski.web.BookService
import pl.goralski.web.model.BookDto
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
class BookServiceTest extends Specification {

    @Inject
    BookService tested

    def title = "title"
    def author = "author"
    def cover = "cover"

    def "Should create book"() {
        given:
        def bookDto = new BookDto(null, title, author, cover, null)

        when:
        def actual = tested.createBook(bookDto)

        then:
        actual.id != null
        actual.fullName != null
    }

    def "Should get one book"() {
        given:
        def bookDto = new BookDto(null, title, author, cover, null)
        def createdBook = tested.createBook(bookDto)

        when:
        def actual = tested.getBookById(createdBook.id)

        then:
        actual.id == createdBook.id
        actual.fullName == author + " " + title
    }

    def "Should update book"() {
        given:
        def bookDto = new BookDto(null, title, author, cover, null)
        def createdBook = tested.createBook(bookDto)
        def toUpdate = new BookDto(null, title + 1, author + 1, cover + 1, null)

        when:
        def actual = tested.updateBook(createdBook.id, toUpdate)

        then:
        actual.id == createdBook.id
        actual.title == title + 1
        actual.author == author + 1
        actual.cover == cover + 1
    }

    def "Should get books"() {
        given: "creating BookDto"
        def bookDtoOne = new BookDto(null, title + 1, author + 1, cover + 1, null)
        def bookDtoTwo = new BookDto(null, title + 2, author + 2, cover + 2, null)
        tested.createBook(bookDtoOne)
        tested.createBook(bookDtoTwo)

        when:
        def actual = tested.getBooks()

        then:
        actual.size() == 2
    }

    def "Should delete one book"() {
        given:
        def bookDto = new BookDto(null, title, author, cover, null)
        def createdBook = tested.createBook(bookDto)

        when:
        tested.deleteBookById(createdBook.id)

        then:
        def check = tested.getBookById(createdBook.id)
        check == null
    }

    def "Should delete all books"() {
        when:
        tested.deleteAllBooks()

        then:
        def check = tested.getBooks()
        check == []
    }

    def "Should find Book by title"() {
        given:
        def bookDto = new BookDto(null, title, author, cover, null)
        tested.createBook(bookDto)

        when:
        def actual = tested.findByTitle(title)

        then:
        actual[0].title == title
    }

}