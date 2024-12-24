package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day19Test {

    private var day19: Day19 = Day19()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "r, wr, b, g, bwu, rb, gb, br\n" +
                "\n" +
                "brwrr\n" +
                "bggr\n" +
                "gbbr\n" +
                "rrbgbr\n" +
                "ubwu\n" +
                "bwurrg\n" +
                "brgr\n" +
                "bbrgwb" to 6,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day19.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day19.solvePartOneFromFile()

        assertEquals(265, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "r, wr, b, g, bwu, rb, gb, br\n" +
                "\n" +
                "brwrr\n" +
                "bggr\n" +
                "gbbr\n" +
                "rrbgbr\n" +
                "ubwu\n" +
                "bwurrg\n" +
                "brgr\n" +
                "bbrgwb" to 16L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day19.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day19.solvePartTwoFromFile()

        assertEquals(752461716635602L, result)
    }
}