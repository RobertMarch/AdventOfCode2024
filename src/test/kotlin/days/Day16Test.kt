package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day16Test {

    private var day16: Day16 = Day16()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "###############\n" +
                "#.......#....E#\n" +
                "#.#.###.#.###.#\n" +
                "#.....#.#...#.#\n" +
                "#.###.#####.#.#\n" +
                "#.#.#.......#.#\n" +
                "#.#.#####.###.#\n" +
                "#...........#.#\n" +
                "###.#.#####.#.#\n" +
                "#...#.....#.#.#\n" +
                "#.#.#.###.#.#.#\n" +
                "#.....#...#.#.#\n" +
                "#.###.#.#.#.#.#\n" +
                "#S..#.....#...#\n" +
                "###############" to 7036L,
        "#################\n" +
                "#...#...#...#..E#\n" +
                "#.#.#.#.#.#.#.#.#\n" +
                "#.#.#.#...#...#.#\n" +
                "#.#.#.#.###.#.#.#\n" +
                "#...#.#.#.....#.#\n" +
                "#.#.#.#.#.#####.#\n" +
                "#.#...#.#.#.....#\n" +
                "#.#.#####.#.###.#\n" +
                "#.#.#.......#...#\n" +
                "#.#.###.#####.###\n" +
                "#.#.#...#.....#.#\n" +
                "#.#.#.#####.###.#\n" +
                "#.#.#.........#.#\n" +
                "#.#.#.#########.#\n" +
                "#S#.............#\n" +
                "#################" to 11048L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day16.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day16.solvePartOneFromFile()

        assertEquals(75416L, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "###############\n" +
                "#.......#....E#\n" +
                "#.#.###.#.###.#\n" +
                "#.....#.#...#.#\n" +
                "#.###.#####.#.#\n" +
                "#.#.#.......#.#\n" +
                "#.#.#####.###.#\n" +
                "#...........#.#\n" +
                "###.#.#####.#.#\n" +
                "#...#.....#.#.#\n" +
                "#.#.#.###.#.#.#\n" +
                "#.....#...#.#.#\n" +
                "#.###.#.#.#.#.#\n" +
                "#S..#.....#...#\n" +
                "###############" to 45,
        "#################\n" +
                "#...#...#...#..E#\n" +
                "#.#.#.#.#.#.#.#.#\n" +
                "#.#.#.#...#...#.#\n" +
                "#.#.#.#.###.#.#.#\n" +
                "#...#.#.#.....#.#\n" +
                "#.#.#.#.#.#####.#\n" +
                "#.#...#.#.#.....#\n" +
                "#.#.#####.#.###.#\n" +
                "#.#.#.......#...#\n" +
                "#.#.###.#####.###\n" +
                "#.#.#...#.....#.#\n" +
                "#.#.#.#####.###.#\n" +
                "#.#.#.........#.#\n" +
                "#.#.#.#########.#\n" +
                "#S#.............#\n" +
                "#################" to 64,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day16.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day16.solvePartTwoFromFile()

        assertEquals(476, result)
    }
}