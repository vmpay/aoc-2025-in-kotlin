fun main() {
    fun part1(input: List<String>): Long =
        sumInvalidIds(input, ::isIdInvalidPart1)

    fun part2(input: List<String>): Long =
        sumInvalidIds(input, ::isIdInvalidPart2)

    // Read a large test input from the `src/DayXX_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 4174379265L)

    // Read the input from the `src/DayXX.txt` file.
    val input = readInput("Day02")
    println("Final results")
    part1(input).println()
    part2(input).println()
}

fun sumInvalidIds(input: List<String>, isIdInvalid: (String) -> Boolean): Long =
    input.first()
        .split(",")
        .fold(0) { acc, string ->
            acc + string.split("-").let {
                (it[0].toLong()..it[1].toLong())
                    .sumOf { id ->
                        if (isIdInvalid(id.toString())) id else 0
                    }
            }
        }

fun isIdInvalidPart1(id: String): Boolean =
    if (id.length % 2 == 1) {
        false
    } else {
        with(id.chunked(id.length / 2)) {
            this[0] == this[1]
        }
    }

fun isIdInvalidPart2(id: String): Boolean {
    val n = id.length
    for (len in 1..n / 2) {
        if (n % len != 0) continue
        var isRepeating = true
        for (i in len until n) {
            if (id[i] != id[i % len]) {
                isRepeating = false
                break
            }
        }
        if (isRepeating) return true
    }
    return false
}