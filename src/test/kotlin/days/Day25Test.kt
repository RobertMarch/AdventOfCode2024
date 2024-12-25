package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day25Test {

    private var day25: Day25 = Day25()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "#####\n" +
                ".####\n" +
                ".####\n" +
                ".####\n" +
                ".#.#.\n" +
                ".#...\n" +
                ".....\n" +
                "\n" +
                "#####\n" +
                "##.##\n" +
                ".#.##\n" +
                "...##\n" +
                "...#.\n" +
                "...#.\n" +
                ".....\n" +
                "\n" +
                ".....\n" +
                "#....\n" +
                "#....\n" +
                "#...#\n" +
                "#.#.#\n" +
                "#.###\n" +
                "#####\n" +
                "\n" +
                ".....\n" +
                ".....\n" +
                "#.#..\n" +
                "###..\n" +
                "###.#\n" +
                "###.#\n" +
                "#####\n" +
                "\n" +
                ".....\n" +
                ".....\n" +
                ".....\n" +
                "#....\n" +
                "#.#..\n" +
                "#.#.#\n" +
                "#####" to 3,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day25.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day25.solvePartOneFromFile()

        assertEquals(3365, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "No part two" to "No part two!",
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day25.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day25.solvePartTwoFromFile()

        assertEquals("No part two!", result)
    }
}