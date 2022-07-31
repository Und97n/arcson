package org.philips.arcson.bcoder.json

import org.junit.jupiter.api.Test
import org.philips.arcson.test_utils.InputFixture
import org.philips.arcson.text.JsonParser
import org.philips.arcson.test_utils.OutputFixture
import org.philips.arcson.text.JsonWriter
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.test.assertEquals

class CommonJsonCoderIT {
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

    private fun encodeAndCheckLengthAndDecodeAndCheckWithOrigin(json: String, expectedCodedLength: Int) {
        val sink = OutputFixture()

        val builder = JsonEncodingAdapter(sink)

        JsonParser(json, builder).parse()

        val hexData = sink.toHexString()

        println(hexData)

        assertEquals(expectedCodedLength,
            sink.toBytes().size)

        val printer = StringWriter()

        val decoder = JsonDecoder(InputFixture.fromHexString(hexData), JsonWriter(PrintWriter(printer)))

        decoder.decode()

        assertEquals(json.trim(),
            printer.buffer.toString().trim())
    }

    @Test
    fun testEncodeSimpleValue1() =
        encodeAndCheckLengthAndDecodeAndCheckWithOrigin(
            jsonSimpleValue1,
            4)

    @Test
    fun testEncodeSimpleValue2() =
        encodeAndCheckLengthAndDecodeAndCheckWithOrigin(
            jsonSimpleValue2,
            13)

    @Test
    fun testEncodeObject1() =
        encodeAndCheckLengthAndDecodeAndCheckWithOrigin(
            jsonSimpleObject1,
            18)

    @Test
    fun testEncodeObject2() =
        encodeAndCheckLengthAndDecodeAndCheckWithOrigin(
            jsonSimpleObject2,
            14)

    @Test
    fun testEncodeObject3() =
        encodeAndCheckLengthAndDecodeAndCheckWithOrigin(
            jsonSimpleObject3,
            18)

    @Test
    fun testEncodeObject4() =
        encodeAndCheckLengthAndDecodeAndCheckWithOrigin(
            jsonSimpleObject4,
            28)

    @Test
    fun testEncodeComplexObject1() =
        encodeAndCheckLengthAndDecodeAndCheckWithOrigin(
            jsonComplexObject1,
            83)
}