package model

/**
 * An enum for directions in maze
 *
 * @author Justin Wong
 */
enum class Direction(val bit: Int,
                     val dR: Int,
                     val dC: Int) {


    N(1, -1, 0) {
        override val opposite: Direction
            get() = S
    },
    S(2, 1, 0) {
        override val opposite: Direction
            get() = N
    },
    E(4, 0, 1) {
        override val opposite: Direction
            get() = W
    },
    W(8, 0, -1) {
        override val opposite: Direction
            get() = E
    };

    abstract val opposite: Direction
}