import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // Read a large test input from the `src/DayXX_test.txt` file:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)

    // Read the input from the `src/DayXX.txt` file.
    val input = readInput("Day11")
    println("Final results")
    part1(input).println()
    part2(input).println()
}
