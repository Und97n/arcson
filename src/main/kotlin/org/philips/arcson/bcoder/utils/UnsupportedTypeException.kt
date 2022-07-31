package org.philips.arcson.bcoder.utils

import org.philips.arcson.type.ArcsonType

class UnsupportedTypeException(_scope: String, _type: ArcsonType):
    BCoderException("Element type $_type is not supported in $_scope")