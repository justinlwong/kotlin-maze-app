package tests

import model.Cell
import model.Maze
import org.junit.Test
import kotlin.test.assertEquals

class MazeTest {
    companion object {
        val MAZE = Maze(arrayOf(
            arrayOf(Cell(6), Cell(8)),
            arrayOf(Cell(5), Cell(8))
        ))

        val MAZE_STR =
            """
            +---+---+
            |       |
            +   +---+
            |       |
            +---+---+
            """.trimIndent()
    }

    @Test
    fun testDisplay() {
        assertEquals(MAZE_STR, MAZE.toString())
    }
}