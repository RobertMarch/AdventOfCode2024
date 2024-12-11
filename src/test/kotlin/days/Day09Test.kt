package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day09Test {

    private var day09: Day09 = Day09()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "2333133121414131402" to 1928L,
        "123" to 6L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day09.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day09.solvePartOneFromFile()

        assertEquals(6471961544878L, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "2333133121414131402" to 2858L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day09.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day09.solvePartTwoFromFile()

        assertEquals(6511178035564L, result)
    }
}