package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day22Test {

    private var day22: Day22 = Day22()

    @TestFactory
    fun testSecretGeneration() = listOf(
        123L to 15887950L,
        15887950L to 16495136L,
        16495136L to 527345L,
        527345L to 704524L,
        704524L to 1553684L,
        1553684L to 12683156L,
        12683156L to 11100544L,
        11100544L to 12249484L,
        12249484L to 7753432L,
        7753432L to 5908254L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day22.getNextSecretValue(input)

            assertEquals(expected, result)
        }
    }

    @TestFactory
    fun testPartOneExamples() = listOf(
        "1\n" +
                "10\n" +
                "100\n" +
                "2024" to 37327623L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day22.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day22.solvePartOneFromFile()

        assertEquals(19847565303L, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "1\n" +
                "2\n" +
                "3\n" +
                "2024" to 23L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day22.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day22.solvePartTwoFromFile()

        assertEquals(2250L, result)
    }
}