package com.plugineers.Architect.entities.brushes.Utils

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * @DevNote this tool allows the user to take a building and blow it apart make modifications to it and then put it back
 * together again.
 */
class Explode : CoreBrush() {
    override fun onCommand(player: Player, args: MutableList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
