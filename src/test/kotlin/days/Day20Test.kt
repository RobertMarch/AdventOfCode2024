package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day20Test {

    private var day20: Day20 = Day20()

    private val exampleInput: String = "###############\n" +
            "#...#...#.....#\n" +
            "#.#.#.#.#.###.#\n" +
            "#S#...#.#.#...#\n" +
            "#######.#.#.###\n" +
            "#######.#.#...#\n" +
            "#######.#.###.#\n" +
            "###..E#...#...#\n" +
            "###.#######.###\n" +
            "#...###...#...#\n" +
            "#.#####.#.###.#\n" +
            "#.#...#.#.#...#\n" +
            "#.#.#.#.#.#.###\n" +
            "#...#...#...###\n" +
            "###############"

    @TestFactory
    fun testPartOneExamples() = listOf(
        65L to 0,
        64L to 1,
        41L to 1,
        40L to 2,
        20L to 5,
        11L to 8,
    ).map { (threshold, expected) ->
        DynamicTest.dynamicTest("when input is $exampleInput and threshold is $threshold then answer should be $expected") {
            val result = day20.solvePartOneWithThreshold(exampleInput, threshold)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day20.solvePartOneFromFile()

        assertEquals(1197, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        77L to 0,
        76L to 3,
        75L to 3,
        74L to 7,
        50L to 32 + 31 + 29 + 39 + 25 + 23 + 20 + 19 + 12 + 14 + 12 + 22 + 4 + 3,
    ).map { (threshold, expected) ->
        DynamicTest.dynamicTest("when input is $exampleInput and threshold is $threshold then answer should be $expected") {
            val result = day20.solvePartTwoWithThreshold(exampleInput, threshold)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day20.solvePartTwoFromFile()

        assertEquals(944910, result)
    }
}