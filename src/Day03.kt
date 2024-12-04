import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.streams.toList

fun main() {
    fun String.sumOfMul(): Int {
        return """mul\((\d{1,3}),(\d{1,3})\)""".toRegex().findAll(this)
            .sumOf { match ->
                val (x, y) = match.destructured
                x.toInt() * y.toInt()
            }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { it.sumOfMul() }
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        input.forEach { line ->
            var restLine = line
            while (restLine.isNotEmpty()) {
                val currentLine = restLine.substringBefore("don't()")
                sum += currentLine.sumOfMul()
                if (restLine.contains("do()")) {
                    restLine = restLine.substringAfter(currentLine).substringAfter("do()")
                } else {
                    restLine = ""
                }
            }
        }
        return sum
    }

    fun part2Idiomatic(input: List<String>): Int {
        return input.sumOf { line ->
            line.split("do()")
                .map { it.substringBefore("don't()") }
                .sumOf { it.sumOfMul() }
        }
    }

    val testInput = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    check(part1(listOf(testInput)) == 161)
    check(part2(listOf(testInput)) == 48)

    val input = Path("src/Day03.txt").readText()

    part2Idiomatic(listOf(input)).println()
    part1(listOf(input)).println()
    part2(listOf(input)).println()
}
