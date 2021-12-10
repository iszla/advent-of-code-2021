import java.lang.Integer.parseInt

fun main() {
    class Board(input: List<String>) {
        val data: List<MutableList<Int>>

        init {
            this.data = mutableListOf()
            input.map { it.split(" ") }
                .map { l -> this.data.add(l.filter { it != "" }.map { parseInt(it) } as MutableList<Int>) }
        }

        fun mark(number: Int) {
            data.filter { it.contains(number) }.map { it[it.indexOf(number)] = -1 }
        }

        fun bingo(): Boolean {
            if (data.count { row -> row.count { it == -1 } == data[0].size } > 0) return true

            for (i in 0 until data[0].size) {
                if (data.count { line -> line[i] == -1 } == data[0].size) return true
            }

            return false
        }

        fun score(num: Int): Int {
            data.map { list -> list.replaceAll { if (it == -1) 0 else it } }
            return data.sumOf { it.sum() } * num
        }
    }

    class BingoHall(val numbers: List<Int>, boards: List<List<String>>) {
        val boards: MutableList<Board>

        init {
            this.boards = mutableListOf()
            boards.forEach {
                this.boards.add(Board(it))
            }
        }

        fun play(): Int {
            for (num in numbers) {
                val winner = boards.map {
                    it.mark(num)
                    return@map it
                }.firstOrNull { it.bingo() }

                if (winner != null) {
                    return winner.score(num)
                }
            }
            return -1
        }

        fun playLastWinner(): Int {
            var lastWinner: Board? = null
            var lastNumber: Int? = null
            for (num in numbers) {
                val winner = boards.map {
                    it.mark(num)
                    return@map it
                }.firstOrNull { it.bingo() }

                if (winner != null) {
                    lastWinner = winner
                    lastNumber = num
                    boards.removeIf { it.bingo() }
                }

            }
            return lastWinner!!.score(lastNumber!!)
        }


    }

    fun bingoParser(data: List<String>): BingoHall {
        val numbers = data[0].split(",").map { parseInt(it) }
        val t = data.toMutableList()
        t.removeFirst()

        return BingoHall(numbers, t.filter { it != "" }.chunked(5))
    }

    fun part1(input: List<String>): Int {
        return bingoParser(input).play()
    }

    fun part2(input: List<String>): Int {
        return bingoParser(input).playLastWinner()
    }

    val testData = readInput("Day04_1_test")
    check(part1(testData) == 4512) { part1(testData).toString() }

    val data = readInput("Day04_1")
    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}