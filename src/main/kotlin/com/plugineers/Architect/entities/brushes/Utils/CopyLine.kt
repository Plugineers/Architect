package com.plugineers.Architect.entities.brushes.Utils


import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * The copy line brush acts the same as the array however it stacks based off of the line created by the user.
 * Arguments:
 * rot -> Allows rotation along the axis of the line
 * a -> Ignores air selection.
 * d -> distance between the stack along the line.
 * Clicks:
 * arrow:
 * 1 & 2 -> Selects region between the stack
 * click and holding will allow the user to draw the line or individual clicks.
 * gunpowder
 * executes the current line.
 *
 */
class CopyLine : CoreBrush() {
    override fun onCommand(player: Player, args: MutableList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
