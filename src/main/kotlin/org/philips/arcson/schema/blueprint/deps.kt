package org.philips.arcson.schema.blueprint

import org.philips.arcson.common.type.*
import org.philips.arcson.schema.blueprint.models.JsonNumberBlueprint
import org.philips.arcson.schema.blueprint.models.JsonObjectBlueprint
import org.philips.arcson.schema.blueprint.models.JsonStringBlueprint
import org.philips.arcson.type.ArcsonValueType
import org.philips.arcson.type.ArcsonValueTypeNumber
import org.philips.arcson.type.ArcsonValueTypeObject
import org.philips.arcson.type.ArcsonValueTypeString

fun ArcsonValueType.createBlueprint(value: Any? = null): JsonBlueprint =
    when (this) {
        is ArcsonValueTypeObject -> JsonObjectBlueprint()
        is ArcsonValueTypeString -> JsonStringBlueprint()
        is ArcsonValueTypeNumber -> JsonNumberBlueprint()
        else -> throw IllegalAccessException("Json value type $this is not supported for blueprints making")
    }
