package days

class Day11 : Day(11) {

    private fun parseInput(inputString: String): Map<String, Long> {
        val stoneValues: MutableMap<String, Long> = mutableMapOf()

        inputString.split(" ").forEach {
            stoneValues.merge(it, 1L, Long::plus)
        }

        return stoneValues
    }

    private fun processStep(stoneValues: Map<String, Long>): Map<String, Long> {
        val newStones: MutableMap<String, Long> = mutableMapOf()

        stoneValues.keys.forEach {
            if (it == "0") {
                newStones.merge("1", stoneValues[it]!!, Long::plus)
            } else if (it.length % 2 == 0) {
                newStones.merge(it.substring(0..<(it.length/2)), stoneValues[it]!!, Long::plus)
                val secondPart: String = it.substring(it.length/2).toLong().toString()
                newStones.merge(secondPart, stoneValues[it]!!, Long::plus)
            } else {
                newStones.merge((it.toLong() * 2024).toString(), stoneValues[it]!!, Long::plus)
            }
        }

        return newStones
    }

    override fun solvePartOne(inputString: String): Any {
        var stoneValues: Map<String, Long> = parseInput(inputString)

        for (step in 1..25) {
            stoneValues = processStep(stoneValues)
        }

        return stoneValues.values.sum()
    }

    override fun solvePartTwo(inputString: String): Any {
        var stoneValues: Map<String, Long> = parseInput(inputString)

        for (step in 1..75) {
            stoneValues = processStep(stoneValues)
        }

        return stoneValues.values.sum()
    }
}

fun main() {
    val day = Day11()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
