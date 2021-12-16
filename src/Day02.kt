fun main() {
    fun part1(instructions: List<String>): Int {
        var x = 0
        var y = 0

        for (inst in instructions) {
            val (comm, value) = inst.split(' ')
            val v = value.toInt()
            when(comm) {
                "forward" -> x += v
                "down" -> y += v
                "up" -> y -= v
            }
        }

        return x * y
    }

    fun part2(instructions: List<String>): Int {
        var x = 0
        var y = 0
        var aim = 0

        for (inst in instructions) {
            val (comm, value) = inst.split(' ')
            val v = value.toInt()
            when(comm) {
                "forward" -> {
                    x += v
                    y += aim * v
                }
                "down" -> aim += v
                "up" -> aim -= v
            }
        }

        return x * y
    }

    val input = readInput("resources/day02.txt")
    println(part1(input))
    println(part2(input))
}