package days

class Day05 : Day(5) {

    private fun getRules(rulesString: String): Map<Int, Set<Int>> {
        val result: MutableMap<Int, MutableSet<Int>> = mutableMapOf()

        rulesString.lines().forEach { line ->
            val parts: List<Int> = line.split("|").map { it.toInt() }
            result.putIfAbsent(parts[0], mutableSetOf())
            result[parts[0]]?.add(parts[1])
        }

        return result
    }

    private fun isValid(update: List<Int>, rulesMap: Map<Int, Set<Int>>): Boolean {
        val previousValues: MutableSet<Int> = mutableSetOf(update[0])
        for (i in 1..<update.size) {
            val current: Int = update[i]
            if (previousValues.intersect(rulesMap.getOrDefault(current, setOf())).isNotEmpty()) {
                return false
            }
            previousValues.add(current)
        }
        return true
    }

    override fun solvePartOne(inputString: String): Any {
        val parts: List<String> = inputString.replace("\r\n", "\n").split("\n\n")

        val rules: Map<Int, Set<Int>> = getRules(parts[0])
        val updates: List<List<Int>> = parts[1].lines().map { line -> line.split(",").map { it.toInt() } }

        return updates
            .filter { isValid(it, rules) }
            .sumOf { it[(it.size - 1) / 2] }
    }

    private fun fixOrder(update: List<Int>, rulesMap: Map<Int, Set<Int>>): List<Int> {
        val originalList: MutableList<Int> = update.toMutableList()

        val reorderedList: MutableList<Int> = mutableListOf()

        while (originalList.isNotEmpty()) {
            for (i in originalList.indices) {
                val current: Int = originalList[i]

                if (!rulesMap.containsKey(current) || rulesMap[current]?.intersect(originalList.toSet())?.isEmpty() == true) {
                    reorderedList.add(0, current)
                    originalList.remove(current)
                    break
                }
            }
        }

        return reorderedList
    }

    override fun solvePartTwo(inputString: String): Any {
        val parts: List<String> = inputString.replace("\r\n", "\n").split("\n\n")

        val rules: Map<Int, Set<Int>> = getRules(parts[0])
        val updates: List<List<Int>> = parts[1].lines().map { line -> line.split(",").map { it.toInt() } }

        return updates
            .filter { !isValid(it, rules) }
            .map { fixOrder(it, rules) }
            .sumOf { it[(it.size - 1) / 2] }
    }
}

fun main() {
    val day = Day05()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
