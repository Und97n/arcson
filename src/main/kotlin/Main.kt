import org.philips.arcson.schema.blueprint.models.JsonObjectBlueprint
import org.philips.arcson.schema.JsonParser
import kotlin.random.Random

val INPUT = arrayOf(
    """
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
""", """
        {
        	"id": "0001",
        	"type": "donut",
        	"name": "Cake",
        	"image":
        		{
        			"url": "images/0001.jpg",
        			"width": 200,
        			"height": 200
        		}
        }
""", """
        {
        	"id": "0001",
        	"type": "donut",
        	"image":
        		{
        			"url": "images/0001.jpg"
        		},
        	"thumbnail":
        		{
        			"url": "images/thumbnails/0001.jpg"
        		}
        }
""")

fun main(args: Array<String>) {

    val obj = JsonObjectBlueprint()

    for (i in 1..1000000) {
        val parser = JsonParser(INPUT[Random.nextInt(INPUT.size)])
        parser.parse(obj)
    }

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