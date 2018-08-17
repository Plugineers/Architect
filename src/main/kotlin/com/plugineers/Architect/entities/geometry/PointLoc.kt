package com.plugineers.Architect.entities.geometry

import javafx.geometry.Point3D
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector

class PointLoc : Vector {
    var world: World? = null
        private set
    private var yaw: Float = 0.0f
    private var pitch: Float = 0.0f

    private val point: Point3D? = null

    val asLocation: Location
        get() = Location(world, getX(), getY(), getZ(), yaw, pitch)

    /**
     * Creates a new instance of `Point3D`.
     *
     * @param x     The X coordinate of the `Point3D`
     * @param y     The Y coordinate of the `Point3D`
     * @param z     The Z coordinate of the `Point3D`
     * @param world
     */
    constructor(x: Double, y: Double, z: Double, world: World) : super(x, y, z) {
        this.world = world
    }

    constructor(location: Location) : super(location.x, location.z, location.y) {
        this.world = location.world
        this.pitch = location.pitch
        this.yaw = location.yaw
    }
}
    
    
