package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day17Test {

    private var day17: Day17 = Day17()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "Register A: 10\n" +
                "Register B: 0\n" +
                "Register C: 0\n" +
                "\n" +
                "Program: 5,0,5,1,5,4" to "0,1,2",
        "Register A: 2024\n" +
                "Register B: 0\n" +
                "Register C: 0\n" +
                "\n" +
                "Program: 0,1,5,4,3,0" to "4,2,5,6,7,7,7,7,3,1,0",
        "Register A: 729\n" +
                "Register B: 0\n" +
                "Register C: 0\n" +
                "\n" +
                "Program: 0,1,5,4,3,0" to "4,6,3,5,6,3,5,2,1,0",
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day17.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day17.solvePartOneFromFile()

        assertEquals("5,1,4,0,5,1,0,2,6", result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "Register A: 2024\n" +
                "Register B: 0\n" +
                "Register C: 0\n" +
                "\n" +
                "Program: 0,3,5,4,3,0" to 117440L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day17.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day17.solvePartTwoFromFile()

        assertEquals(202322936867370L, result)
    }
}