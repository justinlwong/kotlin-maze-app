package model

import java.lang.StringBuilder

/**
 * A wrapper class for maze which is a 2-D array for [Cell]s
 *
 * @author Justin Wong
 */
class Maze(private val arr: Array<Array<Cell>>) {
    val numRows: Int = arr.size
    val numCols: Int = arr[0].size

    fun display() {
        print(toString())
    }

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
