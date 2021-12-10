import java.awt.Point
import java.lang.Integer.parseInt

data class Command(val direction: String, val length: Int)

fun main() {
    fun commandParser(input: String): Command {
        val i = input.split(" ")
        return Command(i[0], parseInt(i[1]))
    }

    fun part1(data: List<Command>): Int {
        val pos = Point()

        for (command in data) {
            when (command.direction) {
                "forward" -> pos.x += command.length
                "down" -> pos.y += command.length
                "up" -> pos.y -= command.length
            }
        }

        return pos.x * pos.y
    }

    fun part2(data: List<Command>): Int {
        val pos = Point()
        var aim = 0

        for (command in data) {
            when (command.direction) {
                "forward" -> {
                    pos.x += command.length
                    pos.y += aim * command.length
                }
                "down" -> aim += command.length
                "up" -> aim -= command.length
            }
        }

        return pos.x * pos.y
    }


    val testData = readInput("Day02_1_test", ::commandParser)
    check(part1(testData) == 150)
    check(part2(testData) == 900)

    val data = readInput("Day02_1", ::commandParser)
    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}
