package days

import kotlin.math.roundToInt
import kotlin.math.roundToLong

class Day13 : Day(13) {

    private val buttonRegex = Regex("Button .: X\\+(\\d+), Y\\+(\\d+)")
    private val prizeRegex = Regex("Prize: X=(\\d+), Y=(\\d+)")

    private data class ClawMachine(val xa: Double, val ya: Double, val xb: Double, val yb: Double, val xt: Double, val yt: Double)

    private fun solve(inputString: String, prizeOffset: Double, maxButtonCount: Long?): Long {
        val machines: List<ClawMachine> = inputString.lines().chunked(4) { lines ->
            val buttonA: List<Double> = buttonRegex.find(lines[0])!!.groupValues.drop(1).map { it.toDouble() }
            val buttonB: List<Double> = buttonRegex.find(lines[1])!!.groupValues.drop(1).map { it.toDouble() }
            val prize: List<Double> = prizeRegex.find(lines[2])!!.groupValues.drop(1).map { it.toDouble() }
            ClawMachine(buttonA[0], buttonA[1], buttonB[0], buttonB[1], prize[0] + prizeOffset, prize[1] + prizeOffset)
        }

        return machines.sumOf {
            val buttonBCount: Long = (((it.xt / it.xa) - (it.yt / it.ya)) / ((it.xb / it.xa) - (it.yb / it.ya))).roundToLong()
            val buttonACount: Long = ((it.xt - buttonBCount * it.xb) / it.xa).roundToLong()

            val countsPositive: Boolean = buttonACount >= 0 && buttonBCount >= 0
            val countsWithinMax: Boolean = maxButtonCount == null || (buttonACount <= maxButtonCount && buttonBCount <= maxButtonCount)
            val targetXHitExactly: Boolean = buttonACount * it.xa + buttonBCount * it.xb == it.xt
            val targetYHitExactly: Boolean = buttonACount * it.ya + buttonBCount * it.yb == it.yt

            if (countsPositive && countsWithinMax && targetXHitExactly && targetYHitExactly) {
                return@sumOf buttonACount * 3 + buttonBCount
            }

            0L
        }
    }

    override fun solvePartOne(inputString: String): Long {
        return solve(inputString, 0.0, 100)
//        val machines: List<ClawMachine> = inputString.lines().chunked(4) { lines ->
//            val buttonA: List<Double> = buttonRegex.find(lines[0])!!.groupValues.drop(1).map { it.toDouble() }
//            val buttonB: List<Double> = buttonRegex.find(lines[1])!!.groupValues.drop(1).map { it.toDouble() }
//            val prize: List<Double> = prizeRegex.find(lines[2])!!.groupValues.drop(1).map { it.toDouble() }
//            ClawMachine(buttonA[0], buttonA[1], buttonB[0], buttonB[1], prize[0], prize[1])
//        }
//
//        return machines.sumOf {
//            val buttonBCount: Int = (((it.xt / it.xa) - (it.yt / it.ya)) / ((it.xb / it.xa) - (it.yb / it.ya))).roundToInt()
//            val buttonACount: Int = ((it.xt - buttonBCount * it.xb) / it.xa).roundToInt()
//
//            if (buttonACount in 0..100 && buttonBCount in 0..100) {
//                if (buttonACount * it.xa + buttonBCount * it.xb == it.xt) {
//                    return@sumOf buttonACount * 3 + buttonBCount
//                }
//            }
//
//            0
//        }
    }

    override fun solvePartTwo(inputString: String): Long {
        return solve(inputString, 10000000000000.0, null)
//        val machines: List<ClawMachine> = inputString.lines().chunked(4) { lines ->
//            val buttonA: List<Double> = buttonRegex.find(lines[0])!!.groupValues.drop(1).map { it.toDouble() }
//            val buttonB: List<Double> = buttonRegex.find(lines[1])!!.groupValues.drop(1).map { it.toDouble() }
//            val prize: List<Double> = prizeRegex.find(lines[2])!!.groupValues.drop(1).map { it.toDouble() }
//            ClawMachine(buttonA[0], buttonA[1], buttonB[0], buttonB[1], prize[0] + 10000000000000, prize[1] + 10000000000000)
//        }
//
//        return machines.sumOf {
//            val buttonBCount: Long = (((it.xt / it.xa) - (it.yt / it.ya)) / ((it.xb / it.xa) - (it.yb / it.ya))).roundToLong()
//            val buttonACount: Long = ((it.xt - buttonBCount * it.xb) / it.xa).roundToLong()
//
//            if (buttonACount >= 0 && buttonBCount >= 0) {
//                if (buttonACount * it.xa + buttonBCount * it.xb == it.xt && buttonACount * it.ya + buttonBCount * it.yb == it.yt) {
//                    return@sumOf buttonACount * 3 + buttonBCount
//                }
//            }
//
//            0L
//        }
    }
}

fun main() {
    val day = Day13()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
