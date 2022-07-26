package org.philips.arcson.type

import java.util.*
import java.util.stream.Stream

@Suppress("UNCHECKED_CAST")
open class ObjectByArcsonTypeMap<T> (
    private val data: Array<Any?> = Array(ArcsonValueTypeUID.MAX_VALUE+1) { null }
) {
    operator fun get(type: ArcsonValueType): T? =
        data[type.uid.asIntValue()] as T?

    operator fun set(type: ArcsonValueType, obj: T) =
        if (has(type)) {
            throw RuntimeException()
        } else {
            data[type.uid.asIntValue()] = obj
        }

    fun getOrMake(type: ArcsonValueType, createFn: () -> T): T =
        this[type] ?: createFn().let {
            set(type, it)
            it
        }

    fun has(type: ArcsonValueType): Boolean = data[type.uid.asIntValue()] !== null

    fun computeSize(): Int =
        data.count(Objects::nonNull)

    fun getFirst(): T? = data.firstNotNullOfOrNull { it as T? }

    fun stream(): Stream<T> =
        Arrays.stream(data).filter(Objects::nonNull).map { it as T }
}