package org.philips.arcson.schema.superposition.models

import org.philips.arcson.FieldName
import org.philips.arcson.type.ArcsonValueTypeComplex
import org.philips.arcson.type.ArcsonValueTypeSimple

abstract class ComplexElementSuperposition: Superposition() {
    abstract fun nextSimpleEncounter(type: ArcsonValueTypeSimple, name: FieldName?, value: Any?): SimpleElementSuperposition
    abstract fun nextComplexEncounter(type: ArcsonValueTypeComplex, name: FieldName?): ComplexElementSuperposition
}