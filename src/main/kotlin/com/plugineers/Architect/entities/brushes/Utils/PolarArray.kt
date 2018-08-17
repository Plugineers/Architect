package com.plugineers.Architect.entities.brushes.Utils


import com.google.common.base.Splitter
import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.PlayerAction
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import javafx.geometry.Point3D
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * Polar array takes a selected object and rotates it around a set axis and rotates it based off the number of count
 * /b parray r5 | d5 # igr
 * # by using r (radius) or d (distance) you can specify the circle that will have the items rotated along.
 * #  after radius specifies how many times the item will be rotated on the outside of the circle
 * Arguments:
 * igr: or ignore rotation will not rotate the object along the circle.
 * a: or air will ignore and not paste within air space.
 *
 * Click:
 * 1 & 2 -> These clicks specify the selection area of whatever you're tryin to copy.
 * 3 -> The third click depending on whether or not you click the top of the block or the side will either
 * flip it horizontally or vertically.
 *
 */
class PolarArray : CoreBrush() {
    private val firstPoint: Point3D? = null
    private val secondPoint: Point3D? = null
    private val copyPoint: Point3D? = null
    private var range: Int = 0
    private var isRange: Boolean = false

    override fun onCommand(player: Player, args: MutableList<String>) {
        if (args.size === 1) {
            if (args[0].contains("r") || args[0].contains("d")) {
                val split = Splitter.fixedLength(1).splitToList(args[0])
                isRange = if (args[0] == "r") true else false
                range = Integer.valueOf(split[1])
                return
            }
        }
    }

    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    @Override
    fun onAction(clickType: PlayerAction, world: World, clickPoint: Point3D, isFaceClicked: Boolean) {


    }
}