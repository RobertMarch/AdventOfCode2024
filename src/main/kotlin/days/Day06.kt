package days

import days.point.Point2D

class Day06 : Day(6) {

    private val directions: Map<Int, Point2D> = mapOf(
        Pair(0, Point2D(1, 0)),
        Pair(1, Point2D(0, 1)),
        Pair(2, Point2D(-1, 0)),
        Pair(3, Point2D(0, -1)),
    )

    private fun parseInput(inputString: String): Triple<List<Point2D>, Point2D, Int> {
        val walls: MutableList<Point2D> = mutableListOf()
        var position: Point2D? = null
        var direction: Int? = null

        inputString.lines().mapIndexed { yIndex, line ->
            line.mapIndexed { xIndex, char ->
                when (char) {
                    '#' -> walls.add(Point2D(xIndex.toLong(), yIndex.toLong()))
                    '^' -> {
                        position = Point2D(xIndex.toLong(), yIndex.toLong())
                        direction = 3
                    }
                    '>' -> {
                        position = Point2D(xIndex.toLong(), yIndex.toLong())
                        direction = 0
                    }
                    'v' -> {
                        position = Point2D(xIndex.toLong(), yIndex.toLong())
                        direction = 1
                    }
                    '<' -> {
                        position = Point2D(xIndex.toLong(), yIndex.toLong())
                        direction = 2
                    }
                    else -> {}
                }
            }
        }

        return Triple(walls, position!!, direction!!)
    }

    private fun isPositionInGrid(position: Point2D, maxY: Int, maxX: Int): Boolean {
        return position.x in 0..maxX && position.y in 0..maxY
    }

    private fun walkPath(walls: List<Point2D>, initialPosition: Point2D, initialDirection: Int, maxY: Int, maxX: Int): Pair<Boolean, Set<Point2D>> {
        var position: Point2D = initialPosition
        var direction: Int = initialDirection
        val visitedPoints: MutableSet<Pair<Point2D, Int>> = mutableSetOf(Pair(position, direction))

        while (isPositionInGrid(position, maxY, maxX)) {
            val nextPosition: Point2D = position.addToPoint(directions[direction]!!)

            if (visitedPoints.contains(Pair(nextPosition, direction))) {
                return Pair(true, visitedPoints.map { it.first }.toSet())
            }

            if (walls.contains(nextPosition)) {
                direction = (direction + 1) % 4
                continue
            }

            visitedPoints.add(Pair(position, direction))
            position = nextPosition
        }
        return Pair(false, visitedPoints.map { it.first }.toSet())
    }

    override fun solvePartOne(inputString: String): Any {
        val (walls: List<Point2D>, initialPosition: Point2D, initialDirection: Int) = parseInput(inputString)

        val maxY: Int = inputString.lines().size - 1
        val maxX: Int = inputString.lines()[0].length - 1

        return walkPath(walls, initialPosition, initialDirection, maxY, maxX).second.size
    }

    override fun solvePartTwo(inputString: String): Any {
        val (walls: List<Point2D>, initialPosition: Point2D, initialDirection: Int) = parseInput(inputString)
        val maxY: Int = inputString.lines().size - 1
        val maxX: Int = inputString.lines()[0].length - 1

        val visitedPositions: Set<Point2D> = walkPath(walls, initialPosition, initialDirection, maxY, maxX).second

        return visitedPositions.count {
            if (initialPosition == it) {
                return@count false
            }

            val newWalls: MutableList<Point2D> = walls.toMutableList()
            newWalls.add(it)
            walkPath(newWalls, initialPosition, initialDirection, maxY, maxX).first
        }
    }
}

fun main() {
    val day = Day06()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
