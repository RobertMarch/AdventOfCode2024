package days

import days.point.Point2D

class Day08 : Day(8) {

    private fun parseAntennas(inputString: String): Map<Char, List<Point2D>> {
        val antennas: MutableMap<Char, MutableList<Point2D>> = mutableMapOf()

        inputString.lines().mapIndexed { yIndex, line ->
            line.mapIndexed { xIndex, char ->
                if (char != '.') {
                    antennas.putIfAbsent(char, mutableListOf())
                    antennas[char]?.add(Point2D(xIndex.toLong(), yIndex.toLong()))
                }
            }
        }

        return antennas
    }

    private fun isValid(point: Point2D, xMax: Long, yMax: Long): Boolean {
        return point.x in 0..xMax && point.y in 0..yMax
    }

    private fun solve(inputString: String, partTwo: Boolean): Int {
        val antennas: Map<Char, List<Point2D>> = parseAntennas(inputString)

        val antinodes: MutableSet<Point2D> = mutableSetOf()

        val xMax: Long = inputString.lines()[0].length.toLong() - 1
        val yMax: Long = inputString.lines().size.toLong() - 1

        antennas.values.forEach { antennaPoints ->
            if (partTwo) {
                antinodes.addAll(antennaPoints)
            }

            antennaPoints.forEachIndexed { firstIndex, firstPoint ->
                antennaPoints.subList(firstIndex + 1, antennaPoints.size).forEach { secondPoint ->
                    val direction: Point2D = firstPoint.getPointVectorTo(secondPoint)

                    var newPoint1: Point2D = secondPoint.addToPoint(direction)
                    while (isValid(newPoint1, xMax, yMax)) {
                        antinodes.add(newPoint1)
                        newPoint1 = newPoint1.addToPoint(direction)
                        if (!partTwo) {
                            // For part one, only add one antinode and immediately break
                            break
                        }
                    }

                    var newPoint2: Point2D = firstPoint.addToPoint(direction.scaleBy(-1))
                    while (isValid(newPoint2, xMax, yMax)) {
                        antinodes.add(newPoint2)
                        newPoint2 = newPoint2.addToPoint(direction.scaleBy(-1))
                        if (!partTwo) {
                            // For part one, only add one antinode and immediately break
                            break
                        }
                    }
                }
            }
        }

        return antinodes.size
    }

    override fun solvePartOne(inputString: String): Int {
        return solve(inputString, false)
    }

    override fun solvePartTwo(inputString: String): Int {
        return solve(inputString, true)
    }
}

fun main() {
    val day = Day08()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
