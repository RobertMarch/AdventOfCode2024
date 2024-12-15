package days

import days.point.Point2D
import kotlin.math.sign

class Day14 : Day(14) {

    private val inputRegex = Regex("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)")

    private class Robot(var position: Point2D, val velocity: Point2D) {
        fun updatePosition(width: Long, height: Long) {
            val newPosition = position.addToPoint(velocity).addToPoint(Point2D(width, height))
            position = Point2D(newPosition.x % width, newPosition.y % height)
        }
    }

    private fun parseLine(line: String): Robot {
        val values: List<Long> = inputRegex.find(line)!!.groupValues.drop(1).map { it.toLong() }
        return Robot(Point2D(values[0], values[1]), Point2D(values[2], values[3]))
    }

    private fun solvePartOneWithSize(inputString: String, width: Long, height: Long): Long {
        val quadrantCount: MutableMap<Int, Long> = mutableMapOf()

        val middleWidth: Long = width / 2
        val middleHeight: Long = height / 2

        inputString.lines().forEach { line ->
            val robot: Robot = parseLine(line)
            val finalPosition: Point2D = robot.position.addToPoint(robot.velocity.scaleBy(100))
            val finalX: Long = ((finalPosition.x % width) + width) % width
            val finalY: Long = ((finalPosition.y % height) + height) % height

            val topBottomPart: Int = (finalX - middleWidth).sign
            val leftRightPart: Int = (finalY - middleHeight).sign * 10

            if (topBottomPart != 0 && leftRightPart != 0) {
                quadrantCount.merge(topBottomPart + leftRightPart, 1, Long::plus)
            }
        }

        return quadrantCount.values.reduce { acc, l -> acc * l }
    }

    fun solvePartOneExample(inputString: String): Long {
        return solvePartOneWithSize(inputString, 11, 7)
    }

    override fun solvePartOne(inputString: String): Long {
        return solvePartOneWithSize(inputString, 101, 103)
    }

    /**
     * Helper method to print the data within a constructed map using the given mapping function for each value.
     */
    private fun printRobots(points: List<Point2D>, width: Long, height: Long) {
        val result: MutableList<Char> = mutableListOf()

        for (y in 0..height) {
            for (x in 0..width) {
                val point = Point2D(x, y)
                if (points.contains(point)) {
                    result.add('#')
                } else {
                    result.add(' ')
                }
            }
            result.add('\n')
        }

        println(result.joinToString(""))
    }

    override fun solvePartTwo(inputString: String): Any {
        val width: Long = 101
        val height: Long = 103

        val robots: List<Robot> = inputString.lines().map { parseLine(it) }

        val initialSteps: Long = 7000
        val printTo: Long = 8000

        val verticalStart: Long = 18
        val verticalFreq: Long = 101
        val horizontalStart: Long = 76
        val horizontalFreq: Long = 103

        for (i in 0..printTo) {
            if (i > initialSteps && ((i - verticalStart) % verticalFreq == 0L || (i - horizontalStart) % horizontalFreq == 0L)) {
                println("\nAfter $i steps:")
                printRobots(robots.map { it.position }, width, height)
            }
            robots.forEach { it.updatePosition(width, height) }
        }

        return -1
    }
}

fun main() {
    val day = Day14()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
