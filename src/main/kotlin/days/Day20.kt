package days

import days.point.Point2D
import jdk.jfr.Threshold

class Day20 : Day(20) {

    private val directions: List<Point2D> = listOf(
        Point2D(0, 1),
        Point2D(1, 0),
        Point2D(0, -1),
        Point2D(-1, 0),
    )

    private val cheatDirections: List<Point2D> = directions.map { it.scaleBy(2) }

    private fun parseState(inputString: String): Triple<Point2D, Point2D, Set<Point2D>> {
        val paths: MutableSet<Point2D> = mutableSetOf()
        var start: Point2D? = null
        var end: Point2D? = null

        inputString.lines().forEachIndexed { yIndex, line ->
            line.forEachIndexed { xIndex, char ->
                if (char != '#') {
                    val point: Point2D = Point2D(xIndex.toLong(), yIndex.toLong())
                    paths.add(point)
                    if (char == 'S') {
                        start = point
                    }
                    if (char == 'E') {
                        end = point
                    }
                }
            }
        }

        return Triple(start!!, end!!, paths)
    }

    private fun getDistanceAlongPathToPoints(inputString: String): Map<Point2D, Long> {
        val (start: Point2D, end: Point2D, paths: Set<Point2D>) = parseState(inputString)

        val exploredStates: MutableMap<Point2D, Long> = mutableMapOf(
            Pair(start, 0)
        )

        var position: Point2D = start
        var distance: Long = 0

        while (position != end) {
            val nextPoints: List<Point2D> = directions.map { it.addToPoint(position) }
                .filter { !exploredStates.contains(it) && paths.contains(it) }

            if (nextPoints.size != 1) {
                throw IllegalStateException("Found more than one possible next point from: $position")
            }

            position = nextPoints[0]
            distance++
            exploredStates[position] = distance
        }

        return exploredStates
    }

    fun solvePartOneWithThreshold(inputString: String, threshold: Long): Int {
        val exploredStates: Map<Point2D, Long> = getDistanceAlongPathToPoints(inputString)

        return exploredStates.entries.sumOf { entry ->
            cheatDirections.map { it.addToPoint(entry.key) }
                .count { exploredStates.getOrDefault(it, 0L) - entry.value - 2 >= threshold }
        }
    }

    override fun solvePartOne(inputString: String): Any {
        return solvePartOneWithThreshold(inputString, 100L)
    }

    fun solvePartTwoWithThreshold(inputString: String, threshold: Long): Int {
        val exploredStates: Map<Point2D, Long> = getDistanceAlongPathToPoints(inputString)

        return exploredStates.entries.sumOf { entry ->
            exploredStates.entries
                .count { other ->
                    val cheatPathLength: Long = other.key.getPointVectorTo(entry.key).manhattenDist()
                    val cheatSaving: Long = other.value - entry.value - cheatPathLength

                    cheatPathLength <= 20 && cheatSaving >= threshold
                }
        }
    }

    override fun solvePartTwo(inputString: String): Any {
        return solvePartTwoWithThreshold(inputString, 100L)
    }
}

fun main() {
    val day = Day20()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
