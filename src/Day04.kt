import java.lang.Exception

class Board(private val board: Array<IntArray>) {

    private val locs = HashMap<Int, Pair<Int, Int>>()

    init {
        for (x in board.indices) {
            for (y in board.indices) {
                locs[board[x][y]] = Pair(x, y)
            }
        }
    }

    fun mark(num: Int): Boolean {
        if (locs.containsKey(num)) {
            val coord = locs[num] ?: throw Exception()
            val x = coord.first
            val y = coord.second
            board[x][y] = -1

            // Check for bingo.
            if (board.all { it[y] == -1 }) return true
            if (board[x].all { it == -1 }) return true
        }

        return false
    }

    fun leftOver(): Int {
        return board.sumOf { it.filter { el -> el != -1 }.sum() }
    }
}

fun part1(nums: List<Int>, bs: List<Array<IntArray>>): Int {
    // Make boards.
    val boards = bs.map { Board(it) }

    // Mark off all numbers.
    for (num in nums) {
        for (board in boards) {
            if (board.mark(num)) {
                return board.leftOver() * num
            }
        }
    }

    throw Exception("No bingo found")
}

fun part2(nums: List<Int>, bs: List<Array<IntArray>>): Int {
    // Make boards.
    val boards = bs.map { Board(it) }.toMutableSet()

    // Mark off all numbers.
    for (num in nums) {
        val boardsToCheck = boards.toSet()
        for (board in boardsToCheck) {
            if (!board.mark(num)) continue

            // Board caused a bingo.
            if (boards.size == 1) return board.leftOver() * num
            else boards.remove(board)
        }
    }

    throw Exception("No bingo found")
}

fun main() {
    val input = readInput(
        "resources/day04.txt"
//        "resources/day04small.txt"
    )
    val nums = input[0].split(",").map { it.toInt() }
    var i = 1
    val boards = mutableListOf<Array<IntArray>>()
    while (i < input.size) {
        if (input[i - 1] == "") {
            // Scan all 5 lines and make a 2D board array.
            val board = Array(5) { IntArray(5) }
            for (j in 0 until 5) {
                board[j] = readInts(input[i + j])
            }

            boards.add(board)
        }

        i++
    }

    println(part1(nums, boards))
    println(part2(nums, boards))
}