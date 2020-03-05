import model.GridPosition
import utils.aStarSearch
import utils.createMaze

fun main(args: Array<String>) {
    val rows = if (args.isNotEmpty()) args[0].toInt() else 8
    val cols = if (args.size == 2) args[1].toInt() else 8
    val maze = createMaze(rows, cols)
    maze.display()

    val (path, cost) = aStarSearch(GridPosition(0,0), GridPosition(rows - 1, cols - 1), maze)

    println()
    println("Maze solution:")
    println("Cost: $cost  Path: $path")
}