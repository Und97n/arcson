package org.philips.arcson.schema.superposition.models

import org.philips.arcson.type.ArcsonValueType

@JvmInline
value class StringIndentation(private val indent: Int) {
    override fun toString(): String = "\n" + ("  ".repeat(indent))
    fun next(): StringIndentation = StringIndentation(indent+1)
}

abstract class Superposition {
    private var _occurrences: Long = 0

    val occurrences: Long
        get() = _occurrences

    abstract val type: ArcsonValueType

    fun incrementOccurrences() {
        _occurrences++
    }

    abstract fun toNiceString(indent: StringIndentation): String

    final override fun toString(): String = toNiceString(StringIndentation(0))
}