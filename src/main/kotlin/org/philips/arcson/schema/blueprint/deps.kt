package org.philips.arcson.schema.blueprint

import org.philips.arcson.schema.blueprint.models.JsonArrayBlueprint
import org.philips.arcson.schema.blueprint.models.JsonNumberBlueprint
import org.philips.arcson.schema.blueprint.models.JsonObjectBlueprint
import org.philips.arcson.schema.blueprint.models.JsonStringBlueprint
import org.philips.arcson.type.*

fun ArcsonValueTypeSimple.createBlueprintS(): JsonBlueprint =
    when (this) {
        is ArcsonValueTypeString -> JsonStringBlueprint()
        is ArcsonValueTypeNumber -> JsonNumberBlueprint()
        else -> throw IllegalAccessException("Json value type $this is not supported for blueprints making")
    }

fun ArcsonValueTypeComplex.createBlueprintC(): JsonBlueprint =
    when (this) {
        is ArcsonValueTypeObject -> JsonObjectBlueprint()
        is ArcsonValueTypeArray -> JsonArrayBlueprint()
        else -> throw IllegalAccessException("Json value type $this is not supported for blueprints making")
    }
