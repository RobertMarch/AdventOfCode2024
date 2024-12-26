package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day21Test {

    private var day21: Day21 = Day21()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "029A\n" +
                "980A\n" +
                "179A\n" +
                "456A\n" +
                "379A" to 126384L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day21.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day21.solvePartOneFromFile()

        assertEquals(171596L, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "029A\n" +
                "980A\n" +
                "179A\n" +
                "456A\n" +
                "379A" to 154115708116294L, // Calculated output of program
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day21.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day21.solvePartTwoFromFile()

        assertEquals(209268004868246L, result)
    }
}