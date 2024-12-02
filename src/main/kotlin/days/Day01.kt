package days

import kotlin.math.abs

class Day01 : Day(1) {

    override fun solvePartOne(inputString: String): Any {
        val list1: MutableList<Int> = mutableListOf()
        val list2: MutableList<Int> = mutableListOf()

        inputString.lines().forEach {
            val parts: List<Int> = it.split("   ").map { it2 -> it2.toInt() }
            list1.add(parts[0])
            list2.add(parts[1])
        }

        list1.sort()
        list2.sort()

        return list1.mapIndexed { index, val1 -> abs(val1 - list2[index]) }.sum()
    }

    override fun solvePartTwo(inputString: String): Any {
        val freq1: MutableMap<Int, Int> = mutableMapOf()
        val freq2: MutableMap<Int, Int> = mutableMapOf()

        inputString.lines().forEach {
            val parts: List<Int> = it.split("   ").map { it2 -> it2.toInt() }
            freq1.compute(parts[0]) { _, value -> (value ?: 0) + 1 }
            freq2.compute(parts[1]) { _, value -> (value ?: 0) + 1 }
        }

        return freq1.map { it.key * it.value * (freq2[it.key] ?: 0) }.sum()
    }
}

fun main() {
    val day = Day01()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
