package org.philips.arcson.schema.superposition

import org.philips.arcson.FieldName
import org.philips.arcson.parser.JsonParsingAdapter
import org.philips.arcson.schema.superposition.models.ComplexElementSuperposition
import org.philips.arcson.schema.superposition.models.RootSuperposition
import org.philips.arcson.schema.superposition.models.SimpleElementSuperposition
import org.philips.arcson.schema.superposition.models.Superposition
import org.philips.arcson.type.ArcsonValueTypeComplex
import org.philips.arcson.type.ArcsonValueTypeSimple

class SuperpositionBuilder:
    JsonParsingAdapter<Superposition, ComplexElementSuperposition, SimpleElementSuperposition, Unit> {

    public val rootNode: ComplexElementSuperposition = RootSuperposition()

    override fun onRoot(context: Unit): ComplexElementSuperposition {
        rootNode.incrementOccurrences()
        return rootNode
    }

    override fun onNextSimpleEntry(
        context: Unit,
        parent: ComplexElementSuperposition,
        type: ArcsonValueTypeSimple,
        fieldName: FieldName?,
        value: Any?
    ): SimpleElementSuperposition =
        parent.nextSimpleEncounter(type, fieldName, value)
            .let {
                it.incrementOccurrences()
                it
            }

    override fun onNextComplexEntry(
        context: Unit,
        parent: ComplexElementSuperposition,
        type: ArcsonValueTypeComplex,
        fieldName: FieldName?
    ): ComplexElementSuperposition =
        parent.nextComplexEncounter(type, fieldName)
            .let {
                it.incrementOccurrences()
                it
            }

}

