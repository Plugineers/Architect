package com.plugineers.Architect.entities.brushes.Utils

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * Arc brush will remain largely the same
 * @DevNote Command allows for a fully three dimensional rotation of the arch.
 * Note the command can be executed in multiple different ways:
 * Length of Chord:
 * given two points the application figures out and calculates the correct arc distance between the two points.
 * However this can also be applied so that the user specifies the length, this can either done by setting the length or
 * an additional click on the demonstration that allows them to visually see the changes.
 * Angle:
 * Given two points (the first being angle point and the second the actual arc start point) the user can either input a
 * angle they want set or visually move it around until they find a position that they want to hit.
 * @Angle
 * @Usage /b arc a
 */
class Arc : CoreBrush() {
    override fun onCommand(player: Player, args: MutableList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
