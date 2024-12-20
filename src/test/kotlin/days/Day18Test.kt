package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day18Test {

    private var day18: Day18 = Day18()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "5,4\n" +
                "4,2\n" +
                "4,5\n" +
                "3,0\n" +
                "2,1\n" +
                "6,3\n" +
                "2,4\n" +
                "1,5\n" +
                "0,6\n" +
                "3,3\n" +
                "2,6\n" +
                "5,1\n" +
                "1,2\n" +
                "5,5\n" +
                "2,5\n" +
                "6,5\n" +
                "1,4\n" +
                "0,4\n" +
                "6,4\n" +
                "1,1\n" +
                "6,1\n" +
                "1,0\n" +
                "0,5\n" +
                "1,6\n" +
                "2,0" to 22L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day18.solvePartOneForNFallenBytes(input, 12, 6)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day18.solvePartOneFromFile()

        assertEquals(1, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "5,4\n" +
                "4,2\n" +
                "4,5\n" +
                "3,0\n" +
                "2,1\n" +
                "6,3\n" +
                "2,4\n" +
                "1,5\n" +
                "0,6\n" +
                "3,3\n" +
                "2,6\n" +
                "5,1\n" +
                "1,2\n" +
                "5,5\n" +
                "2,5\n" +
                "6,5\n" +
                "1,4\n" +
                "0,4\n" +
                "6,4\n" +
                "1,1\n" +
                "6,1\n" +
                "1,0\n" +
                "0,5\n" +
                "1,6\n" +
                "2,0" to "6,1",
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day18.solvePartTwoExample(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day18.solvePartTwoFromFile()

        assertEquals("20,12", result)
    }
}