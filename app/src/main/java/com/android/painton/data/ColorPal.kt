package com.android.painton.data

class ColorPal(
    var color: Int,
    var check: Boolean
) {
    override fun equals(other: Any?): Boolean {
        return other is ColorPal && other.color == color
    }

    override fun hashCode(): Int {
        var result = color
        result = 31 * result + check.hashCode()
        return result
    }
}