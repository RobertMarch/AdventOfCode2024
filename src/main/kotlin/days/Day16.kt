package days

import days.point.Point2D

class Day16 : Day(16) {

    private enum class Direction(val vector: Point2D) {
        NORTH(Point2D(0, -1)) {
            override fun getAdjacentDirections(): List<Direction> {
                return listOf(EAST, WEST)
            }
        },
        SOUTH(Point2D(0, 1)) {
            override fun getAdjacentDirections(): List<Direction> {
                return listOf(EAST, WEST)
            }
        },
        EAST(Point2D(1, 0)) {
            override fun getAdjacentDirections(): List<Direction> {
                return listOf(NORTH, SOUTH)
            }
        },
        WEST(Point2D(-1, 0)) {
            override fun getAdjacentDirections(): List<Direction> {
                return listOf(NORTH, SOUTH)
            }
        };

        abstract fun getAdjacentDirections(): List<Direction>
    }

    private data class ReachableState(val position: Point2D, val direction: Direction)

    private fun parseState(inputString: String): Triple<Point2D, Point2D, Set<Point2D>> {
        val paths: MutableSet<Point2D> = mutableSetOf()
        var start: Point2D? = null
        var end: Point2D? = null

        inputString.lines().forEachIndexed { yIndex, line ->
            line.forEachIndexed { xIndex, char ->
                if (char != '#') {
                    val point: Point2D = Point2D(xIndex.toLong(), yIndex.toLong())
                    paths.add(point)
                    if (char == 'S') {
                        start = point
                    }
                    if (char == 'E') {
                        end = point
                    }
                }
            }
        }

        return Triple(start!!, end!!, paths)
    }

    override fun solvePartOne(inputString: String): Long {
        val (start: Point2D, end: Point2D, paths: Set<Point2D>) = parseState(inputString)

        val exploredStates: MutableMap<ReachableState, Long> = mutableMapOf()
        val nextStates: MutableMap<ReachableState, Long> = mutableMapOf(Pair(ReachableState(start, Direction.EAST), 0))

        while (nextStates.isNotEmpty() && !exploredStates.any { it.key.position == end }) {
            val (nextState: ReachableState, nextDistance: Long) = nextStates.minBy { it.value }
            exploredStates[nextState] = nextDistance
            nextStates.remove(nextState)

            val possibleNextStates: List<Pair<ReachableState, Long>> = listOf(
                Pair(ReachableState(nextState.position.addToPoint(nextState.direction.vector), nextState.direction), nextDistance + 1),
                Pair(ReachableState(nextState.position, nextState.direction.getAdjacentDirections()[0]), nextDistance + 1000),
                Pair(ReachableState(nextState.position, nextState.direction.getAdjacentDirections()[1]), nextDistance + 1000),
            )

            possibleNextStates.filter {
                paths.contains(it.first.position) && !exploredStates.contains(it.first) && nextStates.getOrDefault(it.first, Long.MAX_VALUE) > it.second
            }.forEach {
                nextStates[it.first] = it.second
            }
        }

        return exploredStates.filterKeys { it.position == end }.values.min()
    }

    private class State(val reachableState: ReachableState, var distance: Long, val visitedPoints: MutableSet<Point2D>) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as State

            return reachableState == other.reachableState
        }

        override fun hashCode(): Int {
            return reachableState.hashCode()
        }
    }

    private fun printMapAndBestRoutes(exploredStates: Set<State>, paths: Set<Point2D>, end: Point2D) {
        val stateToEnd: State = exploredStates.find { it.reachableState.position == end }!!

        for (y in 0..exploredStates.maxOf { it.reachableState.position.y } + 1) {
            for (x in 0..exploredStates.maxOf { it.reachableState.position.x } + 1) {
                val p: Point2D = Point2D(x, y)
                print(when {
                    stateToEnd.visitedPoints.contains(p) -> 'O'
                    paths.contains(p) -> '.'
                    else -> '#'
                })
            }
            println()
        }
    }

    override fun solvePartTwo(inputString: String): Int {
        val (start: Point2D, end: Point2D, paths: Set<Point2D>) = parseState(inputString)

        val exploredStates: MutableSet<State> = mutableSetOf()
        val nextStates: MutableSet<State> = mutableSetOf(State(ReachableState(start, Direction.EAST), 0, mutableSetOf(start)))

        while (nextStates.isNotEmpty() && !exploredStates.any { it.reachableState.position == end }) {
            val nextState: State = nextStates.minBy { it.distance }
            exploredStates.add(nextState)
            nextStates.remove(nextState)

            val nextPositionAfterMove: Point2D = nextState.reachableState.position.addToPoint(nextState.reachableState.direction.vector)
            val nextVisitedAfterMove: MutableSet<Point2D> = nextState.visitedPoints.toMutableSet()
            nextVisitedAfterMove.add(nextPositionAfterMove)

            val possibleNextStates: List<State> = listOf(
                State(ReachableState(nextPositionAfterMove, nextState.reachableState.direction), nextState.distance + 1, nextVisitedAfterMove),
                State(ReachableState(nextState.reachableState.position, nextState.reachableState.direction.getAdjacentDirections()[0]), nextState.distance + 1000, nextState.visitedPoints),
                State(ReachableState(nextState.reachableState.position, nextState.reachableState.direction.getAdjacentDirections()[1]), nextState.distance + 1000, nextState.visitedPoints),
            )

            possibleNextStates.filter {
                paths.contains(it.reachableState.position) && !exploredStates.contains(it) && (nextStates.find { next -> it == next }?.distance ?: Long.MAX_VALUE) >= it.distance
            }.forEach {
                val existingNextState: State? = nextStates.find { next -> it == next }
                if (existingNextState?.distance == it.distance) {
                    existingNextState.visitedPoints.addAll(it.visitedPoints)
                } else {
                    nextStates.add(it)
                }
            }
        }

//        printMapAndBestRoutes(exploredStates, paths, end)

        return exploredStates.filter { it.reachableState.position == end }.minBy {it.distance}.visitedPoints.size
    }
}

fun main() {
    val day = Day16()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
