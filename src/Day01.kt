fun main() {
    fun part1(input: List<Int>): Int {
        var increments = 0
        for (i in 0..input.size - 2) {
            if (input[i + 1] > input[i]) increments++
        }
        return increments
    }

    fun part2(input: List<Int>): Int {
        val parsed = mutableListOf<Int>()
        for (i in 0..input.size - 3) {
            parsed.add(input[i] + input[i + 1] + input[i + 2])
        }
        return part1(parsed)
    }


    var testInput = readInputToInt("Day01_1_test")
    check(part1(testInput) == 7)
    testInput = readInputToInt("Day01_2_test")
    check(part2(testInput) == 5)

    val input = readInputToInt("Day01_1")
    println(part1(input))
    println(part2(input))
}
