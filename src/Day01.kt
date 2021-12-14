fun main() {
    fun part1(input: List<Int>): Int {
        var asc = 0
        var prev = input[0]
        for (i in 1 until input.size) {
            if (input[i] > prev) {
                asc++
            }

            prev = input[i]
        }

        return asc
    }

    fun part2(input: List<Int>): Int {
        var asc = 0
        var prev = input[0] + input[1] + input[2]
        for (i in 1..input.size-3) {
            val next = input[i] + input[i+1] + input[i+2]

            if (next > prev) asc++

            prev = next
        }

        return asc
    }

    val input1 = readInput("resources/day01.txt").map { it.toInt() }
    println(part1(input1))

    println(part2(input1))
}
