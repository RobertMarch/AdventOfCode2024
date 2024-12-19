package days

import kotlin.math.pow

class Day17 : Day(17) {

    private class ProgramState(var registerA: Long, var registerB: Long, var registerC: Long, var pointer: Int, val instructions: List<Int>) {

        fun getLiteralOperand(): Long {
            return instructions[pointer + 1].toLong()
        }

        fun getComboOperand(): Long {
            return when (instructions[pointer + 1]) {
                0 -> 0L
                1 -> 1L
                2 -> 2L
                3 -> 3L
                4 -> registerA
                5 -> registerB
                6 -> registerC
                else -> throw IllegalStateException("Unexpected instruction value ${instructions[pointer + 1]} at ${pointer + 1}")
            }
        }
    }

    private fun parseInput(inputString: String): ProgramState {
        val lines: List<String> = inputString.lines().filter { it.isNotBlank() }.map { it.split(": ")[1] }
        val programInstructions: List<Int> = lines[3].split(",").map { it.toInt() }
        return ProgramState(lines[0].toLong(), lines[1].toLong(), lines[2].toLong(), 0, programInstructions)
    }

    private fun processInstruction(state: ProgramState): Long? {
        val opcode: Int = state.instructions[state.pointer]
        var output: Long? = null

        when (opcode) {
            0 -> state.registerA /= 2.0.pow(state.getComboOperand().toDouble()).toLong()
            1 -> state.registerB = state.registerB xor state.getLiteralOperand()
            2 -> state.registerB = state.getComboOperand() % 8
            3 -> {
                if (state.registerA != 0L) {
                    state.pointer = state.getLiteralOperand().toInt()
                    return null
                }
            }
            4 -> state.registerB = state.registerB xor state.registerC
            5 -> output = state.getComboOperand() % 8
            6 -> state.registerB = state.registerA / 2.0.pow(state.getComboOperand().toDouble()).toLong()
            7 -> state.registerC = state.registerA / 2.0.pow(state.getComboOperand().toDouble()).toLong()
            else -> throw IllegalStateException("Unexpected instruction value $opcode at ${state.pointer}")
        }

        state.pointer += 2
        return output
    }

    private fun getOutputs(state: ProgramState): List<Long> {
        val outputs: MutableList<Long?> = mutableListOf()

        while (state.pointer < state.instructions.size) {
            outputs.add(processInstruction(state))
        }

        return outputs.filterNotNull()
    }

    override fun solvePartOne(inputString: String): String {
        val state: ProgramState = parseInput(inputString)

        return getOutputs(state).joinToString(",")
    }

    override fun solvePartTwo(inputString: String): Long {
        val state: ProgramState = parseInput(inputString)

        var possibleRegisterAValues: List<Long> = listOf(0L)

        for (digit in 1..state.instructions.size) {
            val possibleNextRegisterAValues: MutableList<Long> = mutableListOf()

            possibleRegisterAValues.forEach { previousRegisterA ->
                for (testNextDigit in 0..7) {
                    val registerA = previousRegisterA * 8 + testNextDigit

                    val outputs: List<Long> = getOutputs(ProgramState(registerA, 0, 0, 0, state.instructions))

                    if (outputs.first().toInt() == state.instructions[state.instructions.size - digit]) {
                        possibleNextRegisterAValues.add(registerA)
                    }
                }
            }

            possibleRegisterAValues = possibleNextRegisterAValues
        }

        return possibleRegisterAValues.min()
    }
}

fun main() {
    val day = Day17()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
