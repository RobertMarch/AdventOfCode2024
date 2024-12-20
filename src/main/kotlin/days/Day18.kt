package days

import days.point.Point2D

class Day18 : Day(18) {

    private val directions: List<Point2D> = listOf(
        Point2D(0, 1),
        Point2D(1, 0),
        Point2D(0, -1),
        Point2D(-1, 0),
    )

    private fun getWalls(inputString: String, linesToUse: Int): Set<Point2D> {
        return inputString.lines().take(linesToUse).map {
            val coords: List<Long> = it.split(",").map { coord -> coord.toLong() }
            Point2D(coords[0], coords[1])
        }.toSet()
    }

    fun solvePartOneForNFallenBytes(inputString: String, fallenBytes: Int, maxCoordinate: Long): Long? {
        val walls: Set<Point2D> = getWalls(inputString, fallenBytes)

        val start: Point2D = Point2D(0, 0)
        val end: Point2D = Point2D(maxCoordinate, maxCoordinate)

        val exploredStates: MutableMap<Point2D, Long> = mutableMapOf(
            Pair(start, 0)
        )
        val nextStates: MutableMap<Point2D, Long> = mutableMapOf(Pair(start, 0))

        while (nextStates.isNotEmpty() && !exploredStates.any { it.key == end }) {
            val (position: Point2D, distance: Long) = nextStates.minBy { it.value }
            exploredStates[position] = distance
            nextStates.remove(position)

            directions.map { it.addToPoint(position) }
                .filter { it.x in 0..maxCoordinate && it.y in 0..maxCoordinate }
                .filter { !exploredStates.contains(it) && !walls.contains(it) && nextStates.getOrDefault(it, Long.MAX_VALUE) > distance + 1 }
                .forEach { nextStates[it] = distance + 1 }
        }

        return exploredStates.filterKeys { it == end }.values.minOrNull()
    }

    override fun solvePartOne(inputString: String): Long {
        return solvePartOneForNFallenBytes(inputString, 1024, 70)!!
    }

    private fun solvePartTwo(inputString: String, initialFallenBytes: Int, maxCoordinate: Long): String {
        for (bytes in initialFallenBytes..inputString.lines().size) {
            if (solvePartOneForNFallenBytes(inputString, bytes, maxCoordinate) == null) {
                return inputString.lines()[bytes - 1]
            }
        }
        return "Failed to find answer"
    }

    fun solvePartTwoExample(inputString: String): String {
        return solvePartTwo(inputString, 12, 6)
    }

    override fun solvePartTwo(inputString: String): String {
        return solvePartTwo(inputString, 1024, 70)
    }
}

fun main() {
    val day = Day18()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
