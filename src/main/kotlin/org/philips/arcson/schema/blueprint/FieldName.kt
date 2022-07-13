package org.philips.arcson.schema.blueprint

@JvmInline
value class FieldName(private val name: String) {
    override fun toString(): String = "#'$name'"
}
