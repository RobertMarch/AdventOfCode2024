package days

class Day07 : Day(7) {

    private fun parseLine(line: String): Pair<Long, List<Long>> {
        val parts: List<String> = line.split(": ")
        val target: Long = parts[0].toLong()
        val values: List<Long> = parts[1].split(" ").map { it.toLong() }
        return Pair(target, values)
    }

    private fun hasValidSolution(target: Long, values: List<Long>, allowConcatenation: Boolean): Boolean {
        var possibleValues: Set<Long> = setOf(values[0])

        for (value in values.subList(1, values.size)) {
            val nextValues: MutableSet<Long> = mutableSetOf()
            possibleValues.forEach {
                if (it + value <= target) {
                    nextValues.add(it + value)
                }
                if (it * value <= target) {
                    nextValues.add(it * value)
                }
                if (allowConcatenation) {
                    val joined: Long = (it.toString() + value.toString()).toLong()
                    if (joined <= target) {
                        nextValues.add(joined)
                    }
                }
            }
            possibleValues = nextValues
        }

        return possibleValues.contains(target)
    }

    override fun solvePartOne(inputString: String): Any {
        return inputString.lines().sumOf {
            val (target: Long, values: List<Long>) = parseLine(it)
            if (hasValidSolution(target, values, false)) target else 0
        }
    }

    override fun solvePartTwo(inputString: String): Any {
        return inputString.lines().sumOf {
            val (target: Long, values: List<Long>) = parseLine(it)
            if (hasValidSolution(target, values, true)) target else 0
        }
    }
}

fun main() {
    val day = Day07()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
