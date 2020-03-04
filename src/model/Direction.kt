package model

/**
 * An enum for directions in maze
 *
 * @author Justin Wong
 */
enum class Direction(val bit: Int,
                     val dx: Int,
                     val dy: Int) {


    N(1, 0, -1) {
        override val opposite: Direction
            get() = S
    },
    S(2, 0, 1) {
        override val opposite: Direction
            get() = N
    },
    E(4, 1, 0) {
        override val opposite: Direction
            get() = W
    },
    W(8, -1, 0) {
        override val opposite: Direction
            get() = E
    };

    abstract val opposite: Direction
}