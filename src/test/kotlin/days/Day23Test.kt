package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day23Test {

    private var day23: Day23 = Day23()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "kh-tc\n" +
                "qp-kh\n" +
                "de-cg\n" +
                "ka-co\n" +
                "yn-aq\n" +
                "qp-ub\n" +
                "cg-tb\n" +
                "vc-aq\n" +
                "tb-ka\n" +
                "wh-tc\n" +
                "yn-cg\n" +
                "kh-ub\n" +
                "ta-co\n" +
                "de-co\n" +
                "tc-td\n" +
                "tb-wq\n" +
                "wh-td\n" +
                "ta-ka\n" +
                "td-qp\n" +
                "aq-cg\n" +
                "wq-ub\n" +
                "ub-vc\n" +
                "de-ta\n" +
                "wq-aq\n" +
                "wq-vc\n" +
                "wh-yn\n" +
                "ka-de\n" +
                "kh-ta\n" +
                "co-tc\n" +
                "wh-qp\n" +
                "tb-vc\n" +
                "td-yn" to 7,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day23.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day23.solvePartOneFromFile()

        assertEquals(1358, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "kh-tc\n" +
                "qp-kh\n" +
                "de-cg\n" +
                "ka-co\n" +
                "yn-aq\n" +
                "qp-ub\n" +
                "cg-tb\n" +
                "vc-aq\n" +
                "tb-ka\n" +
                "wh-tc\n" +
                "yn-cg\n" +
                "kh-ub\n" +
                "ta-co\n" +
                "de-co\n" +
                "tc-td\n" +
                "tb-wq\n" +
                "wh-td\n" +
                "ta-ka\n" +
                "td-qp\n" +
                "aq-cg\n" +
                "wq-ub\n" +
                "ub-vc\n" +
                "de-ta\n" +
                "wq-aq\n" +
                "wq-vc\n" +
                "wh-yn\n" +
                "ka-de\n" +
                "kh-ta\n" +
                "co-tc\n" +
                "wh-qp\n" +
                "tb-vc\n" +
                "td-yn" to "co,de,ka,ta",
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day23.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day23.solvePartTwoFromFile()

        assertEquals(1, result)
    }
}