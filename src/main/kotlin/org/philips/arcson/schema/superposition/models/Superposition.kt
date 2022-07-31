package org.philips.arcson.schema.superposition.models

import org.philips.arcson.type.ArcsonType
import org.philips.arcson.utils.StringIndentation

abstract class Superposition {
    private var _occurrences: Long = 0

    val occurrences: Long
        get() = _occurrences

    abstract val type: ArcsonType

    fun incrementOccurrences() {
        _occurrences++
    }

    abstract fun toNiceString(indent: StringIndentation): String

    final override fun toString(): String = toNiceString(StringIndentation(0))
}