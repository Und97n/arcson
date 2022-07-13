import com.jsoniter.JsonIterator
import org.philips.arcson.schema.blueprint.JsonObjectBlueprint
import org.philips.arcson.schema.JsonParser

fun main(args: Array<String>) {
        val input = """
        {
        	"id": "0001",
        	"type": "donut",
        	"name": "Cake",
        	"image":
        		{
        			"url": "images/0001.jpg",
        			"width": 200,
        			"height": 200
        		},
        	"thumbnail":
        		{
        			"url": "images/thumbnails/0001.jpg",
        			"width": 32,
        			"height": 32
        		}
        }
    """
    val parser = JsonParser(input)

    val obj = JsonObjectBlueprint()

    parser.parse(obj)

    println(obj)


//    val iterator: JsonIterator = JsonIterator.parse(input)
//
//    var obj = iterator.readObject()
//
//
//    while (obj != null) {
//        when (iterator.whatIsNext()) {
//            ValueType.OBJECT -> println("OBJ: ${iterator.r()}");
//            ValueType.STRING -> println("STR: ${iterator.readString()}")
//            ValueType.NUMBER -> println("NUM: ${iterator.readNumberAsString()}")
//            else -> iterator.skip()
//        }
//
//        obj = iterator.readObject()
//    }

}