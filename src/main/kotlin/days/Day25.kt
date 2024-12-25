package days

import days.point.Point2D

class Day25 : Day(25) {

    private fun parseChunk(chunk: String): Set<Point2D> {
        val points: MutableSet<Point2D> = mutableSetOf()
        chunk.lines().mapIndexed { yIndex, line ->
            line.mapIndexed { xIndex, char ->
                if (char != '.') {
                    points.add(Point2D(xIndex.toLong(), yIndex.toLong()))
                }
            }
        }
        return points
    }

    override fun solvePartOne(inputString: String): Any {
        val chunks: List<Set<Point2D>> = inputString.replace("\r", "").split("\n\n")
            .map { parseChunk(it) }

        val locks: List<Set<Point2D>> = chunks.filter { it.contains(Point2D(0, 0)) }
        val keys: List<Set<Point2D>> = chunks.filter { !it.contains(Point2D(0, 0)) }

        return locks.sumOf { lock ->
            keys.count { key ->
                lock.intersect(key).isEmpty()
            }
        }
    }

    override fun solvePartTwo(inputString: String): Any {
        return "No part two!"
    }
}

fun main() {
    val day = Day25()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
