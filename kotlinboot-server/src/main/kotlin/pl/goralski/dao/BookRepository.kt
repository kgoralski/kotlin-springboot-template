package pl.goralski.dao

import org.springframework.data.cassandra.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.UUID

@RepositoryRestResource(exported = false) // disable for Spring Data REST exposing
interface BookRepository : CrudRepository<Book, UUID> {
    // PagingAndSortingRepository not supported in Spring Data 1.4.2 version yet

    @Query("Select * from books where title=?0")
    fun findByTitle(title: String): List<Book>

}
