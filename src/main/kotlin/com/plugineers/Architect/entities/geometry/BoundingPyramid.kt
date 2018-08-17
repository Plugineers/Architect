package com.plugineers.Architect.entities.geometry


import org.bukkit.util.Vector

class BoundingPyramid(private var step: Int, private var size: Int) {
    val pyramidMap: MutableMap<Int, Int>? = null
    val centerPoint: Vector? = null
    private var isHollow: Boolean? = false


    //TODO FINISH
    fun constructAt(vector: Vector): Boolean {
        val xUpperBounds: Int = vector.blockX + size
        val yUpperBounds: Int = vector.blockZ + size
        val xLowerBounds: Int = vector.blockX - size;
        val yLowerBounds: Int = vector.blockZ - size;

        for (x in xLowerBounds..xUpperBounds) {
            for (y in yLowerBounds..yUpperBounds) {

            }
        }
        return true
    }
}
