package days

import kotlin.math.abs
import kotlin.math.sign

class Day02 : Day(2) {

    private fun splitToLevels(line: String): List<Int> {
        return line.split(" ").map { it.toInt() }
    }

    private fun isSafe(levels: List<Int>): Boolean {
        val differences: List<Int> = levels.subList(1, levels.size).mapIndexed { i, v -> v - levels[i] }
        return differences.all { it.sign == differences[0].sign && abs(it) >= 1 && abs(it) <= 3 }
    }

    override fun solvePartOne(inputString: String): Any {
        return inputString.lines().count {
            val levels: List<Int> = splitToLevels(it)
            isSafe(levels)
        }
    }

    override fun solvePartTwo(inputString: String): Any {
        return inputString.lines().count {
            val levels: List<Int> = splitToLevels(it)

            if (isSafe(levels)) {
                return@count true
            }

            for (index in levels.indices) {
                val newLevels: MutableList<Int> = levels.toMutableList()
                newLevels.removeAt(index)

                if (isSafe(newLevels)) {
                    return@count true
                }
            }

            false
        }
    }
}

fun main() {
    val day = Day02()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
