package org.philips.arcson.schema.blueprint

import java.util.stream.Collectors

@JvmInline
value class Field(private val data: MutableList<JsonBlueprint> = ArrayList()) {
    operator fun get(type: ObjType<*>): JsonBlueprint? =
        data.find { it.type == type }

    operator fun set(type: ObjType<*>, obj: JsonBlueprint) =
        if (contains(type)) {
            throw RuntimeException()
        } else {
            data.add(obj)
        }

    fun getOrMake(type: ObjType<*>, createFn: () -> JsonBlueprint): JsonBlueprint =
        this[type] ?: createFn().let {
            this@Field[type] = it
            it
        }

    fun contains(type: ObjType<*>): Boolean = data.find { it.type == type } != null

    override fun toString(): String =
        toNiceString(StringIndentation(0))

    fun toNiceString(indent: StringIndentation): String {
        val nextIndent = indent.next()

        return if (data.size == 1) {
            "\n${data.get(0).toNiceString(nextIndent)}"
        } else {
            data.stream().map { it.toNiceString(nextIndent) }.collect(Collectors.joining("\n", "(\n", "\n$indent)"))
        }
    }
}