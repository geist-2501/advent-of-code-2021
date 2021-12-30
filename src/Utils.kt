import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

data class Point(var x: Int, var y: Int) {
    fun add(p: Point) {
        x += p.x
        y += p.y
    }
}

data class Matrix(val xLim: Int, val yLim: Int) {

    public val xIdx: IntRange
        get() = IntRange(0, xLim - 1)

    public val yIdx: IntRange
        get() = IntRange(0, yLim - 1)

    private val m: Array<Array<Int>> = Array(xLim) { Array(yLim) { 0 } }

    operator fun get(x: Int, y: Int): Int {
        return m[x][y]
    }

    operator fun set(x: Int, y: Int, value: Int) {
        m[x][y] = value
    }

    override fun toString(): String {
        var s = ""
        for (i in m.indices) {
            for (j in m[0].indices) {
                s += "${m[j][i]} "
            }
            s += "\n"
        }

        return s
    }


}

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

fun readPoint(str: String): Point {
    val ints = str.trim().split(',').map { it.toInt() }
    return Point(ints[0], ints[1])
}