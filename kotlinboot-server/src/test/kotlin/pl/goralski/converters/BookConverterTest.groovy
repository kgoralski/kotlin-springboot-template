package pl.goralski.converters

import pl.goralski.dao.Book
import pl.goralski.web.model.BookDto
import spock.lang.Specification

class BookConverterTest extends Specification {

    def "Should convert Entity to Dto"() {
        given:
        def uuid = UUID.randomUUID()
        def book = new Book(uuid, "title", "author", "cover")
        def converter = new BookConverter()

        when:
        def actual = converter.toDto(book)

        then:
        actual.id == book.id
        actual.cover == book.cover
        actual.author == book.author
        actual.title == book.title
        actual.fullName == book.author + " " + book.title
    }

    def "Should convert Dto to Entity"() {
        given: "a new Book class is created"
        def uuid = UUID.randomUUID()
        def book = new BookDto(uuid, "title", "author", "cover", null)
        def converter = new BookConverter()

        when: "converting"
        def actual = converter.toEntity(book)

        then: "book is converted to Dto class"
        actual.id == book.id
        actual.cover == book.cover
        actual.author == book.author
        actual.title == book.title
    }
}
