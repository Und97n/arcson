package org.philips.arcson.type

import java.util.*
import java.util.stream.Stream

@Suppress("UNCHECKED_CAST")
open class ObjectByArcsonTypeMap<T> (
    private val data: Array<Any?> = Array(TypeId.MAX_VALUE+1) { null }
) {
    operator fun get(type: ArcsonType): T? =
        data[type.id.asIntValue()] as T?

    operator fun set(type: ArcsonType, obj: T) =
        if (has(type)) {
            throw RuntimeException()
        } else {
            data[type.id.asIntValue()] = obj
        }

    fun getOrMake(type: ArcsonType, createFn: () -> T): T =
        this[type] ?: createFn().let {
            set(type, it)
            it
        }

    fun has(type: ArcsonType): Boolean = data[type.id.asIntValue()] !== null

    fun computeSize(): Int =
        data.count(Objects::nonNull)

    fun getFirst(): T? = data.firstNotNullOfOrNull { it as T? }

    fun stream(): Stream<T> =
        Arrays.stream(data).filter(Objects::nonNull).map { it as T }
}