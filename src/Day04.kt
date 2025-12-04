fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        input.forEachIndexed { y, string ->
            string.forEachIndexed { x, char ->
                if (char == '@' && checkSurroundings(x, y, input)) result++
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val mInput = input.toMutableList()
        var result = 0
        var shouldRestart: Boolean
        do {
            shouldRestart = false
            mInput.forEachIndexed { y, string ->
                string.forEachIndexed { x, char ->
                    if (char == '@' && checkSurroundings(x, y, mInput)) {
                        result++
                        shouldRestart = true
                        mInput[y] = mInput[y].replaceRange(x..x, "X")
                    }
                }
            }
        } while (shouldRestart)
        return result
    }

    // Read a large test input from the `src/DayXX_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    // Read the input from the `src/DayXX.txt` file.
    val input = readInput("Day04")
    println("Final results")
    part1(input).println()
    part2(input).println()
}

fun checkSurroundings(x: Int, y: Int, input: List<String>): Boolean {
    val surroundings = listOf(
        Pair(x - 1, y - 1),
        Pair(x - 1, y),
        Pair(x - 1, y + 1),
        Pair(x, y - 1),
        Pair(x, y + 1),
        Pair(x + 1, y - 1),
        Pair(x + 1, y),
        Pair(x + 1, y + 1),
    ).filter { it.first >= 0 && it.second >= 0 && it.first < input[0].length && it.second < input.size }
    return surroundings.count { input[it.second][it.first] == '@' } < 4
}