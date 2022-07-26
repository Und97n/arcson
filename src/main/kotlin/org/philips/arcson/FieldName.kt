package org.philips.arcson

@JvmInline
value class FieldName(private val name: String) {
    override fun toString(): String = "'$name'"
}
