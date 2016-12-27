package pl.goralski.web

import pl.goralski.services.BookService
import pl.goralski.web.api.BookWebApi
import pl.goralski.web.model.BookDto
import java.util.*
import javax.inject.Inject
import javax.ws.rs.core.Response

open class BookWebApiImpl @Inject constructor(val service: BookService) : BookWebApi {
    // This class needs tests too and better error handling :)

    override fun getBooks(): Response {
        return Response.ok(service.getBooks()).build()
    }

    override fun createBook(book: BookDto): Response {
        return Response.ok(service.createBook(book)).build()
    }

    override fun updateBook(id: UUID, book: BookDto): Response {
        return Response.ok(service.updateBook(id, book)).build()
    }

    override fun getBookById(id: UUID): Response {
        return Response.ok(service.getBookById(id)).build()
    }

    override fun deleteBookById(id: UUID): Response {
        service.deleteBookById(id)
        return Response.noContent().build()
    }

    override fun findByTitle(title: String): Response {
        return Response.ok(service.findByTitle(title)).build()
    }

    override fun deleteAllBooks(): Response {
        service.deleteAllBooks()
        return Response.noContent().build()
    }
}
