package pl.goralski.web

import org.springframework.hateoas.Resource
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder.linkTo
import pl.goralski.services.BookService
import pl.goralski.web.api.BookHateoasApi
import pl.goralski.web.api.BookWebApi
import pl.goralski.web.model.BookDto
import java.util.*
import javax.inject.Inject
import javax.ws.rs.core.Response

open class BookHateoasApiImpl @Inject constructor(val service: BookService): BookHateoasApi {

    override fun getBookById(id: UUID): Response {
        val bookDto = service.getBookById(id)
        val resource = Resource<BookDto>(bookDto, linkTo(BookHateoasApi::class.java).slash(bookDto!!.id).withSelfRel())
        return Response.ok(resource).build()
    }
}