package org.philips.arcson

@JvmInline
value class FieldName(val value: String) {
    override fun toString(): String = "'$value'"
}
