package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day11Test {

    private var day11: Day11 = Day11()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "125 17" to 55312L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day11.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day11.solvePartOneFromFile()

        assertEquals(197357L, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "0" to 22938365706844, // Note test cases not given in text
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day11.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day11.solvePartTwoFromFile()

        assertEquals(234568186890978L, result)
    }
}