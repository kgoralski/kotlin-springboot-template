package pl.goralski.converters

interface BaseConverter<F, T> {

    fun toDto(from: F): T

    fun toEntity(from: T): F

    fun toDtos(fElements: Iterable<F>): Collection<T> {
        return fElements.map { element ->
            toDto(element)
        }.toList()
    }

    fun toEntities(fElements: Iterable<T>): Collection<F> {
        return fElements.map { element ->
            toEntity(element)
        }.toList()
    }
}