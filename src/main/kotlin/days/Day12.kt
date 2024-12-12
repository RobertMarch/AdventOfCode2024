package days

import days.point.Point2D

class Day12 : Day(12) {

    private val directions: List<Point2D> = listOf(
        Point2D(0, 1),
        Point2D(1, 0),
        Point2D(0, -1),
        Point2D(-1, 0),
    )

    private fun parseInput(inputString: String): Map<Char, List<Point2D>> {
        val map: MutableMap<Char, MutableList<Point2D>> = mutableMapOf()

        inputString.lines().forEachIndexed { yIndex, line ->
            line.forEachIndexed { xIndex, char ->
                map.putIfAbsent(char, mutableListOf())
                map[char]?.add(Point2D(xIndex.toLong(), yIndex.toLong()))
            }
        }

        return map
    }

    override fun solvePartOne(inputString: String): Long {
        val mapByType: Map<Char, List<Point2D>> = parseInput(inputString)

        var result = 0L

        mapByType.values.forEach { points ->
            val exploredPoints: MutableSet<Point2D> = mutableSetOf()

            var currentRegionArea = 0
            var currentRegionPerimeter = 0
            val nextPoints: MutableSet<Point2D> = mutableSetOf(points[0])

            while (nextPoints.isNotEmpty()) {
                val currPoint: Point2D = nextPoints.first()
                nextPoints.remove(currPoint)
                exploredPoints.add(currPoint)
                currentRegionArea++
                directions.forEach { dir ->
                    val neighbour: Point2D = currPoint.addToPoint(dir)
                    if (!exploredPoints.contains(neighbour)) {
                        if (points.contains(neighbour)) {
                            nextPoints.add(neighbour)
                        } else {
                            currentRegionPerimeter++
                        }
                    }
                }

                if (nextPoints.isEmpty()) {
                    result += currentRegionArea * currentRegionPerimeter
                    val nextRegion: Point2D? = points.find { !exploredPoints.contains(it) }
                    if (nextRegion != null) {
                        currentRegionArea = 0
                        currentRegionPerimeter = 0
                        nextPoints.add(nextRegion)
                    }
                }
            }
        }

        return result
    }

    // Set of directions from each point to the corners (captured as top left corner of point)
    // Weights correspond to each diagonal for later
    private val cornerDirectionsAndWeights: Map<Point2D, Int> = mapOf(
        Pair(Point2D(0, 0), 1),
        Pair(Point2D(1, 1), 1),
        Pair(Point2D(0, 1), 10),
        Pair(Point2D(1, 0), 10),
    )

    private fun getCorners(point: Point2D): Map<Point2D, Int> {
        return cornerDirectionsAndWeights.mapKeys { point.addToPoint(it.key) }
    }

    override fun solvePartTwo(inputString: String): Long {
        val mapByType: Map<Char, List<Point2D>> = parseInput(inputString)

        var result = 0L

        mapByType.values.forEach { points ->
            val exploredPoints: MutableSet<Point2D> = mutableSetOf()

            var currentRegionArea = 0
            val currentRegionCorners: MutableMap<Point2D, Int> = mutableMapOf()
            val nextPoints: MutableSet<Point2D> = mutableSetOf(points[0])

            while (nextPoints.isNotEmpty()) {
                val currPoint: Point2D = nextPoints.first()
                nextPoints.remove(currPoint)
                exploredPoints.add(currPoint)
                currentRegionArea++
                getCorners(currPoint).forEach { (cornerPoint, weight) ->
                    currentRegionCorners.merge(cornerPoint, weight, Int::plus)
                }
                directions.forEach { dir ->
                    val neighbour: Point2D = currPoint.addToPoint(dir)
                    if (!exploredPoints.contains(neighbour) && points.contains(neighbour)) {
                        nextPoints.add(neighbour)
                    }
                }

                if (nextPoints.isEmpty()) {
                    val faces: Int = currentRegionCorners.values.sumOf {
                        when (it) {
                            0, 11, 22 -> 0 // Straight edge, or fully contained - no corners
                            1, 10, 12, 21 -> 1 // Internal or external corners - adds one corner
                            2, 20 -> 2 // Opposite internal corners - adds two corners
                            else -> throw IllegalStateException("Unexpected corner state: $it")
                        }.toInt()
                    }
                    result += currentRegionArea * faces

                    val nextRegion: Point2D? = points.find { !exploredPoints.contains(it) }
                    if (nextRegion != null) {
                        currentRegionArea = 0
                        currentRegionCorners.clear()
                        nextPoints.add(nextRegion)
                    }
                }
            }
        }

        return result
    }
}

fun main() {
    val day = Day12()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
