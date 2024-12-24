package days

class Day23 : Day(23) {

    private fun parseInput(inputString: String): Map<String, List<String>> {
        val connections: MutableMap<String, MutableList<String>> = mutableMapOf()
        inputString.lines().forEach { line ->
            val parts: List<String> = line.split("-")
            connections.putIfAbsent(parts[0], mutableListOf())
            connections.putIfAbsent(parts[1], mutableListOf())
            connections[parts[0]]?.add(parts[1])
            connections[parts[1]]?.add(parts[0])
        }
        return connections
    }

    override fun solvePartOne(inputString: String): Int {
        val connections: Map<String, List<String>> = parseInput(inputString)

        val triples: MutableSet<Set<String>> = mutableSetOf()

        connections.keys
            .filter { it.startsWith("t") }
            .forEach { firstKey ->
                val connectedNodes: List<String> = connections[firstKey]!!

                for (i in connectedNodes.indices) {
                    val secondKey: String = connectedNodes[i]
                    for (j in i + 1..<connectedNodes.size) {
                        val thirdKey: String = connectedNodes[j]
                        if (connections[secondKey]!!.contains(thirdKey)) {
                            triples.add(setOf(firstKey, connectedNodes[i], connectedNodes[j]))
                        }
                    }
                }
            }

        return triples.size
    }

    private fun getLongestGroup(connections: Map<String, List<String>>, currentGroup: List<String>): List<String> {
        val commonConnections: List<String> = currentGroup
            .map { connections[it]!!.toSet() }
            .reduce { acc, strings -> acc.intersect(strings) }
            .filter { it > currentGroup.last() }
            .toList()

        if (commonConnections.isEmpty()) {
            return currentGroup
        }

        return commonConnections
            .map {
                val nextGroup: MutableList<String> = currentGroup.toMutableList()
                nextGroup.add(it)
                getLongestGroup(connections, nextGroup)
            }
            .maxBy { it.size }
    }

    override fun solvePartTwo(inputString: String): String {
        val connections: Map<String, List<String>> = parseInput(inputString)

        return connections.keys
            .map { getLongestGroup(connections, listOf(it)) }
            .maxBy { it.size }
            .sorted()
            .joinToString(",")
    }
}

fun main() {
    val day = Day23()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
