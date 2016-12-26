package pl.goralski.client

import com.fasterxml.jackson.module.kotlin.*
import pl.goralski.web.model.BookDto
import pl.goralski.web.api.BookWebApi
import java.util.*
import javax.ws.rs.core.Response

class BookClient(resourceURI: String) : GenericClient<BookWebApi>(
        resourceURI, BookWebApi::class.java) {

    // jackson kotlin module
    val JSON = jacksonObjectMapper()

    fun getBooks(): List<BookDto> {
        val response = resource!!.getBooks().readEntity(String::class.java)
        return JSON.readValue(response)
    }

    fun getBookById(id: UUID): BookDto? {
        val response = resource!!.getBookById(id).readEntity(String::class.java)
        return JSON.readValue(response)
    }

    fun createBook(bookDto: BookDto): BookDto {
        val response = resource!!.createBook(bookDto).readEntity(String::class.java)
        return JSON.readValue(response)
    }

    fun updateBook(id: UUID, bookDto: BookDto): BookDto {
        val response = resource!!.updateBook(id, bookDto).readEntity(String::class.java)
        return JSON.readValue(response)
    }

    fun deleteAllBooks(): Response {
        return resource!!.deleteAllBooks()
    }

    fun deleteBookById(id: UUID): Response {
        return resource!!.deleteBookById(id)
    }

    fun findByTitle(title: String): List<BookDto> {
        val response = resource!!.findByTitle(title).readEntity(String::class.java)
        return JSON.readValue(response)
    }


}