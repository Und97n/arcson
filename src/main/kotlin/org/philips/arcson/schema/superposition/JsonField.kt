package org.philips.arcson.schema.superposition

import org.philips.arcson.schema.superposition.models.StringIndentation
import org.philips.arcson.schema.superposition.models.Superposition
import org.philips.arcson.type.ObjectByArcsonTypeMap
import java.util.stream.Collectors

class JsonField: ObjectByArcsonTypeMap<Superposition>() {
    override fun toString(): String =
        toNiceString(StringIndentation(0))

    fun toNiceString(indent: StringIndentation): String {
        val nextIndent = indent.next()

        return if (computeSize() == 1) {
            getFirst()!!.toNiceString(nextIndent)
        } else {
            stream().map { it.toNiceString(nextIndent) }.collect(Collectors.joining(","))
        }
    }
}