package org.philips.arcson.schema

import com.jsoniter.JsonIterator
import com.jsoniter.ValueType
import org.philips.arcson.schema.blueprint.*
import org.philips.arcson.schema.blueprint.models.JsonNumberBlueprint
import org.philips.arcson.schema.blueprint.models.JsonObjectBlueprint
import org.philips.arcson.schema.blueprint.models.JsonStringBlueprint

@JvmInline
value class JsonParser(private val iterator: JsonIterator) {
    constructor(json: String): this(JsonIterator.parse(json))

    fun parse(root: JsonComplexObjectBlueprint) {
        while (next(iterator.readObject(), root)) {}
    }

    private fun mapTypes(tp: ValueType): ObjType<*> =
        when (tp) {
            ValueType.OBJECT -> JsonObjectBlueprint.TYPE
            ValueType.STRING -> JsonStringBlueprint.TYPE
            ValueType.NUMBER -> JsonNumberBlueprint.TYPE
            else -> null!!
        }

    private fun next(fieldStr: String?, parent: JsonComplexObjectBlueprint): Boolean {
        if (fieldStr == null) return false

        val fieldName = FieldName(fieldStr)

        val blueprint = parent.nextEncounter(fieldName, mapTypes(iterator.whatIsNext()))

        if (blueprint is JsonComplexObjectBlueprint) {
            iterator.readObjectCB({ _, fld, par ->
                next(fld, par as JsonComplexObjectBlueprint)
            }, blueprint)
        } else {
            when (blueprint.type) {
                JsonStringBlueprint.TYPE -> iterator.readString()
                JsonNumberBlueprint.TYPE -> iterator.readNumberAsString()
                else -> null!!
            }
        }

        return true
    }
}

