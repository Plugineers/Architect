package com.plugineers.Architect.entities.brushes.Utils

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import com.plugineers.Architect.entities.geometry.PointLoc
import com.plugineers.Architect.entities.geometry.RegionBoundBox
import com.plugineers.Architect.repositories.PlayerDataRepository
import javafx.geometry.Point3D
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.util.Vector
import javax.inject.Inject

/**
 * Copy brush
 * Usage: /b cp
 * Left Click: Selection
 * Right Click: Pasting
 * Perfomers:
 * tpc -> Three Point copy instead of using two points the first two points select the region the third is the copy
 * point.
 */
class Copy : CoreBrush() {


    @get:Inject
    private lateinit var playerDataRepository: PlayerDataRepository<*>
    private val world: World? = null
    private var firstPoint: Point3D? = null
    private var boundingBox: RegionBoundBox? = null
    private var copyPoint: PointLoc? = null
    private val blockData: Map<Point3D, Block>? = null

    private var threePointSelection = false

    override fun onCommand(player: Player, args: MutableList<String>) {
        if (args[0].contains("tpc")) {
            threePointSelection = true
        }
    }

    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


//    @Override
//    fun onAction(player: Player, actionType: ActionType, world: World, clickPoint: PointLoc, isFaceClicked: Boolean) {
//        if (actionType.equals(ActionType.WAND)) {
//            if (null == firstPoint) {
//                this.firstPoint = clickPoint
//            } else if (null != firstPoint) {
//                boundingBox = RegionBoundBox(world, firstPoint, clickPoint, clickPoint.getYaw(), clickPoint.getPitch())
//                if (threePointSelection) {
//                    player.sendMessage("Bounding box created, select copy point.")
//                } else {
//                    player.sendMessage("Selection has been copied.")
//                    copy()
//                    playerDataRepository!!.update(player.getUniqueId(), this)
//                }
//            } else if (null != boundingBox) {
//                copyPoint = clickPoint
//                copy()
//                playerDataRepository!!.update(player.getUniqueId(), this)
//                player.sendMessage("Selection has been copied.")
//
//            }
//        } else {
//            if (threePointSelection) {
//                val difference = boundingBox!!.getDifference()
//                for (i in 6 downTo 0 step 2)
//                for (x in clickPoint.blockX downTo   )
//                for (x in clickPoint.getX()..(clickPoint.getX() + difference.getX())) {
//                    for (y in boundingBox!!.getMaxY()..clickPoint.getY() + difference.getY()) {
//                        for (z in boundingBox!!.getMinZ()..clickPoint.getZ() + difference.getZ()) {
//                            val block = blockData!![Point3D(x, y, z)]
//                            val newBlock = boundingBox!!.getWorld().getBlockAt(x.toInt(), y.toInt(), z.toInt())
//                            newBlock.setBlockData(block.getBlockData())
//                            newBlock.setType(block.getType())
//
//                        }
//                    }
//                }
//
//            }
//
//        }
//    }
//
//    private fun copy() {
//        for (x in boundingBox!!.getMaxX()..boundingBox!!.getMaxX()) {
//            for (y in boundingBox!!.getMaxY()..boundingBox!!.getMaxY())
//                for (z in boundingBox!!.getMinZ()..boundingBox!!.getMaxZ()) {
//                    val block = boundingBox!!.getWorld().getBlockAt(x.toInt(), y.toInt(), z.toInt())
//                    this.blockData!!.put(Point3D(x, y, z), block)
//                }
//        }
//    }
}
