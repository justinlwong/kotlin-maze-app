package utils

import model.Direction
import model.GridPosition
import model.Maze
import kotlin.math.abs

const val MAX_SCORE = 99999999

/**
 * Extension function to calculate move cost of going to neighbors inside [Maze]
 */
fun Maze.moveCost(pos: GridPosition, direction: Direction): Int {
    val canGo: Boolean = when (direction) {
        Direction.N -> arr[pos.first][pos.second].canGoNorth()
        Direction.E -> arr[pos.first][pos.second].canGoEast()
        Direction.W -> arr[pos.first][pos.second].canGoWest()
        Direction.S -> arr[pos.first][pos.second].canGoSouth()
    }
    return if (canGo) 1 else MAX_SCORE
}

/**
 * Calculates heuristic distance for A*star
 */
fun heuristicDistance(start: GridPosition, finish: GridPosition): Int {
    val dr = abs(start.first - finish.first)
    val dc = abs(start.second - finish.second)
    return (dr + dc) + (-2) * minOf(dr, dc)
}

/**
 * Algorithm to compute cost and path to go through maze
 */
fun aStarSearch(start: GridPosition, finish: GridPosition, maze: Maze): Pair<List<GridPosition>, Int> {

    /**
     * Use the cameFrom values to Backtrack to the start position to generate the path
     */
    fun generatePath(currentPos: GridPosition, cameFrom: Map<GridPosition, GridPosition>): List<GridPosition> {
        val path = mutableListOf(currentPos)
        var current = currentPos
        while (cameFrom.containsKey(current)) {
            current = cameFrom.getValue(current)
            path.add(0, current)
        }
        return path.toList()
    }

    val openVertices = mutableSetOf(start)
    val closedVertices = mutableSetOf<GridPosition>()
    val costFromStart = mutableMapOf(start to 0)
    val estimatedTotalCost = mutableMapOf(start to heuristicDistance(start, finish))

    val cameFrom = mutableMapOf<GridPosition, GridPosition>()  // Used to generate path by back tracking

    while (openVertices.size > 0) {

        val currentPos = openVertices.minBy { estimatedTotalCost.getValue(it) }!!

        // Check if we have reached the finish
        if (currentPos == finish) {
            // Backtrack to generate the most efficient path
            val path = generatePath(currentPos, cameFrom)
            return Pair(path, estimatedTotalCost.getValue(finish)) // First Route to finish will be optimum route
        }

        // Mark the current vertex as closed
        openVertices.remove(currentPos)
        closedVertices.add(currentPos)

        maze.getNeighboursMap(currentPos)
            .filterNot { entry -> closedVertices.contains(entry.value) }  // Exclude previous visited vertices
            .forEach { entry ->
                val neighbourDir = entry.key
                val neighbourPos = entry.value
                val score = costFromStart.getValue(currentPos) + maze.moveCost(currentPos, neighbourDir)
                if (score < costFromStart.getOrDefault(neighbourPos, MAX_SCORE)) {
                    if (!openVertices.contains(neighbourPos)) {
                        openVertices.add(neighbourPos)
                    }
                    cameFrom[neighbourPos] = currentPos
                    costFromStart[neighbourPos] = score
                    estimatedTotalCost[neighbourPos] = score + heuristicDistance(neighbourPos, finish)
                }
            }
    }

    throw IllegalArgumentException("No Path from Start $start to Finish $finish")
}