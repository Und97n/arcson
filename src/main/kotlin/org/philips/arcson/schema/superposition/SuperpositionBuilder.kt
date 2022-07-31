package org.philips.arcson.schema.superposition

import org.philips.arcson.FieldName
import org.philips.arcson.JsonHandler
import org.philips.arcson.schema.superposition.models.ComplexElementSuperposition
import org.philips.arcson.schema.superposition.models.RootSuperposition
import org.philips.arcson.schema.superposition.models.SimpleElementSuperposition
import org.philips.arcson.schema.superposition.models.Superposition
import org.philips.arcson.type.ArcsonTypeComplex
import org.philips.arcson.type.ArcsonTypeSimple

class SuperpositionBuilder:
    JsonHandler<Superposition, ComplexElementSuperposition, SimpleElementSuperposition> {

    val rootNode: ComplexElementSuperposition = RootSuperposition()

    override fun onRoot(): ComplexElementSuperposition {
        rootNode.incrementOccurrences()
        return rootNode
    }

    override fun onNextSimpleEntry(
        parent: ComplexElementSuperposition,
        type: ArcsonTypeSimple,
        fieldName: FieldName?,
        value: Any?
    ): SimpleElementSuperposition =
        parent.nextSimpleEncounter(type, fieldName, value)
            .let {
                it.incrementOccurrences()
                it
            }

    override fun onNextComplexEntry(
        parent: ComplexElementSuperposition,
        type: ArcsonTypeComplex,
        fieldName: FieldName?
    ): ComplexElementSuperposition =
        parent.nextComplexEncounter(type, fieldName)
            .let {
                it.incrementOccurrences()
                it
            }

}

