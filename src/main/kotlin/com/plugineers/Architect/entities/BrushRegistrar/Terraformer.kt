package com.plugineers.Architect.entities.BrushRegistrar

import org.bukkit.util.Vector
import java.util.*

class Terraformer(tName: String, private val center: Vector, heightMap: Map<Integer, Integer>) {
    var name: String = tName
    var centerPoint: Vector = center
    private var heightMap: Map<Integer, Integer> = HashMap()


    val minBrushSize: Int
        get() = heightMap.size

    init {
        this.heightMap = heightMap
    }


}
