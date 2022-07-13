package org.philips.arcson.schema.blueprint

class ObjType<T: JsonBlueprint>(private val type: Class<T>, private val constructor: () -> T) {
    fun newInstance(): JsonBlueprint = constructor()

    override fun equals(other: Any?): Boolean =
        when (other) {
            is ObjType<*> -> other.type == this.type
            else -> false
        }

    override fun hashCode(): Int = type.hashCode()

    override fun toString(): String = "JsonType($type)"
}