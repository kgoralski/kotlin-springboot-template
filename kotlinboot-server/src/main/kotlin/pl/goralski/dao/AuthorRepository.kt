package pl.goralski.dao

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "authors", path = "authors")
interface AuthorRepository : PagingAndSortingRepository<Author, Long> {

	fun findByLastName(@Param("name") lastName: String): Iterable<Author>

	fun findByFirstName(@Param("name") firstName: String): Iterable<Author>
}
