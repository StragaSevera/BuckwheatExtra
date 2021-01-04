package ru.ought.buckwheatextra.utils

import java.util.*

private val mapOfNumerals = TreeMap(mapOf(
    1000 to "M",
    900 to "CM",
    500 to "D",
    400 to "CD",
    100 to "C",
    90 to "XC",
    50 to "L",
    40 to "XL",
    10 to "X",
    9 to "IX",
    5 to "V",
    4 to "IV",
    1 to "I",
))

fun toRoman(number: Int): String {
    tailrec fun toRomanInner(number: Int, result: StringBuilder): StringBuilder {
        if (number <= 0) return result
        val l = mapOfNumerals.floorKey(number)
        result.append(mapOfNumerals[l])
        return if (number == l) {
            result
        } else {
            toRomanInner(number - l, result)
        }
    }
    return toRomanInner(number, StringBuilder()).toString()
}