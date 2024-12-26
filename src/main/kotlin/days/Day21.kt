package days

import days.point.Point2D
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day21 : Day(21) {

    private val positions: Map<Char, Point2D> = mapOf(
        Pair('A', Point2D(0, 0)),
        Pair('0', Point2D(-1, 0)),
        Pair('1', Point2D(-2, 1)),
        Pair('2', Point2D(-1, 1)),
        Pair('3', Point2D(0, 1)),
        Pair('4', Point2D(-2, 2)),
        Pair('5', Point2D(-1, 2)),
        Pair('6', Point2D(0, 2)),
        Pair('7', Point2D(-2, 3)),
        Pair('8', Point2D(-1, 3)),
        Pair('9', Point2D(0, 3)),
        Pair('<', Point2D(-2, -1)),
        Pair('^', Point2D(-1, 0)),
        Pair('>', Point2D(0, -1)),
        Pair('v', Point2D(-1, -1)),
    )

    private val possibleCommands: List<String> = listOf(
        "A",

        ">A",
        "<A",
        "^A",
        "vA",

        ">vA",
        "v>A",

        ">^A",
        "^>A",

        "<vA",
        "v<A",

        "<^A",
        "^<A",
    )

    private fun calculateCostsForNextLayerCommands(previousLayerButtonPressCounts: Map<String, Long>, nextCommandsInputs: List<String>): Map<String, Long> {
        return nextCommandsInputs.associateWith { command ->
            var prev: Point2D = Point2D(0, 0)

            command.sumOf { buttonToPress ->
                val current: Point2D = positions[buttonToPress]!!
                val previous: Point2D = prev
                val vector: Point2D = previous.getPointVectorTo(current)
                prev = current

                val horizontalChange: String = when(vector.x.sign) {
                    -1 -> "<"
                    0 -> ""
                    1 -> ">"
                    else -> throw IllegalStateException("Unknown sign")
                }
                val verticalChange: String = when(vector.y.sign) {
                    -1 -> "v"
                    0 -> ""
                    1 -> "^"
                    else -> throw IllegalStateException("Unknown sign")
                }
                val repeatPressCount: Int = (vector.x.absoluteValue.toInt() - 1).coerceAtLeast(0) + (vector.y.absoluteValue.toInt() - 1).coerceAtLeast(0)

                val results: MutableSet<String> = mutableSetOf()

                if (!(previous.x == -2L && current.y == 0L)) {
                    results.add(verticalChange + horizontalChange + "A")
                }
                if (!(previous.y == 0L && current.x == -2L)) {
                    results.add(horizontalChange + verticalChange + "A")
                }

                results.minOf { previousLayerButtonPressCounts[it]!! } + repeatPressCount
            }
        }
    }

    private fun solveForNLayers(inputString: String, robotLayers: Int): Long {
        var buttonPressCosts: Map<String, Long> = possibleCommands.associateWith { it.length.toLong() }

        for (i in 1..robotLayers) {
            buttonPressCosts = calculateCostsForNextLayerCommands(buttonPressCosts, possibleCommands)
        }

        val outputCodeButtonPressCosts: Map<String, Long> = calculateCostsForNextLayerCommands(buttonPressCosts, inputString.lines())

        return outputCodeButtonPressCosts.entries.sumOf { it.key.substring(0..2).toLong() * it.value }
    }

    override fun solvePartOne(inputString: String): Long {
        return solveForNLayers(inputString, 2)
    }

    override fun solvePartTwo(inputString: String): Long {
        return solveForNLayers(inputString, 25)
    }
}

fun main() {
    val day = Day21()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
