import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.round

fun part1(crabs: List<Int>): Int {
    val crabsSorted = crabs.sorted()

    val median = crabsSorted[floor(crabsSorted.size / 2f).toInt()]

    return crabs.sumOf { abs(it - median) }
}

fun part2(crabs: List<Int>): Int {
    val avg = floor(crabs.sum() / crabs.size.toDouble()).toInt()
    return crabs.sumOf {
        val diff = abs(it - avg)
        (1..diff).sum()
    }
}

fun main() {
    val input = readInput(
        //"resources/day07small.txt"
         "resources/day07.txt"
    )

    val crabs = input.first().split(',').map { it.toInt() }

    println(part1(crabs))
    println(part2(crabs))
}