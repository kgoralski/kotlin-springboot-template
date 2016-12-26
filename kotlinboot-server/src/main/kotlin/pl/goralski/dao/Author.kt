package pl.goralski.dao

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Author(

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        var firstName: String = "",
        var lastName: String = "") {

    override fun toString(): String {
        return "Author(id=$id, firstName='$firstName', lastName='$lastName')"
    }
}
