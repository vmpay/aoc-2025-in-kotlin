fun main() {
    fun part1(input: List<String>): Long {
        val (signs, numbers) = parseInput1(input)
        return signs.foldIndexed(0L) { index, acc, sign ->
            if (sign == '+') {
                acc + numbers[index].sum()
            } else {
                acc + numbers[index].fold(1L) { acc, l -> acc * l }
            }
        }
    }

    fun part2(input: List<String>): Long {
        return parseInput2(input)
    }

    // Read a large test input from the `src/DayXX_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 4277556L)
    check(part2(testInput) == 3263827L)

    // Read the input from the `src/DayXX.txt` file.
    val input = readInput("Day06")
    println("Final results")
    part1(input).println()
    part2(input).println()
}

fun parseInput1(input: List<String>): Pair<List<Char>, List<List<Long>>> {
    val numbers = MutableList(input[0].split(" ").size) { mutableListOf<Long>() }
    val signs = mutableListOf<Char>()
    for (j in 0 until input.size) {
        val line = input[j]
            .replace("      ", " ")
            .replace("     ", " ")
            .replace("    ", " ")
            .replace("   ", " ")
            .replace("  ", " ")
            .let { if (it.endsWith(" ")) it.dropLast(1) else it }
            .let { if (it.startsWith(" ")) it.drop(1) else it }
            .split(" ")
        for (i in 0 until line.size) {
            val current = line[i].toLongOrNull()
            if (current != null) {
                numbers[i].add(current)
            } else {
                signs.add(line[i].single())
            }
        }
    }
    return signs to numbers
}

fun parseInput2(list: List<String>): Long {
    val signs = list.last()
    val numbers = list.dropLast(1)
    var currentSign = signs[0]
    var result = 0L
    var groupSum = 0L
    for (j in 0 until numbers[0].length) {
        var currentNumber = ""
        for (i in 0 until numbers.size) {
            if (numbers[i][j].isDigit()) {
                currentNumber += numbers[i][j]
            }
        }
        if (j in signs.indices && signs[j] != ' ') {
            currentSign = signs[j]
            result += groupSum
            groupSum = if (currentSign == '+') 0 else 1
        }
        if (currentNumber.isNotBlank()) {
            if (currentSign == '+') groupSum += currentNumber.toLong()
            else groupSum *= currentNumber.toLong()
        }
    }
    result += groupSum

    return result
}