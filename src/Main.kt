import utils.createMaze

fun main(args: Array<String>) {
    val rows = if (args.isNotEmpty()) args[0].toInt() else 8
    val cols = if (args.size == 2) args[1].toInt() else 8
    val maze = createMaze(rows, cols)
    maze.display()
}