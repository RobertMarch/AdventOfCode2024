package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day03Test {

    private var day03: Day03 = Day03()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))" to 161,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day03.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day03.solvePartOneFromFile()

        assertEquals(189527826, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))" to 48L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day03.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day03.solvePartTwoFromFile()

        assertEquals(63013756L, result)
    }
}