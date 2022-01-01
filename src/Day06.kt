class Timeline(private val size: Int) {

    private val timeline: Array<Long> = Array(size) { 0 }

    private var offset: Int = 0

    fun advance() {
        offset++
    }

    operator fun get(i: Int): Long {
        return timeline[(i + offset) % size]
    }

    operator fun set(i: Int, value: Long) {
        timeline[(i + offset) % size] = value
    }

    fun sum(): Long {
        return timeline.sum()
    }

    override fun toString(): String {
        var s = ""
        for (i in timeline.indices) {
            s += "${get(i)} "
        }
        return s
    }


}

fun part1(fishes: List<Int>, iterations: Int): Long {
    val timeline = Timeline(10)
    fishes.forEach { timeline[it]++ }

    // Check 80 iterations.
    for (i in 1..iterations) {
        val spawned = timeline[0]
        timeline[7] += spawned // Existing fishies reset.
        timeline[9] += spawned // New fishies added.
        timeline[0] = 0
        timeline.advance()
    }

    return timeline.sum()
}

fun main() {
    val input = readInput(
    //    "resources/day06small.txt"
        "resources/day06.txt"
    )
    val fishes = input.first().split(',').map { it.toInt() }

    println(part1(fishes, 80))
    println(part1(fishes, 256))
}