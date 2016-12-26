package pl.goralski.client

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration
import pl.goralski.web.model.BookDto
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ComponentScan
@ContextConfiguration
class BookClientTest extends Specification {

    @LocalServerPort
    int port;

    @Shared
    BookClient bookClient
    def title = "title"
    def author = "author"
    def cover = "cover"

    def setup() {
        bookClient = new BookClient("http://localhost:" + port + "/rest")
        bookClient.deleteAllBooks()
    }

    def "Should create book"() {
        given:
        def bookDto = new BookDto(null, title, author, cover, null)

        when:
        def actual = bookClient.createBook(bookDto)

        then:
        actual.id != null
        actual.fullName != null
    }

    def "Should get one book"() {
        given:
        def bookDto = new BookDto(null, title, author, cover, null)
        def createdBook = bookClient.createBook(bookDto)

        when:
        def actual = bookClient.getBookById(createdBook.id)

        then:
        actual.id == createdBook.id
        actual.fullName == author + " " + title
    }

    def "Should update book"() {
        given:
        def bookDto = new BookDto(null, title, author, cover, null)
        def createdBook = bookClient.createBook(bookDto)
        def toUpdate = new BookDto(null, title + 1, author + 1, cover + 1, null)

        when:
        def actual = bookClient.updateBook(createdBook.id, toUpdate)

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
        bookClient.createBook(bookDtoOne)
        bookClient.createBook(bookDtoTwo)

        when:
        def actual = bookClient.getBooks()

        then:
        actual.size() == 2
    }

    def "Should delete one book"() {
        given:
        def bookDto = new BookDto(null, title, author, cover, null)
        def createdBook = bookClient.createBook(bookDto)

        when:
        def actual = bookClient.deleteBookById(createdBook.id)

        then:
        actual.getStatus() == 204
    }

    def "Should delete all books"() {
        when:
        def actual = bookClient.deleteAllBooks()

        then:
        actual.getStatus() == 204
    }

    def "Should find Book by title"() {
        given:
        def bookDto = new BookDto(null, title, author, cover, null)
        bookClient.createBook(bookDto)

        when:
        def actual = bookClient.findByTitle(title)

        then:
        actual[0].title == title
    }

}