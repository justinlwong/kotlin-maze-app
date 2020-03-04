package utils

import model.Cell
import model.Direction
import model.Maze

/**
 * Creates maze of [Cell]s with given parameters
 * @param numRows
 * @param numCols
 */
fun createMaze(numRows: Int, numCols: Int): Maze {
    val arr: Array<Array<Cell>> = Array(numRows) {
        Array(numCols) {
            Cell(0)
        }
    }
    generateMaze(0, 0, arr)
    return Maze(arr)
}

private fun generateMaze(cRow: Int,
                         cCol: Int,
                         maze: Array<Array<Cell>>) {
    val numRows = maze.size
    val numCols = maze[0].size
    val dirs: MutableList<Direction> = Direction.values().toMutableList()
    dirs.shuffle()
    dirs.forEach { dir ->
        val nRow = cRow + dir.dx
        val nCol = cCol + dir.dy
        if (inside(nRow, numRows) && inside(nCol, numCols)
            && maze[nRow][nCol].wallIndicator == 0) {
            maze[cRow][cCol].wallIndicator = dir.bit or maze[cRow][cCol].wallIndicator
            maze[nRow][nCol].wallIndicator = dir.opposite.bit or maze[nRow][nCol].wallIndicator
            generateMaze(nRow, nCol, maze)
        }
    }
}

private fun inside(v: Int, upper: Int) = v in 0 until upper