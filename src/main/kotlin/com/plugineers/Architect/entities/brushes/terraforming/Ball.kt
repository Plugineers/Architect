package com.plugineers.Architect.entities.brushes.terraforming

import com.plugineers.Architect.entities.BrushRegistrar.Brush
import com.plugineers.Architect.entities.BrushRegistrar.BrushType
import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * Terraform: Ball
 * Type: Terraforming
 * allowCustomBrush: No
 * Example: /b ball
 * Performers:
 * mm -> MatMat
 * cc -> Combo Combo
 *
 */
@Brush(name = "Ball", command = "ball", isEnabled = true, brushType = BrushType.TERRAFORMING, allowsBrushSelection = false)
class Ball : CoreBrush() {
    override fun onCommand(player: Player, args: MutableList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
