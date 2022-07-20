package org.philips.arcson.schema.blueprint.models

import org.philips.arcson.schema.blueprint.JsonSimpleBlueprint
import org.philips.arcson.schema.blueprint.StringIndentation
import org.philips.arcson.type.ArcsonValueType
import org.philips.arcson.type.ArcsonValueTypeString

class JsonStringBlueprint: JsonSimpleBlueprint() {
    override val type: ArcsonValueType
        get() = ArcsonValueTypeString

    override fun toNiceString(indent: StringIndentation): String =
        "$indent#STR[$occurrences]"
}