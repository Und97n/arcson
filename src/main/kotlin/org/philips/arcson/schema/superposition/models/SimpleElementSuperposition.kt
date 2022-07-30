package org.philips.arcson.schema.superposition.models

abstract class SimpleElementSuperposition: Superposition() {
    abstract fun nextValue(value: Any?)
}