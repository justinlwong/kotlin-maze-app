package model

import utils.inside

/**
 * A wrapper class for maze which is a 2-D array for [Cell]s
 *
 * @author Justin Wong
 */

typealias GridPosition = Pair<Int, Int>

class Maze(val arr: Array<Array<Cell>>) {
    private val numRows: Int = arr.size
    private val numCols: Int = arr[0].size

    fun display() = print(toString())

    fun getNeighboursMap(pos: GridPosition): Map<Direction, GridPosition> = Direction.values()
        .map { dir -> dir to GridPosition(pos.first + dir.dR, pos.second + dir.dC)}
        .toMap()
        .filterValues { nPos -> inside(nPos.first, numRows) && inside(nPos.second, numCols)}

    override fun toString(): String {
        val numRows = arr.size
        val numCols = arr[0].size
        val output = StringBuilder()
        (0 until numRows).forEach { row ->
            (0 until numCols).forEach { col ->
                val northBoundary = when {
                    !arr[row][col].canGoNorth() -> "+---"
                    else -> "+   "
                }
                output.append(northBoundary)
            }
            output.appendln("+")
            (0 until numCols).forEach { col ->
                val westBoundary = when {
                    !arr[row][col].canGoWest() -> "|   "
                    else -> "    "
                }
                output.append(westBoundary)
            }
            output.appendln("|")
        }

        // draw the bottom line
        (0 until numCols).forEach { _ ->
            output.append("+---")
        }
        output.append("+")
        return output.toString()
    }
}
