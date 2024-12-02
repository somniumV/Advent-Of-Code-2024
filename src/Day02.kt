import kotlin.streams.toList

fun main() {
    fun isReportSafe(input: List<Int>): Boolean {
        var asc = true
        var safe = true
        for (i in 0..<input.lastIndex) {
            if (i == 0) {
                if (input[i] > input[i + 1]) {
                    asc = false
                }
            }
            if (input[i] == input[i + 1]) {
                safe = false
            } else if (asc && (input[i + 1] - input[i]) !in 1L..3L) {
                safe = false
            } else if (!asc && (input[i] - input[i + 1]) !in 1L..3L) {
                safe = false
            }
        }
        return safe
    }

    fun part1(input: List<String>): Int {
        var safeCount = 0
        val filteredInput = input.map { it.split(" ").map { it.toInt() } }
        filteredInput.forEach {
            if (isReportSafe(it)) {
                safeCount++
            }
        }
        return safeCount
    }

    fun part2(input: List<String>): Int {
        var safeCount = 0
        val filteredInput = input.map { it.split(" ").map { it.toInt() } }
        filteredInput.forEach {
            for (i in 0..it.lastIndex) {
                if (isReportSafe(it.toMutableList().apply { removeAt(i) })) {
                    safeCount++
                    break
                }
            }
        }
        return safeCount
    }

    //check(part1(listOf("test_input")) == 1)

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
