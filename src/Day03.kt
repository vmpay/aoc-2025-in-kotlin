import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Long = activateBatteries(input, 2)

    fun part2(input: List<String>): Long = activateBatteries(input, 12)

    // Read a large test input from the `src/DayXX_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357L)
    check(part2(testInput) == 3121910778619)

    // Read the input from the `src/DayXX.txt` file.
    val input = readInput("Day03")
    println("Final results")
    part1(input).println()
    part2(input).println()

}

fun activateBatteries(input: List<String>, requiredBatteries: Int): Long =
    input.fold(0L) { acc, string ->
        var batteriesCount = requiredBatteries
        var digits = 0L
        var startIndex = 0
        while (batteriesCount != 0) {
            val digit = string.substring(startIndex, string.length - batteriesCount + 1)
                .map { it.digitToInt() }
                .max()
            startIndex = string.indexOf(digit.toString(), startIndex) + 1
            digits += digit * 10.toDouble().pow(batteriesCount - 1).toLong()
            batteriesCount--
        }
        acc + digits
    }
