package org.philips.arcson.schema.blueprint

class JsonNumberBlueprint: JsonSimpleObjectBlueprint() {
    override val type: ObjType<*>
        get() = TYPE

    companion object {
        val TYPE = ObjType(JsonNumberBlueprint::class.java, ::JsonNumberBlueprint)
    }

    override fun toNiceString(indent: StringIndentation): String = "$indent#NUM[$occurrences]"
}