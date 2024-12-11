package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day10Test {

    private var day10: Day10 = Day10()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "0123\n" +
                "1234\n" +
                "8765\n" +
                "9876" to 1,
        "89010123\n" +
                "78121874\n" +
                "87430965\n" +
                "96549874\n" +
                "45678903\n" +
                "32019012\n" +
                "01329801\n" +
                "10456732" to 36,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day10.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day10.solvePartOneFromFile()

        assertEquals(667, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "7777707\n" +
                "7743217\n" +
                "7757727\n" +
                "7765437\n" +
                "7777747\n" +
                "7787657\n" +
                "7797777" to 3L,
        "89010123\n" +
                "78121874\n" +
                "87430965\n" +
                "96549874\n" +
                "45678903\n" +
                "32019012\n" +
                "01329801\n" +
                "10456732" to 81L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day10.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day10.solvePartTwoFromFile()

        assertEquals(1344L, result)
    }
}