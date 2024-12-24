package days

class Day24 : Day(24) {

    private enum class Operation {
        AND,
        OR,
        XOR,
    }

    private interface TreeNode {
        fun toStringIndented(indent: Int): String
    }

    private class InputTreeNode(val outputNode: String) : TreeNode {
        override fun toStringIndented(indent: Int): String {
            return " ".repeat(indent) + "INPUT: $outputNode"
        }
    }

    private class GateTreeNode(val outputNode: String, var inputNode1: TreeNode?, var inputNode2: TreeNode?, val op: Operation) : TreeNode {
        override fun toStringIndented(indent: Int): String {
            if (inputNode1 is InputTreeNode || (inputNode1 is GateTreeNode && (inputNode1 as GateTreeNode).inputNode1 is InputTreeNode)) {
                return " ".repeat(indent) + "$outputNode, $op\n ${inputNode2?.toStringIndented(indent + 2)}\n ${inputNode1?.toStringIndented(indent + 2)}"
            }
            return " ".repeat(indent) + "$outputNode, $op\n ${inputNode1?.toStringIndented(indent + 2)}\n ${inputNode2?.toStringIndented(indent + 2)}"
        }
    }

    private data class Gate(val input1: String, val input2: String, val op: Operation)

    override fun solvePartOne(inputString: String): Any {
        val parts: List<String> = inputString.replace("\r", "").split("\n\n")

        val knownNodeValues: MutableMap<String, Boolean> = parts[0].lines().associate {
            val lineParts: List<String> = it.split(": ")
            Pair(lineParts[0], lineParts[1] == "1")
        }.toMutableMap()

        val connectedNextNodes: MutableMap<String, MutableList<String>> = mutableMapOf()
        val gates: MutableMap<String, Gate> = mutableMapOf()

        parts[1].lines().forEach {
            val lineParts: List<String> = it.split(" ")
            connectedNextNodes.putIfAbsent(lineParts[0], mutableListOf())
            connectedNextNodes.putIfAbsent(lineParts[2], mutableListOf())
            connectedNextNodes[lineParts[0]]!!.add(lineParts[4])
            connectedNextNodes[lineParts[2]]!!.add(lineParts[4])
            gates[lineParts[4]] = Gate(lineParts[0], lineParts[2], Operation.valueOf(lineParts[1]))
        }

        val possibleNodes: MutableList<String> = mutableListOf()

        connectedNextNodes.filter { it.key.startsWith("x") || it.key.startsWith("y") }
            .forEach { possibleNodes.addAll(it.value) }

        while (possibleNodes.isNotEmpty()) {
            val nextNode: String = possibleNodes.removeFirst()
            if (knownNodeValues.containsKey(nextNode)) {
                continue
            }
            val gate: Gate = gates[nextNode]!!
            val input1: Boolean? = knownNodeValues[gate.input1]
            val input2: Boolean? = knownNodeValues[gate.input2]
            if (input1 != null && input2 != null) {
                val result: Boolean = when (gate.op) {
                    Operation.AND -> input1 && input2
                    Operation.OR -> input1 || input2
                    Operation.XOR -> input1 xor input2
                }
                knownNodeValues[nextNode] = result
                val nextNodes: List<String>? = connectedNextNodes[nextNode]
                if (nextNodes != null) {
                    possibleNodes.addAll(nextNodes)
                }
            }
        }

        return knownNodeValues.filter { it.key.startsWith("z") }
            .entries
            .sortedByDescending { it.key }
            .map { if (it.value) "1" else "0" }
            .joinToString("")
            .toLong(2)
    }

    override fun solvePartTwo(inputString: String): Any {
        val parts: List<String> = inputString.replace("\r", "").split("\n\n")

        val connectedNextNodes: MutableMap<String, MutableList<String>> = mutableMapOf()
        val gates: MutableMap<String, Gate> = mutableMapOf()

        val swaps: Map<String, String> = mapOf(
            Pair("z18", "dhq"),
            Pair("dhq", "z18"),

            Pair("pdg", "z22"),
            Pair("z22", "pdg"),

            Pair("jcp", "z27"),
            Pair("z27", "jcp"),

            Pair("kfp", "hbs"),
            Pair("hbs", "kfp"),
        )

        parts[1].lines().forEach {
            val lineParts: List<String> = it.split(" ")
            connectedNextNodes.putIfAbsent(lineParts[0], mutableListOf())
            connectedNextNodes.putIfAbsent(lineParts[2], mutableListOf())

            val outputNode: String = swaps.getOrDefault(lineParts[4], lineParts[4])

            connectedNextNodes[lineParts[0]]!!.add(outputNode)
            connectedNextNodes[lineParts[2]]!!.add(outputNode)
            gates[outputNode] = Gate(lineParts[0], lineParts[2], Operation.valueOf(lineParts[1]))
        }

        val possibleNodes: MutableList<String> = mutableListOf()

        connectedNextNodes.filter { it.key.startsWith("x") || it.key.startsWith("y") }
            .forEach { possibleNodes.addAll(it.value) }

        val gateNodes: Map<String, GateTreeNode> = gates
            .mapValues {
                GateTreeNode(
                    it.key,
                null,
                null,
                it.value.op
            ) }
        gateNodes.keys.forEach {
            val gate: Gate = gates[it]!!
            val node: GateTreeNode = gateNodes[it]!!
            node.inputNode1 = gateNodes[gate.input1] ?: InputTreeNode(gate.input1)
            node.inputNode2 = gateNodes[gate.input2] ?: InputTreeNode(gate.input2)
        }

        val outputNodes: List<GateTreeNode> = gateNodes.values
            .filter { it.outputNode.startsWith("z") }
            .sortedByDescending { it.outputNode }

        outputNodes.forEach {
            println(it.toStringIndented(0))
            println()
        }

        val outputNodesToUsedInputs: List<List<String>> = outputNodes.map { outputNode ->
            val inputs: MutableList<String> = mutableListOf()

            val nextNodes: MutableList<TreeNode> = mutableListOf(outputNode.inputNode1!!, outputNode.inputNode2!!)

            while (nextNodes.isNotEmpty()) {
                val next: TreeNode = nextNodes.removeFirst()

                if (next is GateTreeNode) {
                    nextNodes.add(next.inputNode1!!)
                    nextNodes.add(next.inputNode2!!)
                } else if (next is InputTreeNode && next.outputNode.startsWith("x")) {
                    inputs.add(next.outputNode)
                }
            }

            inputs
        }

        return swaps.keys.sorted().joinToString(",")
    }
}

fun main() {
    val day = Day24()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
