package org.philips.arcson.bcoder.json.common

import org.philips.arcson.bcoder.json.ComplexEntryCoder
import org.philips.arcson.bcoder.json.SimpleEntryCoder
import org.philips.arcson.bcoder.utils.UnsupportedTypeException
import org.philips.arcson.type.*

object CommonCoders {
    fun chooseSimpleCoder(type: ArcsonTypeSimple): SimpleEntryCoder =
        when (type) {
            is ArcsonTypeNumber -> CommonNumberCoder
            is ArcsonTypeString -> CommonStringCoder
            else -> throw UnsupportedTypeException(this.toString(), type)
        }

    fun chooseComplexCoder(type: ArcsonTypeComplex): ComplexEntryCoder =
        when (type) {
            is ArcsonTypeObject -> CommonObjectCoder
            is ArcsonTypeArray -> CommonArrayCoder
            else -> throw UnsupportedTypeException(this.toString(), type)
        }


}