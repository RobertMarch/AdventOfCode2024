package days

class Day19 : Day(19) {

    private fun getWaysToArrangeTowels(target: String, towels: List<String>): Long {
        val reachableIndexes: MutableList<Long> = target.map { 0L }.toMutableList()
        reachableIndexes.add(0, 1L)
        var index = 0

        while (index < target.length) {
            val reachableAtIndex: Long = reachableIndexes[index]
            if (reachableAtIndex > 0) {
                towels.filter { target.startsWith(it, index) }
                    .forEach { reachableIndexes[index + it.length] += reachableAtIndex }
            }
            index++
        }

        return reachableIndexes.last()
    }

    override fun solvePartOne(inputString: String): Any {
        val parts: List<String> = inputString.replace("\r", "").split("\n\n")
        val towels: List<String> = parts[0].split(", ")
        val targetPatterns: List<String> = parts[1].lines()

        return targetPatterns.count {
            getWaysToArrangeTowels(it, towels) > 0
        }
    }

    override fun solvePartTwo(inputString: String): Any {
        val parts: List<String> = inputString.replace("\r", "").split("\n\n")
        val towels: List<String> = parts[0].split(", ")
        val targetPatterns: List<String> = parts[1].lines()

        return targetPatterns.sumOf {
            getWaysToArrangeTowels(it, towels)
        }
    }
}

fun main() {
    val day = Day19()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
