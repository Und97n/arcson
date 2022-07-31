package org.philips.arcson.bcoder.common

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.philips.arcson.bcoder.utils.DecoderException
import org.philips.arcson.test_utils.InputFixture
import org.philips.arcson.test_utils.OutputFixture
import kotlin.test.assertEquals

class ShittyNumberTest {
    @Test
    fun testEncodeSmall() {
        val fixture = OutputFixture()
        ShittyNumber.encodeShitty(fixture, 0x6F)

        assertEquals("6F",
            fixture.toHexString())
    }

    @Test
    fun testEncodeMedium1() {
        val fixture = OutputFixture()
        ShittyNumber.encodeShitty(fixture, 0xAB)

        assertEquals("AB 01",
            fixture.toHexString())
    }

    @Test
    fun testEncodeMedium2() {
        val fixture = OutputFixture()
        ShittyNumber.encodeShitty(fixture, 0x1234)

        assertEquals("B4 24",
            fixture.toHexString())
    }

    @Test
    fun testEncodeLarge() {
        val fixture = OutputFixture()
        ShittyNumber.encodeShitty(fixture, 0x12345678)

        assertEquals("F8 AC D1 91 01",
            fixture.toHexString())
    }

    @Test
    fun testDecodeSmall() {
        val fixture = InputFixture.fromHexString("2F")
        val actual = ShittyNumber.decodeShitty(fixture)

        assertEquals(0x2F,
            actual)
    }

    @Test
    fun testDecodeMedium1() {
        val fixture = InputFixture.fromHexString("CA 01")
        val actual = ShittyNumber.decodeShitty(fixture)

        assertEquals(0xCA,
            actual)
    }

    @Test
    fun testDecodeMedium2() {
        val fixture = InputFixture.fromHexString("CA CA 01")
        val actual = ShittyNumber.decodeShitty(fixture)

        assertEquals(0x654A,
            actual)
    }

    @Test
    fun testDecodeLarge() {
        val fixture = InputFixture.fromHexString("CA CA CA CA 01")
        val actual = ShittyNumber.decodeShitty(fixture)

        assertEquals(0x1952A54A,
            actual)
    }

    @Test
    fun testDecodeTooLarge() {
        val fixture = InputFixture.fromHexString("CA CA CA CA CA 01")
        Assertions.assertThrows(DecoderException::class.java) { ShittyNumber.decodeShitty(fixture) }
    }

    @Test
    fun testEncodeDecodeLarge() {
        val number = 0x98765421.toInt()

        val outF = OutputFixture()

        ShittyNumber.encodeShitty(outF, number)

        assertEquals("A1 A8 D9 C3 09",
            outF.toHexString())

        val inF = InputFixture.fromHexString(outF.toHexString())

        assertEquals(number,
            ShittyNumber.decodeShitty(inF))
    }
}