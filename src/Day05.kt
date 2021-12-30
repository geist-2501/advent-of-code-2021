import kotlin.math.max

/**
 * Similar to a vector, except it has magnitude, direction *and* position.
 */
class Line(val p1: Point, val p2: Point) {
    fun isHorizontal(): Boolean {
        return p1.y == p2.y && p1.x != p2.x
    }

    fun isVertical(): Boolean {
        return p1.x == p2.x && p1.y != p2.y
    }

    fun isDiagonal(): Boolean {
        return !(isHorizontal() || isVertical())
    }

    private fun getVerticalDir(): Int {
        if (isHorizontal()) return 0

        return if (p1.y < p2.y) 1 else -1
    }

    private fun getHorizontalDir(): Int {
        if (isVertical()) return 0

        return if (p1.x < p2.x) 1 else -1
    }

    fun trace(board: Matrix) {
        val moveX = getHorizontalDir()
        val moveY = getVerticalDir()
        val m = Point(moveX, moveY)
        val p = p1.copy()
        do {
            board[p.x, p.y] += 1
            p.add(m)
        } while (p != p2)
        board[p.x, p.y] += 1
    }
}

fun part1(lines: List<Line>, board: Matrix): Int {
    val filteredLines = lines.filter { it.isVertical() || it.isHorizontal() }
    for (line in filteredLines) {
        line.trace(board)
    }

    var overlaps = 0
    for (i in board.xIdx) {
        for (j in board.yIdx) {
            if (board[i,j] > 1) overlaps++
        }
    }

    return overlaps
}

fun part2(lines: List<Line>, board: Matrix): Int {
    for (line in lines) {
        line.trace(board)
    }

    var overlaps = 0
    for (i in board.xIdx) {
        for (j in board.yIdx) {
            if (board[i,j] > 1) overlaps++
        }
    }

    return overlaps
}

fun main() {
    val data = readInput(
//        "resources/day05small.txt"
        "resources/day05.txt"
    )

    val lines = mutableListOf<Line>()
    var maxX = 0
    var maxY = 0
    for (line in data) {
        val coords = line.split("->")
        val p1 = readPoint(coords.first())
        val p2 = readPoint(coords.last())
        maxX = max(maxX, max(p1.x, p2.x))
        maxY = max(maxY, max(p1.y, p2.y))
        lines.add(Line(p1, p2))
    }

    val board1 = Matrix(maxX + 1, maxY + 1)
    println(part1(lines, board1))
    val board2 = Matrix(maxX + 1, maxY + 1)
    println(part2(lines, board2))
}
