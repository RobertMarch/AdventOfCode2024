package days

import days.point.Point2D

class Day15 : Day(15) {

    private val directions: Map<Char, Point2D> = mapOf(
        Pair('>', Point2D(1, 0)),
        Pair('v', Point2D(0, 1)),
        Pair('<', Point2D(-1, 0)),
        Pair('^', Point2D(0, -1)),
    )

    private data class WarehouseState(val walls: Set<Point2D>, val boxes: MutableSet<Point2D>, var robotPosition: Point2D)

    private fun parseInitialState(input: String, xScale: Int): WarehouseState {
        val walls: MutableSet<Point2D> = mutableSetOf()
        val boxes: MutableSet<Point2D> = mutableSetOf()
        var robotPosition: Point2D? = null

        input.lines().forEachIndexed { yIndex, line ->
            line.forEachIndexed { xIndex, char ->
                val point: Point2D = Point2D(xIndex.toLong() * xScale, yIndex.toLong())
                when (char) {
                    '#' -> walls.add(point)
                    'O' -> boxes.add(point)
                    '@' -> robotPosition = point
                }
            }
        }

        return WarehouseState(walls, boxes, robotPosition!!)
    }

    override fun solvePartOne(inputString: String): Long {
        val parts: List<String> = inputString.replace("\r\n", "\n").split("\n\n")
        val state: WarehouseState = parseInitialState(parts[0], 1)

        parts[1].filter { it != '\n' }.forEach { char ->
            val dir: Point2D = directions[char]!!
            val nextRobotPosition: Point2D = state.robotPosition.addToPoint(dir)
            if (state.walls.contains(nextRobotPosition)) {
                return@forEach
            }
            if (state.boxes.contains(nextRobotPosition)) {
                var pointToMoveBoxTo: Point2D = nextRobotPosition.addToPoint(dir)
                while (state.boxes.contains(pointToMoveBoxTo)) {
                    pointToMoveBoxTo = pointToMoveBoxTo.addToPoint(dir)
                }
                if (state.walls.contains(pointToMoveBoxTo)) {
                    return@forEach
                }
                state.boxes.remove(nextRobotPosition)
                state.boxes.add(pointToMoveBoxTo)
            }
            state.robotPosition = nextRobotPosition
        }

        return state.boxes.sumOf { it.x + it.y * 100 }
    }

    private fun findObjectAtPosition(objectSet: Set<Point2D>, position: Point2D): Point2D? {
        if (objectSet.contains(position)) {
            return position
        }
        val leftPosition: Point2D = position.addToPoint(directions['<']!!)
        if (objectSet.contains(leftPosition)) {
            return leftPosition
        }
        return null
    }

    private fun findObjectsInPathOfBox(objectSet: Set<Point2D>, boxPosition: Point2D, dir: Point2D): List<Point2D> {
        return listOfNotNull(
            findObjectAtPosition(objectSet, boxPosition.addToPoint(dir)),
            findObjectAtPosition(objectSet, boxPosition.addToPoint(directions['>']!!).addToPoint(dir)),
        ).filter { it != boxPosition }
    }

    private fun printState(state: WarehouseState) {
        println()
        for (y in 0..state.walls.maxOf { it.y }) {
            for (x in 0..state.walls.maxOf { it.x + 1 }) {
                val p = Point2D(x, y)
                print(when {
                    findObjectAtPosition(state.walls, p) != null -> '#'
                    state.boxes.contains(p) -> '['
                    findObjectAtPosition(state.boxes, p) != null -> ']'
                    state.robotPosition == p -> '@'
                    else -> '.'
                })
            }
            println()
        }
        println()
    }

    fun solvePartTwo(inputString: String, printStates: Boolean): Long {
        val parts: List<String> = inputString.replace("\r\n", "\n").split("\n\n")
        val state: WarehouseState = parseInitialState(parts[0], 2)

        parts[1].filter { it != '\n' }.forEach { char ->
            if (printStates) {
                printState(state)
                println(char)
            }
            val dir: Point2D = directions[char]!!
            val nextRobotPosition: Point2D = state.robotPosition.addToPoint(dir)
            if (findObjectAtPosition(state.walls, nextRobotPosition) != null) {
                return@forEach
            }
            val nextPositionBox: Point2D? = findObjectAtPosition(state.boxes, nextRobotPosition)
            if (nextPositionBox != null) {
                val boxesToMove: MutableSet<Point2D> = mutableSetOf()
                val nextBoxes: MutableList<Point2D> = mutableListOf(nextPositionBox)

                while (nextBoxes.isNotEmpty()) {
                    val nextBox: Point2D = nextBoxes.removeFirst()
                    boxesToMove.add(nextBox)
                    if (findObjectsInPathOfBox(state.walls, nextBox, dir).isNotEmpty()) {
                        return@forEach
                    }
                    nextBoxes.addAll(findObjectsInPathOfBox(state.boxes, nextBox, dir))
                }
                state.boxes.removeAll(boxesToMove)
                state.boxes.addAll(boxesToMove.map { it.addToPoint(dir) })
            }
            state.robotPosition = nextRobotPosition
        }
        if (printStates) {
            printState(state)
        }

        return state.boxes.sumOf { it.x + it.y * 100 }
    }

    override fun solvePartTwo(inputString: String): Long {
        return solvePartTwo(inputString, false)
    }
}

fun main() {
    val day = Day15()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
