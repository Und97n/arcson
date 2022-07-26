package org.philips.arcson.bcoder.utils

import java.io.InputStream

class DecoderException(_msg: String, _stream: InputStream): BCoderException(_msg) {
}