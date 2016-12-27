package pl.goralski.services

import com.datastax.driver.core.utils.UUIDs
import org.springframework.stereotype.Service
import pl.goralski.converters.BookConverter
import pl.goralski.dao.Book
import pl.goralski.dao.BookRepository
import pl.goralski.web.model.BookDto
import java.util.*
import javax.inject.Inject

@Service
class BookService @Inject constructor(val repository: BookRepository, val converter: BookConverter) {

    fun getBooks(): Collection<BookDto> {
        val books = repository.findAll() ?: return emptyList()
        return converter.toDtos(books)
    }

    fun createBook(book: BookDto): BookDto {
        val toSave = Book(UUIDs.timeBased(), book.title, book.author, book.cover)
        return converter.toDto(repository.save(toSave))
    }

    fun updateBook(id: UUID, book: BookDto): BookDto {
        val entity = repository.findOne(id)
        book.id = entity.id
        val toUpdate = converter.toEntity(book)
        return converter.toDto(repository.save(toUpdate))
    }

    fun getBookById(id: UUID): BookDto? {
        val book = repository.findOne(id) ?: return null
        return converter.toDto(book)
    }

    fun deleteBookById(id: UUID) {
        repository.delete(id)
    }

    fun findByTitle(title: String): List<BookDto> {
        return converter.toDtos(repository.findByTitle(title)) as List<BookDto>
    }

    fun deleteAllBooks() {
        repository.deleteAll()
    }
}