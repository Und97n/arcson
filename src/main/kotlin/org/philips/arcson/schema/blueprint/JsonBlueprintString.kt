package org.philips.arcson.schema.blueprint

class JsonStringBlueprint: JsonSimpleObjectBlueprint() {
    companion object {
        val TYPE = ObjType(JsonStringBlueprint::class.java, ::JsonStringBlueprint)
    }

    override val type: ObjType<*>
        get() = TYPE

    override fun toNiceString(indent: StringIndentation): String =
        "$indent#STR[$occurrences]"
}