package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day14Test {

    private var day14: Day14 = Day14()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "p=0,4 v=3,-3\n" +
                "p=6,3 v=-1,-3\n" +
                "p=10,3 v=-1,2\n" +
                "p=2,0 v=2,-1\n" +
                "p=0,0 v=1,3\n" +
                "p=3,0 v=-2,-2\n" +
                "p=7,6 v=-1,-3\n" +
                "p=3,0 v=-1,-2\n" +
                "p=9,3 v=2,3\n" +
                "p=7,3 v=-1,2\n" +
                "p=2,4 v=2,-3\n" +
                "p=9,5 v=-3,-3" to 12L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day14.solvePartOneExample(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day14.solvePartOneFromFile()

        assertEquals(208437768L, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        // No examples given
        "p=0,4 v=3,-3" to -1,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day14.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day14.solvePartTwoFromFile()

        assertEquals(7492L, result)
    }
}