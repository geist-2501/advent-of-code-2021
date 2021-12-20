import kotlin.math.ceil
import kotlin.math.floor

fun main() {
    fun part1(input: List<String>): Int {
        val n = input.size
        val wordLen = input[0].length
        val acc = IntArray(wordLen)
        for (word in input) {
            for (i in word.indices) {
                val v = if (word[i] == '0') 0 else 1
                acc[i] += v
            }
        }

        var gamma = 0
        var epsilon = 0
        for (i in 0 until wordLen) {
            if (acc[i] > floor(n / 2f)) {
                gamma = (gamma shl 1) + 1
                epsilon = epsilon shl 1
            } else {
                gamma = gamma shl 1
                epsilon = (epsilon shl 1) + 1
            }
        }

        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        val n = input[0].length
        var oxygen = input.toList()
        for (col in 0 until n) {
            val numOnes = oxygen.sumOf { Character.getNumericValue(it[col]) }
            val most = if (numOnes >= ceil(oxygen.size / 2f)) '1' else '0'
            oxygen = oxygen.filter { it[col] == most }
            if (oxygen.size <= 1) {
                break
            }
        }

        var co2 = input.toList()
        for (col in 0 until n) {
            val numOnes = co2.sumOf { Character.getNumericValue(it[col]) }
            val least = if (numOnes < ceil(co2.size / 2f)) '1' else '0'
            co2 = co2.filter { it[col] == least }
            if (co2.size <= 1) {
                break
            }
        }

        return binaryToInt(co2[0]) * binaryToInt(oxygen[0])
    }

    val input = readInput("resources/day03.txt")
    println(part1(input))
    println(part2(input))
}

