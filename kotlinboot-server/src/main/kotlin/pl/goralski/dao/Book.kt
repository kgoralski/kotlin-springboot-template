package pl.goralski.dao

import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table
import java.util.*

@Table(value = "books")
class Book @PersistenceConstructor constructor(
        // @PersistenceConstructor solution for empty constructor

        @PrimaryKey
        var id: UUID,
        var title: String = "",
        var author: String = "",
        var cover: String = "") {


    override fun toString(): String {
        return "Book(id=$id, title='$title', author='$author', cover='$cover')"
    }

}
