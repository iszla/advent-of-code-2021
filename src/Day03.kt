import java.lang.Integer.parseInt

fun main() {
    fun getLifeSupportValue(data: List<String>, mostSignificant: Boolean): String {
        var local = data
        for (i in 0 until local[0].length) {
            val bit = if (local.count { it[i] == '0' } > (local.size / 2)) '0' else '1'
            local = if (mostSignificant) {
                local.filter { it[i] == bit }
            } else {
                local.filter { it[i] != bit }
            }
            if (local.size == 1) break
        }

        return local[0]
    }

    fun part1(data: List<String>): Int {
        var gamma = ""
        var epsilon = ""

        for (i in 0 until data[0].length) {
            if (data.count { it[i] == '0' } > (data.size / 2)) {
                gamma += "0"
                epsilon += "1"
            } else {
                gamma += "1"
                epsilon += "0"
            }
        }

        return parseInt(gamma, 2) * parseInt(epsilon, 2)
    }

    fun part2(data: List<String>): Int {
        val oxygen = getLifeSupportValue(data, true)
        val co2 = getLifeSupportValue(data, false)

        return parseInt(oxygen, 2) * parseInt(co2, 2)
    }

    val testData = readInput("Day03_1_test")
    check(part1(testData) == 198) { part1(testData).toString() }
    check(part2(testData) == 230) { part2(testData).toString() }

    val data = readInput("Day03_1")
    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}