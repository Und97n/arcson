package org.philips.arcson.bcoder.json

import org.junit.jupiter.api.Test
import org.philips.arcson.parser.JsonParser
import org.philips.arcson.test_utils.OutputFixture
import kotlin.test.assertEquals

class CommonJsonCoderTests {
    val jsonSimpleValue1 = """
        1234
        """

    val jsonSimpleValue2 = """
        "LaLaLaLaLa"
        """

    val jsonSimpleObject1 = """
        {
        	"data": "HaHaHaha"
        }
        """

    val jsonSimpleObject2 = """
        {
        	"data": 1234567890
        }
        """

    val jsonSimpleObject3 = """
        {
        	"id": "1234567890"
        }
        """

    val jsonSimpleObject4 = """
        {
            "id": [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
        }
        """

    private fun encodeAndDecodeAndDump(json: String): String {
        val sink = OutputFixture()

        val builder = JsonEncodingAdapter(sink)

        JsonParser(json, builder).parse()

        return sink.toHexString()
    }

    @Test
    fun testEncodeSimpleValue1() {
        assertEquals("",
            encodeAndDump(jsonSimpleObject1))
    }
}