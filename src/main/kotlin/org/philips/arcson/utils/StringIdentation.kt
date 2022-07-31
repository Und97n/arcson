package org.philips.arcson.utils

@JvmInline
value class StringIndentation(private val indent: Int) {
    override fun toString(): String = "\n" + ("  ".repeat(indent))
    fun next(): StringIndentation = StringIndentation(indent+1)
}