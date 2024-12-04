package days

import days.point.Point2D

class Day04 : Day(4) {

    private fun getPoints(inputString: String): Map<Point2D, Char> {
        val map: MutableMap<Point2D, Char> = mutableMapOf()

        inputString.lines().flatMapIndexed { lineIndex: Int, line: String ->
            line.mapIndexed { charIndex, char ->
                map.put(Point2D(charIndex.toLong(), lineIndex.toLong()), char)
            }
        }

        return map
    }

    private fun countXmasFromPoint(point: Point2D, charMap: Map<Point2D, Char>): Int {
        if (charMap[point] != 'X') {
            return 0
        }
        var count = 0
        for (dx: Long in -1L..1L) {
            for (dy: Long in -1L..1L) {
                val direction = Point2D(dx, dy)

                val mLocation: Point2D = direction.addToPoint(point)
                val aLocation: Point2D = direction.scaleBy(2).addToPoint(point)
                val sLocation: Point2D = direction.scaleBy(3).addToPoint(point)

                if (charMap[mLocation] == 'M' && charMap[aLocation] == 'A' && charMap[sLocation] == 'S') {
                    count++
                }
            }
        }
        return count
    }

    override fun solvePartOne(inputString: String): Any {
        val charMap: Map<Point2D, Char> = getPoints(inputString)

        return charMap.keys.sumOf {
            countXmasFromPoint(it, charMap)
        }
    }

    private val diagonals: List<Point2D> = listOf(
        Point2D(1, 1),
        Point2D(1, -1),
    )

    private fun isCentreOfXMas(point: Point2D, charMap: Map<Point2D, Char>): Boolean {
        if (charMap[point] != 'A') {
            return false
        }

        return diagonals.all {
            val charsOnDiagonal: Set<Char?> = setOf(
                charMap[point.addToPoint(it)],
                charMap[point.addToPoint(it.scaleBy(-1))]
            )
            charsOnDiagonal.containsAll(setOf('M', 'S'))
        }
    }

    override fun solvePartTwo(inputString: String): Any {
        val charMap: Map<Point2D, Char> = getPoints(inputString)

        return charMap.keys.count {
            isCentreOfXMas(it, charMap)
        }
    }
}

fun main() {
    val day = Day04()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
