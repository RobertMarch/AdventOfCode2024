package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day04Test {

    private var day04: Day04 = Day04()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "..X...\n" +
                ".SAMX.\n" +
                ".A..A.\n" +
                "XMAS.S\n" +
                ".X...." to 4,
        "MMMSXXMASM\n" +
                "MSAMXMSMSA\n" +
                "AMXSXMAAMM\n" +
                "MSAMASMSMX\n" +
                "XMASAMXAMM\n" +
                "XXAMMXXAMA\n" +
                "SMSMSASXSS\n" +
                "SAXAMASAAA\n" +
                "MAMMMXMMMM\n" +
                "MXMXAXMASX" to 18,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day04.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day04.solvePartOneFromFile()

        assertEquals(2378, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "M.S\n" +
                ".A.\n" +
                "M.S" to 1,
        "MMMSXXMASM\n" +
                "MSAMXMSMSA\n" +
                "AMXSXMAAMM\n" +
                "MSAMASMSMX\n" +
                "XMASAMXAMM\n" +
                "XXAMMXXAMA\n" +
                "SMSMSASXSS\n" +
                "SAXAMASAAA\n" +
                "MAMMMXMMMM\n" +
                "MXMXAXMASX" to 9,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day04.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day04.solvePartTwoFromFile()

        assertEquals(1796, result)
    }
}