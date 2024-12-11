package days

class Day09 : Day(9) {

    override fun solvePartOne(inputString: String): Long {
        val files: List<Int> = inputString.map { it.digitToInt() }.toList()
        var startFileIndex = 0
        var startBitIndex = 0
        var endFileIndex: Int = files.size - 1
        var endFileOffset = 0

        var result = 0L

        while (startFileIndex < endFileIndex) {
            if (startFileIndex % 2 == 0) {
                for (fileIndex in 1..files[startFileIndex]) {
                    result += startFileIndex / 2 * startBitIndex
                    startBitIndex ++
                }
                startFileIndex++
            } else {
                for (fileIndex in 1..files[startFileIndex]) {
                    result += endFileIndex / 2 * startBitIndex
                    startBitIndex ++
                    endFileOffset ++
                    if (endFileOffset >= files[endFileIndex]) {
                        endFileIndex -= 2
                        endFileOffset = 0
                    }
                }
                startFileIndex ++
            }
        }

        if (startFileIndex == endFileIndex && endFileOffset < files[startFileIndex]) {
            for (fileIndex in endFileOffset..<files[startFileIndex]) {
                result += startFileIndex / 2 * startBitIndex
                startBitIndex ++
            }
        }

        return result
    }

    private data class FileInfo(val fileId: Int, var size: Int, var startIndex: Int, val hasData: Boolean)

    override fun solvePartTwo(inputString: String): Long {
        var startIndex = 0
        val files: List<FileInfo> = inputString.mapIndexed { index, c ->
            val file = FileInfo(index / 2, c.digitToInt(), startIndex, index % 2 == 0)
            startIndex += file.size
            file
        }.toList()

        var result = 0L

        files.reversed().filter { it.hasData }.forEach { file ->
            val movableSpace: FileInfo? = files.find { !it.hasData && it.size >= file.size && it.startIndex < file.startIndex }
            if (movableSpace != null) {
                for (blockIndex in 0..<file.size) {
                    result += file.fileId * (movableSpace.startIndex + blockIndex)
                }
                movableSpace.size -= file.size
                movableSpace.startIndex += file.size
            } else {
                for (blockIndex in 0..<file.size) {
                    result += file.fileId * (file.startIndex + blockIndex)
                }
            }
        }

        return result
    }
}

fun main() {
    val day = Day09()
    println(day.solvePartOneFromFile())
    println(day.solvePartTwoFromFile())
}
