package org.philips.arcson.schema.blueprint

import org.philips.arcson.type.ObjectByArcsonTypeMap
import java.util.stream.Collectors

class JsonField: ObjectByArcsonTypeMap<JsonBlueprint>() {
    override fun toString(): String =
        toNiceString(StringIndentation(0))

    fun toNiceString(indent: StringIndentation): String {
        val nextIndent = indent.next()

        return if (computeSize() == 1) {
            "\n${getFirst()!!.toNiceString(nextIndent)}"
        } else {
            stream().map { it.toNiceString(nextIndent) }.collect(Collectors.joining("\n", "(\n", "\n$indent)"))
        }
    }
}