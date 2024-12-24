package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day24Test {

    private var day24: Day24 = Day24()

    @TestFactory
    fun testPartOneExamples() = listOf(
        "x00: 1\n" +
                "x01: 1\n" +
                "x02: 1\n" +
                "y00: 0\n" +
                "y01: 1\n" +
                "y02: 0\n" +
                "\n" +
                "x00 AND y00 -> z00\n" +
                "x01 XOR y01 -> z01\n" +
                "x02 OR y02 -> z02" to 4L,
        "x00: 1\n" +
                "x01: 0\n" +
                "x02: 1\n" +
                "x03: 1\n" +
                "x04: 0\n" +
                "y00: 1\n" +
                "y01: 1\n" +
                "y02: 1\n" +
                "y03: 1\n" +
                "y04: 1\n" +
                "\n" +
                "ntg XOR fgs -> mjb\n" +
                "y02 OR x01 -> tnw\n" +
                "kwq OR kpj -> z05\n" +
                "x00 OR x03 -> fst\n" +
                "tgd XOR rvg -> z01\n" +
                "vdt OR tnw -> bfw\n" +
                "bfw AND frj -> z10\n" +
                "ffh OR nrd -> bqk\n" +
                "y00 AND y03 -> djm\n" +
                "y03 OR y00 -> psh\n" +
                "bqk OR frj -> z08\n" +
                "tnw OR fst -> frj\n" +
                "gnj AND tgd -> z11\n" +
                "bfw XOR mjb -> z00\n" +
                "x03 OR x00 -> vdt\n" +
                "gnj AND wpb -> z02\n" +
                "x04 AND y00 -> kjc\n" +
                "djm OR pbm -> qhw\n" +
                "nrd AND vdt -> hwm\n" +
                "kjc AND fst -> rvg\n" +
                "y04 OR y02 -> fgs\n" +
                "y01 AND x02 -> pbm\n" +
                "ntg OR kjc -> kwq\n" +
                "psh XOR fgs -> tgd\n" +
                "qhw XOR tgd -> z09\n" +
                "pbm OR djm -> kpj\n" +
                "x03 XOR y03 -> ffh\n" +
                "x00 XOR y04 -> ntg\n" +
                "bfw OR bqk -> z06\n" +
                "nrd XOR fgs -> wpb\n" +
                "frj XOR qhw -> z04\n" +
                "bqk OR frj -> z07\n" +
                "y03 OR x01 -> nrd\n" +
                "hwm AND bqk -> z03\n" +
                "tgd XOR rvg -> z12\n" +
                "tnw OR pbm -> gnj" to 2024L,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day24.solvePartOne(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartOne() {
        val result = day24.solvePartOneFromFile()

        assertEquals(69201640933606L, result)
    }

    @TestFactory
    fun testPartTwoExamples() = listOf(
        "input" to 1,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("when input is $input then answer should be $expected") {
            val result = day24.solvePartTwo(input)

            assertEquals(expected, result)
        }
    }

    @Test
    fun solvePartTwo() {
        val result = day24.solvePartTwoFromFile()

        // Manual checks done against the outputs to compare the expected pattern of XOR, AND etc
        assertEquals("dhq,hbs,jcp,kfp,pdg,z18,z22,z27", result)
    }
}