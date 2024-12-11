package days

import days.point.Point2D

class Day10 : Day(10) {

    private val directions: List<Point2D> = listOf(
        Point2D(0, 1),
        Point2D(1, 0),
        Point2D(0, -1),
        Point2D(-1, 0),
    )

    private fun parseInput(inputString: String): Map<Int, List<Point2D>> {
        val map: MutableMap<Int, MutableList<Point2D>> = mutableMapOf()

        inputString.lines().forEachIndexed { yIndex, line ->
            line.forEachIndexed { xIndex, char ->
                val digit: Int = char.digitToInt()
                map.putIfAbsent(digit, mutableListOf())
                map[digit]?.add(Point2D(xIndex.toLong(), yIndex.toLong()))
            }
        }

        return map
    }

    override fun solvePartOne(inputString: String): Int {
        val map: Map<Int, List<Point2D>> = parseInput(inputString)

        var reachableFromPointsMap: MutableMap<Point2D, MutableSet<Point2D>> = mutableMapOf()
        map[0]?.forEach { reachableFromPointsMap[it] = mutableSetOf(it) }

        for (currentHeight in 1..9) {
            val newReachablePointsMap: MutableMap<Point2D, MutableSet<Point2D>> = mutableMapOf()

            val currentHeightPoints: List<Point2D> = map[currentHeight]!!

            currentHeightPoints.forEach { point ->
                directions.forEach { dir ->
                    val neighbour: Point2D = point.addToPoint(dir)
                    if (reachableFromPointsMap.containsKey(neighbour)) {
                        newReachablePointsMap.putIfAbsent(point, mutableSetOf())
                        newReachablePointsMap[point]?.addAll(reachableFromPointsMap[neighbour]!!)
                    }
                }
            }

            reachableFromPointsMap = newReachablePointsMap
        }

        return reachableFromPointsMap.values.sumOf { it.size }
    }

    override fun solvePartTwo(inputString: String): Any {
        val map: Map<Int, List<Point2D>> = parseInput(inputString)

        var reachableFromPointsMap: MutableMap<Point2D, Long> = mutableMapOf()
        map[0]?.forEach { reachableFromPointsMap[it] = 1 }

        for (currentHeight in 1..9) {
            val newReachablePointsMap: MutableMap<Point2D, Long> = mutableMapOf()

            val currentHeightPoints: List<Point2D> = map[currentHeight]!!

            currentHeightPoints.forEach { point ->
                directions.forEach { dir ->
                    val neighbour: Point2D = point.addToPoint(dir)
                    if (reachableFromPointsMap.containsKey(neighbour)) {
                        newReachablePointsMap.putIfAbsent(point, 0L)
                        newReachablePointsMap[point] = newReachablePointsMap[point]!! + reachableFromPointsMap[neighbour]!!
                    }
                }
            }

            reachableFromPointsMap = newReachablePointsMap
        }

        return reachableFromPointsMap.values.sum()
    }
}

fun main() {
    val day = Day10()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
