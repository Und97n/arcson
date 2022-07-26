package org.philips.arcson.bcoder.utils

import java.io.OutputStream

class EncoderException(_msg: String, _stream: OutputStream): Exception(_msg) {
}