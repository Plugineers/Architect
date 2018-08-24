package com.plugineers.Architect.entities.brushes.terraforming

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import com.plugineers.Architect.entities.geometry.BoundingCylinder
import org.apache.commons.lang.StringUtils
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector

class Disc : CoreBrush() {
    private var boundingCylinder: BoundingCylinder? = null
    internal var trueCircle = false
    private var depth = 1


    override fun onCommand(player: Player, args: MutableList<String>) {
        for (arg in args) {
            if (arg.toLowerCase() == "d") {
                val `val` = arg.replace("d", "")

                if (StringUtils.isNumeric(arg)) {
                    depth = Integer.valueOf(`val`)
                    break
                }
                player.sendMessage("Invalid argument $arg")
                return
            }
        }

    }

    //var world : World, var centerPoint: Vector,var radius: Double, var depth: Int
    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        boundingCylinder = BoundingCylinder(world, clickPoint, user.size.toDouble(), depth)
        boundingCylinder!!.setMaterials(user.material, user.blockData)

    }


}
