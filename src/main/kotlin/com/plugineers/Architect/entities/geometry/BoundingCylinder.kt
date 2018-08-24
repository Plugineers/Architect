package com.plugineers.Architect.entities.geometry

import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.data.BlockData
import org.bukkit.util.Vector

class BoundingCylinder(var world: World, private var centerPoint: Vector, private var radius: Double, private var depth: Int) {

    fun isWithinBounds(vector: Vector): Boolean {
        if (Math.pow(vector.x - centerPoint.x, 2.0) + Math.pow(vector.y - centerPoint.y, 2.0) < Math.pow(radius, 2.0)) {
            if (centerPoint.z <= vector.z && vector.z >= centerPoint.z - depth) {
                return true
            }
        }
        return false
    }

    fun setMaterials(material: Material, blockData: BlockData) {
        val r2 = (radius * radius).toDouble()
        val xLowPoint: Int = centerPoint.blockX - radius.toInt()
        val xHiPoint: Int = centerPoint.blockX + radius.toInt()
        val yLowPoint: Int = centerPoint.blockY - radius.toInt()
        val yHiPoint: Int = centerPoint.blockY + radius.toInt()

        for (x in xLowPoint..xHiPoint) {
            for (y in yLowPoint..yHiPoint) {
                val i = 0
                while (i < centerPoint.z - depth) {
                    val mat = world.getBlockAt(x.toInt(), i, y.toInt())
                    mat.type = material
                    mat.blockData = blockData
                }
            }
        }
    }

    fun applyChanges() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
