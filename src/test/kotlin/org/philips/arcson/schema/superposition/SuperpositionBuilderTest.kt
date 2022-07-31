package org.philips.arcson.schema.superposition

import org.junit.jupiter.api.Test
import org.philips.arcson.text.JsonParser
import org.philips.arcson.utils.StringIndentation
import java.util.stream.Stream
import kotlin.test.assertEquals

class SuperpositionBuilderTest {
    val jsonSimpleValue1 = """
        1234
        """.trimIndent()

    val jsonSimpleValue2 = """
        "LaLaLaLaLa"
        """.trimIndent()

    val jsonSimpleObject1 = """
        {
          "data": "HaHaHaha"
        }
        """.trimIndent()

    val jsonSimpleObject2 = """
        {
          "data": 1234567890
        }
        """.trimIndent()

    val jsonSimpleObject3 = """
        {
          "id": "1234567890"
        }
        """.trimIndent()

    val jsonSimpleObject4 = """
        {
          "id": [
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            0
          }
        }
        """.trimIndent()

    val jsonComplexObject1 = """
        {
          "id": "0001",
          "type": "donut",
          "name": "Cake",
          "image": {
            "url": "images/0001.jpg",
            "width": 200,
            "height": 200
          }
        }
        """.trimIndent()

    private fun parseModelAndDump(vararg jsons: String): String {
        val builder = SuperpositionBuilder()

        for (json in jsons) {
            JsonParser(json, builder).parse()
        }

        return builder.rootNode.toNiceString(StringIndentation(0)).trimIndent()
    }

    @Test
    fun testSimpleValue_1() {
        assertEquals("""
            #ROOT(1) [
                #NUM(1)
            ]
        """.trimIndent(),
            parseModelAndDump(jsonSimpleValue1))
    }

    @Test
    fun testSimpleValue_2() {
        assertEquals("""
            #ROOT(3) [
                #NUM(2),
                #STR(1)
            ]
        """.trimIndent(),
            parseModelAndDump(jsonSimpleValue1, jsonSimpleValue1, jsonSimpleValue2))
    }

    @Test
    fun testSimpleValue_3() {
        val jsons = Stream.concat(
            List(100) { jsonSimpleValue1 }.stream(),
            List(200) { jsonSimpleValue2 }.stream()
        ).toList().toTypedArray()

        assertEquals("""
            #ROOT(300) [
                #NUM(100),
                #STR(200)
            ]
        """.trimIndent(),
            parseModelAndDump(*jsons))
    }

    @Test
    fun testObject_1() {
        assertEquals("""
            #ROOT(2) [
                #OBJ[2] {
                  'data' -> 
                    #NUM(1),
                    #STR(1)
                }
            ]
        """.trimIndent(),
            parseModelAndDump(jsonSimpleObject1, jsonSimpleObject2))
    }

    @Test
    fun testObject_2() {
        val jsons = Stream.of(
            List(50) { jsonSimpleObject1 }.stream(),
            List(30) { jsonSimpleObject2 }.stream(),
            List(40) { jsonSimpleObject3 }.stream(),
            List(60) { jsonSimpleObject4 }.stream()
        ).flatMap { it }.toList().toTypedArray()

        assertEquals("""
            #ROOT(180) [
                #OBJ[180] {
                  'data' -> 
                    #NUM(30),
                    #STR(50),
                  'id' -> 
                    #ARRAY(60) [
                        #NUM(600)
                    ],
                    #STR(40)
                }
            ]
        """.trimIndent(),
            parseModelAndDump(*jsons))
    }

    @Test
    fun testObject_3() {
        val jsons = Stream.of(
            List(10) { jsonComplexObject1 }.stream(),
            List(50) { jsonSimpleObject1 }.stream(),
            List(30) { jsonSimpleObject2 }.stream(),
            List(40) { jsonSimpleObject3 }.stream(),
            List(60) { jsonSimpleObject4 }.stream()
        ).flatMap { it }.toList().toTypedArray()

        assertEquals("""
            #ROOT(190) [
                #OBJ[190] {
                  'image' -> 
                    #OBJ[10] {
                      'width' -> 
                        #NUM(10),
                      'url' -> 
                        #STR(10),
                      'height' -> 
                        #NUM(10)
                    },
                  'data' -> 
                    #NUM(30),
                    #STR(50),
                  'name' -> 
                    #STR(10),
                  'id' -> 
                    #ARRAY(60) [
                        #NUM(600)
                    ],
                    #STR(50),
                  'type' -> 
                    #STR(10)
                }
            ]
        """.trimIndent(),
            parseModelAndDump(*jsons))
    }
}