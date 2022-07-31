package org.philips.arcson.schema.superposition.models

import org.philips.arcson.FieldName
import org.philips.arcson.type.ArcsonTypeComplex
import org.philips.arcson.type.ArcsonTypeSimple

abstract class ComplexElementSuperposition: Superposition() {
    abstract fun nextSimpleEncounter(type: ArcsonTypeSimple, name: FieldName?, value: Any?): SimpleElementSuperposition
    abstract fun nextComplexEncounter(type: ArcsonTypeComplex, name: FieldName?): ComplexElementSuperposition
}