package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day02Test {

    private var day02: Day02 = Day02()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "7 6 4 2 1\n" +
                "1 2 7 8 9\n" +
                "9 7 6 2 1\n" +
                "1 3 2 4 5\n" +
                "8 6 4 4 1\n" +
                "1 3 6 7 9" to 2,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day02.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day02.solvePartOneFromFile()

        assertEquals(463, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "7 6 4 2 1\n" +
                "1 2 7 8 9\n" +
                "9 7 6 2 1\n" +
                "1 3 2 4 5\n" +
                "8 6 4 4 1\n" +
                "1 3 6 7 9" to 4,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day02.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day02.solvePartTwoFromFile()

        assertEquals(514, result)
    }
}