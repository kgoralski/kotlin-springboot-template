package pl.goralski.converters

import pl.goralski.dao.Book
import pl.goralski.web.model.BookDto
import org.springframework.stereotype.Component

@Component
class BookConverter : BaseConverter<Book, BookDto> {

    override fun toDto(from: Book): BookDto {
        return BookDto(from.id, from.title, from.author, from.cover, from.author + " " + from.title)
    }

    override fun toEntity(from: BookDto): Book {
        return Book(from.id!!, from.title, from.author, from.cover)
    }
}