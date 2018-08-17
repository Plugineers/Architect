package com.plugineers.Architect.entities.brushes.terraforming

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import com.plugineers.Architect.entities.geometry.BoundingCylinder
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * FillCavern Additional brush (fc) or fill cavern slightly alters the way filldown is handled, normally it would fill the blocks in a circlular
 * shape and remove all air spaces below it.  Fc is designed to leave the top Surface the same and remove all air spaces below it to bedrock.
 * /b filldown fc
 */
class FillCavern : CoreBrush() {
    override fun onCommand(player: Player, args: MutableList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val boundingCylinder: BoundingCylinder? = null


    private fun onCylinderAction(boundingCylinder: BoundingCylinder, world: World, clickPoint: Vector, mat: Material, brushSize: Int) {


    }
}