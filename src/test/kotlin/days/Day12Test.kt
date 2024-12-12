package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day12Test {

    private var day12: Day12 = Day12()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "AAAA\n" +
                "BBCD\n" +
                "BBCC\n" +
                "EEEC" to 140L,
        "OOOOO\n" +
                "OXOXO\n" +
                "OOOOO\n" +
                "OXOXO\n" +
                "OOOOO" to 772L,
        "RRRRIICCFF\n" +
                "RRRRIICCCF\n" +
                "VVRRRCCFFF\n" +
                "VVRCCCJFFF\n" +
                "VVVVCJJCFE\n" +
                "VVIVCCJJEE\n" +
                "VVIIICJJEE\n" +
                "MIIIIIJJEE\n" +
                "MIIISIJEEE\n" +
                "MMMISSJEEE" to 1930L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day12.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day12.solvePartOneFromFile()

        assertEquals(1461806L, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "AAAA\n" +
                "BBCD\n" +
                "BBCC\n" +
                "EEEC" to 80L,
        "OOOOO\n" +
                "OXOXO\n" +
                "OOOOO\n" +
                "OXOXO\n" +
                "OOOOO" to 436L,
        "RRRRIICCFF\n" +
                "RRRRIICCCF\n" +
                "VVRRRCCFFF\n" +
                "VVRCCCJFFF\n" +
                "VVVVCJJCFE\n" +
                "VVIVCCJJEE\n" +
                "VVIIICJJEE\n" +
                "MIIIIIJJEE\n" +
                "MIIISIJEEE\n" +
                "MMMISSJEEE" to 1206L,
        "EEEEE\n" +
                "EXXXX\n" +
                "EEEEE\n" +
                "EXXXX\n" +
                "EEEEE" to 236L,
        "AAAAAA\n" +
                "AAABBA\n" +
                "AAABBA\n" +
                "ABBAAA\n" +
                "ABBAAA\n" +
                "AAAAAA" to 368L,
        "AAAAAA\n" +
                "ABBAAA\n" +
                "ABBAAA\n" +
                "AAABBA\n" +
                "AAABBA\n" +
                "AAAAAA" to 368L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day12.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day12.solvePartTwoFromFile()

        assertEquals(887932L, result)
    }
}