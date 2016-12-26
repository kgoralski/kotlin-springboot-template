package pl.goralski.web.api

import pl.goralski.web.model.BookDto
import java.util.*
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/books")
interface BookWebApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getBooks(): Response

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getBookById(@PathParam("id") id: UUID): Response

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createBook(book: BookDto): Response

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateBook(@PathParam("id") id: UUID, book: BookDto): Response

    @DELETE
    fun deleteAllBooks(): Response

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteBookById(@PathParam("id") id: UUID): Response

    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    fun findByTitle(@QueryParam("title") title: String): Response
}