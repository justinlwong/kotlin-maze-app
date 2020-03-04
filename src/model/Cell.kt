package model

/**
 * A cell which contains information about the presence
 * of walls in any of the four [Direction]s
 *
 * @author Justin Wong
 */
data class Cell(var wallIndicator: Int) {
    fun canGoNorth(): Boolean = wallIndicator and 1 != 0
    fun canGoSouth(): Boolean = wallIndicator and 2 != 0
    fun canGoEast(): Boolean = wallIndicator and 4 != 0
    fun canGoWest(): Boolean = wallIndicator and 8 != 0
}