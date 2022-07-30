package org.philips.arcson.schema.superposition

import org.philips.arcson.schema.superposition.models.*
import org.philips.arcson.type.*

fun ArcsonValueTypeSimple.createSuperpositionS(): SimpleElementSuperposition =
    when (this) {
        is ArcsonValueTypeString -> StringSuperposition()
        is ArcsonValueTypeNumber -> NumberSuperposition()
        else -> throw IllegalAccessException("Json value type $this is not supported for blueprints making")
    }

fun ArcsonValueTypeComplex.createSuperpositionC(): ComplexElementSuperposition =
    when (this) {
        is ArcsonValueTypeObject -> ObjectSuperposition()
        is ArcsonValueTypeArray -> ArraySuperposition()
        else -> throw IllegalAccessException("Json value type $this is not supported for blueprints making")
    }
