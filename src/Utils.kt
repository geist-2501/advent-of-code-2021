import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", name).readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun intToBinary(v: Int): String {
    return Integer.toBinaryString(v)
}

fun binaryToInt(b: String): Int {
    var v = 0
    for (i in b.indices) {
        v = (v shl 1) + if (b[i]=='1') 1 else 0
    }

    return v
}

fun readInts(str: String): IntArray {
    val words = str.trim().split("""\s+""".toRegex())
    return words.map { it.toInt() }.toIntArray()
}