package tictactoe

fun main() {
    var cells = listOf("___", "___", "___")
    val xWins = { checkWin(cells, 'X') }
    val oWins = { checkWin(cells, 'O') }
    val empty = { cells.joinToString("").contains('_') }
    var letter = 'X'

    do {
        printGame(cells)
        cells = getCell(cells.toMutableList(), letter)
        letter = if (letter == 'X') 'O' else 'X'
    } while (!xWins() && !oWins() && empty())

    printGame(cells)

    when {
        xWins() -> println("X wins")
        oWins() -> println("O wins")
        else -> println("Draw")
    }
}

fun getCell(cells: MutableList<String>, char: Char): List<String> {
    var numbers: List<String>
    val range = 1..3

    while (true) {
        numbers = getString("Enter the coordinates: ").split(" ")
        if (numbers.size != 2 || !isNumber(numbers[0]) || !isNumber(numbers[1]))
            println("You should enter numbers!")
        else if (!range.contains(numbers[0].toInt()) || !range.contains(numbers[1].toInt()))
            println("Coordinates should be from 1 to 3!")
        else if (cells[numbers[0].toInt() - 1][numbers[1].toInt() - 1] != '_')
            println("This cell is occupied! Choose another one!")
        else break
    }

    val i1 = numbers[0].toInt() - 1
    val i2 = numbers[1].toInt() - 1

    cells[i1] = (if (i2 > 0) cells[i1].substring(0, i2) else "") + char +
            if (i2 < cells[i1].lastIndex) cells[i1].substring(i2 + 1) else ""

    return cells
}

fun printGame(cells: List<String>) {
    val dashes = "-".repeat(9)

    println(dashes)
    cells.forEach { line -> print("| "); line.chunked(1).forEach { print("$it ") }; println("|") }
    println(dashes)
}

fun checkWin(cells: List<String>, check: Char): Boolean {
    cells.forEach { line -> if (line.all { it == check }) return true }
    for (i in cells.indices) if (cells[0][i] == check && cells[1][i] == check && cells[2][i] == check) return true
    if (cells[0][0] == check && cells[1][1] == check && cells[2][2] == check) return true
    if (cells[0][2] == check && cells[1][1] == check && cells[2][0] == check) return true
    return false
}

fun getString(text: String): String {
    print(text)
    return readLine()!!
}

fun isNumber(number: String) = number.toIntOrNull() != null
/*
fun main() {
    var n = 0
    val grid = mutableListOf(
        mutableListOf(" ", " ", " "),
        mutableListOf(" ", " ", " "),
        mutableListOf(" ", " ", " ")
    )
    val moves = listOf("X", "O")

    while (true) {
        // Print grid
        println("---------")
        for (row in grid) {
            print("| ")
            for (cell in row) {
                print("$cell ")
            }
            println("|")
        }
        println("---------")

        val player = moves[n % 2]

        // Check for a winner
        if (checkWin(grid, player)) {
            println("$player wins")
            break
        }

        // Check for a draw
        if (n == 9) {
            println("Draw")
            break
        }

        // Prompt for move
        print("Enter the coordinates: ")
        val input = readLine()
        val x: Int
        val y: Int
        try {
            val parts = input!!.split(" ")
            x = parts[0].toInt()
            y = parts[1].toInt()
        } catch (e: Exception) {
            println("You should enter numbers!")
            continue
        }

        // Validate move
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            println("Coordinates should be from 1 to 3!")
            continue
        }
        val row = 3 - y
        val col = x - 1
        if (grid[row][col] != " ") {
            println("This cell is occupied! Choose another one!")
            continue
        }

        // Make move
        grid[row][col] = player
        n++
    }
}

fun checkWin(grid: List<List<String>>, player: String): Boolean {
    // Check rows
    for (row in grid) {
        if (row.all { it == player }) {
            return true
        }
    }

    // Check columns
    for (col in 0..2) {
        if (grid.all { it[col] == player }) {
            return true
        }
    }

    // Check diagonals
    if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
        return true
    }
    if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) {
        return true
    }

    return false
}
*/
