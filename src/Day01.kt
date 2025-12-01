import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        var position = 50
        for (line in input) {
            val direction = line[0]
            val distance = line.substring(1).toInt()
            position += if(direction == 'L') -distance else distance
            position %= 100
            if (position < 0) {
                position += 100
            }
            if (position == 0) {
                result++
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        var position = 50
        for (line in input) {
            val direction = line[0]
            val distance = line.substring(1).toInt()
            var adjuster = 0
            when (direction) {
                'L' -> {
                    if (position == 0) {
                        adjuster--
                    }
                    if(position <= distance){
                        adjuster++
                    }
                    position -= distance
                }

                else -> {
                    position += distance
                }
            }
            adjuster += abs(position / 100)
            result += adjuster
            position %= 100
            if (position < 0) {
                position += 100
            }
        }
        return result
    }

    // Read a large test input from the `src/DayXX_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    // Read the input from the `src/DayXX.txt` file.
    val input = readInput("Day01")
    println("Final results")
    part1(input).println()
    part2(input).println()
}
