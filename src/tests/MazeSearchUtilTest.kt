package tests

import model.Cell
import model.Direction
import model.GridPosition
import model.Maze
import org.junit.Test
import utils.MAX_SCORE
import utils.aStarSearch
import utils.heuristicDistance
import utils.moveCost
import kotlin.test.assertEquals

class MazeSearchUtilTest {
    companion object {
        val MAZE = Maze(
            arrayOf(
                arrayOf(Cell(6), Cell(8)),
                arrayOf(Cell(5), Cell(8))
            )
        )

        val PATH: List<GridPosition> = listOf(GridPosition(0, 0), GridPosition(1, 0), GridPosition(1, 1))
        val COST = PATH.size - 1
        val START = GridPosition(0, 0)
        val END = GridPosition(1, 1)
        val HEURISTIC_START = GridPosition(0, 0)
        val HEURISTIC_END = GridPosition(5, 5)
        const val HEURISTIC_VAL = 0
    }

    @Test
    fun testSolveMaze() {
        val (path, cost) = aStarSearch(START, END, MAZE)
        assertEquals(PATH, path)
        assertEquals(COST, cost)
    }

    @Test
    fun testHeuristicDistance() {
        val dist = heuristicDistance(HEURISTIC_START, HEURISTIC_END)
        assertEquals(HEURISTIC_VAL, dist)
    }

    @Test
    fun testMoveCost() {
        val cost1 = MAZE.moveCost(START, Direction.N)
        assertEquals(MAX_SCORE, cost1)
        val cost2 = MAZE.moveCost(START, Direction.E)
        assertEquals(1, cost2)
        val cost3 = MAZE.moveCost(START, Direction.W)
        assertEquals(MAX_SCORE, cost3)
        val cost4 = MAZE.moveCost(START, Direction.S)
        assertEquals(1, cost4)
    }
}