package org.philips.arcson.bcoder

import org.philips.arcson.type.ArcsonType

interface Coder {
    val targetType: ArcsonType
}