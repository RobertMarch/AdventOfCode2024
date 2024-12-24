package days

import java.util.stream.Collectors

class Day22 : Day(22) {

    fun getNextSecretValue(input: Long): Long {
        var number: Long = input

        var mixingValue: Long = number * 64
        number = (number xor mixingValue) % 16777216
        mixingValue = number / 32
        number = (number xor mixingValue)
        mixingValue = number * 2048
        return (number xor mixingValue) % 16777216
    }

    override fun solvePartOne(inputString: String): Long {
        return inputString.lines().sumOf {
            var number: Long = it.toLong()
            for (i in 1..2000) {
                number = getNextSecretValue(number)
            }
            println(number)
            number
        }
    }

    override fun solvePartTwo(inputString: String): Any {
        val sequences: MutableList<List<Long>> = mutableListOf()
        for (a in -9L..9L) {
            for (b in -9L..9) {
                for (c in -9L..9) {
                    for (d in -9L..9) {
                        if ((0..9).any {
                            it + a in 0..9
                                    && it + a + b in 0..9
                                    && it + a + b + c in 0..9
                                    && it + a + b + c + d in 0..9
                        }) {
                            sequences.add(listOf(a, b, c, d))
                        }
                    }
                }
            }
        }

        return sequences
            .parallelStream()
            .map { sequence ->
                inputString.lines().sumOf {
                    var number: Long = it.toLong()
                    val lastDifferences: MutableList<Long> = mutableListOf()
                    for (i in 1..2000) {
                        val nextNumber = getNextSecretValue(number)
                        lastDifferences.add((nextNumber % 10) - (number % 10))
                        number = nextNumber
                        if (lastDifferences.size > 4) {
                            lastDifferences.removeFirst()
                        }
                        if (lastDifferences.size == 4 && lastDifferences == sequence) {
                            return@sumOf (number % 10)
                        }
                    }
                    0
                }
            }
            .collect(Collectors.toList())
            .max()
    }
}

fun main() {
    val day = Day22()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
