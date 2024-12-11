package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day08Test {

    private var day08: Day08 = Day08()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "............\n" +
                "........0...\n" +
                ".....0......\n" +
                ".......0....\n" +
                "....0.......\n" +
                "......A.....\n" +
                "............\n" +
                "............\n" +
                "........A...\n" +
                ".........A..\n" +
                "............\n" +
                "............" to 14,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day08.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day08.solvePartOneFromFile()

        assertEquals(252, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "T.........\n" +
                "...T......\n" +
                ".T........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                ".........." to 9,
        "............\n" +
                "........0...\n" +
                ".....0......\n" +
                ".......0....\n" +
                "....0.......\n" +
                "......A.....\n" +
                "............\n" +
                "............\n" +
                "........A...\n" +
                ".........A..\n" +
                "............\n" +
                "............" to 34,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day08.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day08.solvePartTwoFromFile()

        assertEquals(839, result)
    }
}