package org.philips.arcson.text

import org.philips.arcson.FieldName
import org.philips.arcson.JsonHandler
import org.philips.arcson.type.*
import org.philips.arcson.utils.StringIndentation
import java.io.PrintWriter

class JsonWriter(
    private val output: PrintWriter,
    private val initialIndentation: StringIndentation = StringIndentation(0)
): JsonHandler<StringIndentation, StringIndentation, StringIndentation> {

    private var lastEntryWasSimple: Boolean = false

    override fun onRoot(): StringIndentation =
        initialIndentation

    override fun onNextComplexEntry(parent: StringIndentation, type: ArcsonTypeComplex, fieldName: FieldName?): StringIndentation {
        if (lastEntryWasSimple) {
            output.print(",")
        }

        if (fieldName != null) {
            output.print("$parent\"${fieldName.value}\": ")
        } else {
            output.print("$parent")
        }

        when (type) {
            is ArcsonTypeObject -> output.print("{")
            is ArcsonTypeArray -> output.print("[")
        }

        lastEntryWasSimple = false

        return parent.next()
    }

    override fun onNextSimpleEntry(parent: StringIndentation, type: ArcsonTypeSimple, fieldName: FieldName?, value: Any?): StringIndentation {
        val data = when (type) {
            is ArcsonTypeString -> "\"$value\""
            else -> value.toString()
        }

        if (lastEntryWasSimple) {
            output.print(",")
        }

        if (fieldName != null) {
            output.print("$parent\"${fieldName.value}\": ")
        } else {
            output.print("$parent")
        }

        output.print(data)

        lastEntryWasSimple = true

        return parent
    }

    override fun onComplexEntryEnd(node: StringIndentation) {
        output.print("$node}")
    }
}