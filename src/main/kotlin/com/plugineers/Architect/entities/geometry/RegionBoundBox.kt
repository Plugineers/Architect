package com.plugineers.Architect.entities.geometry

import javafx.geometry.BoundingBox
import org.bukkit.World
import org.bukkit.util.Vector

class RegionBoundBox(world: World, floor: Vector, ceil: Vector, val yaw: Float, val pitch: Float) :
        BoundingBox(floor.x, floor.z, floor.z, ceil.x - floor.x, ceil.z - floor.z,
        ceil.y - floor.y) {
    val world: World? = world


}
