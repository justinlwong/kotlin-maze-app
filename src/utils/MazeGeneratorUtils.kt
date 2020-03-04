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
                         mazeArr: Array<Array<Cell>>) {
    val numRows = mazeArr.size
    val numCols = mazeArr[0].size
    val dirs: MutableList<Direction> = Direction.values().toMutableList()
    dirs.shuffle()
    dirs.forEach { dir ->
        val nRow = cRow + dir.dR
        val nCol = cCol + dir.dC
        if (inside(nRow, numRows) && inside(nCol, numCols)
            && mazeArr[nRow][nCol].wallIndicator == 0) {
            mazeArr[cRow][cCol].wallIndicator = dir.bit or mazeArr[cRow][cCol].wallIndicator
            mazeArr[nRow][nCol].wallIndicator = dir.opposite.bit or mazeArr[nRow][nCol].wallIndicator
            generateMaze(nRow, nCol, mazeArr)
        }
    }
}

private fun inside(v: Int, upper: Int) = v in 0 until upper