package days

import kotlin.math.min

class Day03 : Day(3) {

    private val mulRegex = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")

    override fun solvePartOne(inputString: String): Any {
        return mulRegex.findAll(inputString).sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    }

    override fun solvePartTwo(inputString: String): Any {
        var currentlyEnabled = true
        var total: Long = 0

        for (index: Int in inputString.indices) {
            val substring: String = inputString.substring(index, min(index + 7, inputString.length))

            if (!currentlyEnabled) {
                if (substring.startsWith("do()")) {
                    currentlyEnabled = true
                }
                continue
            }

            if (substring.startsWith("don't()")) {
                currentlyEnabled = false
                continue
            }

            if (mulRegex.matchesAt(inputString, index)) {
                val match: MatchResult = mulRegex.matchAt(inputString, index)!!
                total += match.groupValues[1].toLong() * match.groupValues[2].toLong()
            }
        }

        return total
    }
}

fun main() {
    val day = Day03()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
