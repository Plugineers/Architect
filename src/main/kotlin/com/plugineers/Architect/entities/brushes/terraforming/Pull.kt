package com.plugineers.Architect.entities.brushes.terraforming

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.PlayerAction
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import javafx.geometry.Point3D
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector

class Pull : CoreBrush() {
    override fun onCommand(player: Player, args: MutableList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Override
    fun onAction(clickType: PlayerAction, world: World, clickPoint: Point3D, isFaceClicked: Boolean) {

    }


}
