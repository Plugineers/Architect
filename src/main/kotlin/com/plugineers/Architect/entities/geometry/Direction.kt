package com.plugineers.Architect.entities.geometry

enum class Direction private constructor(name: String, private val abreviation: String) {
    NORTH("north", "n"),
    EAST("east", "e"),
    SOUTH("south", "s"),
    WEST("west", "w");

    companion object {
        fun getDirection(direction: String): Direction? {
            for (dir in values()) {
                if (direction.equals(dir)) {
                    return dir
                }
            }
            return null
        }
    }


}
