package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class DayXXTest {

    private var dayXX: DayXX = DayXX()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "input" to 1,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = dayXX.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = dayXX.solvePartOneFromFile()

        assertEquals(1, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "input" to 1,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = dayXX.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = dayXX.solvePartTwoFromFile()

        assertEquals(1, result)
    }
}