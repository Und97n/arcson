package org.philips.arcson.schema.superposition

import org.philips.arcson.schema.superposition.models.*
import org.philips.arcson.type.*

fun ArcsonTypeSimple.createSuperpositionS(): SimpleElementSuperposition =
    when (this) {
        is ArcsonTypeString -> StringSuperposition()
        is ArcsonTypeNumber -> NumberSuperposition()
        else -> throw IllegalAccessException("Json value type $this is not supported for blueprints making")
    }

fun ArcsonTypeComplex.createSuperpositionC(): ComplexElementSuperposition =
    when (this) {
        is ArcsonTypeObject -> ObjectSuperposition()
        is ArcsonTypeArray -> ArraySuperposition()
        else -> throw IllegalAccessException("Json value type $this is not supported for blueprints making")
    }
