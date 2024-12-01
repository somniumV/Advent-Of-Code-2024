fun main() {
    fun part1(input: List<String>): Int {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()
        input.forEach {
            it.split("\\s+".toRegex()).forEachIndexed { index, number ->
                if (index == 0) leftList.add(number.toInt()) else rightList.add(number.toInt())
            }
        }
        leftList.sort()
        rightList.sort()
        val rangeList = mutableListOf<Int>()
        for (i in input.indices) {
            rangeList.add(maxOf(rightList[i], leftList[i]) - minOf(rightList[i], leftList[i]))
        }
        return rangeList.sum()
    }

    fun part2(input: List<String>): Int {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()
        input.forEach {
            it.split("\\s+".toRegex()).forEachIndexed { index, number ->
                if (index == 0) leftList.add(number.toInt()) else rightList.add(number.toInt())
            }
        }
        return leftList.toSet().sumOf { value ->
            rightList.count { it == value } * value
        }
    }

    // Test if implementation meets criteria from the description, like:
    //check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
