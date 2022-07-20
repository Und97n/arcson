package org.philips.arcson.schema.blueprint.models

import org.philips.arcson.common.type.JsonValueType
import org.philips.arcson.common.type.JsonValueTypeNumber
import org.philips.arcson.schema.blueprint.JsonSimpleBlueprint
import org.philips.arcson.schema.blueprint.StringIndentation

class JsonNumberBlueprint: JsonSimpleBlueprint() {
    override val type: JsonValueType
        get() = JsonValueTypeNumber

    override fun toNiceString(indent: StringIndentation): String = "$indent#NUM[$occurrences]"
}