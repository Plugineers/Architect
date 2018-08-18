package com.plugineers.Architect.entities.brushes.Utils

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * Select multiple edit regions or uneditable regions.
 * @DevNote this is being removed, instead of just selecting uneditable region player can create a selected region and create a layer and then
 set it to uneditable.  Also if this layer is the current selected layer for the player then it will  be the only one they can change.
 */
@Deprecated
class MultiSelection : CoreBrush() {
    override fun onCommand(player: Player, args: MutableList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
