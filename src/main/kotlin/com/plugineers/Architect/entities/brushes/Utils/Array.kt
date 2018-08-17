package com.plugineers.Architect.entities.brushes.Utils


import com.google.common.base.Splitter
import com.plugineers.Architect.entities.BrushRegistrar.Brush
import com.plugineers.Architect.entities.BrushRegistrar.BrushType
import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import com.plugineers.Architect.entities.geometry.Direction
import jdk.nashorn.internal.ir.Block
import org.apache.commons.lang.StringUtils
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * Design notes
 * Example. /b array N10 E30 W5
 * N/E/S/W with number specifies the direction and the number of times. D# will specify the distance between if it's not already
 * done.
 * The first and second selection are the objects that it is being stacked, the third is the point where the array will be from.
 */

@Brush(name = "Array", command = "ar", isEnabled = true, brushType = BrushType.TOOL)
class Array : CoreBrush() {


    private val selection: MutableList<Block>? = null
    private val arrayDistance: MutableMap<Direction?, Int> = HashMap()
    private var distance: Int = 10


    override fun onCommand(player: Player, args: MutableList<String>) {
        if (args.isNotEmpty()) {


            val distanceOptional = args.stream()
                    .filter { v -> v.startsWith("d") }
                    .map { d ->
                        val temp = d.replace("d", "")
                        if (StringUtils.isNumeric(temp)) Integer.valueOf(temp) else null
                    }
                    .findFirst()
            distance = distanceOptional.orElse(0)!!

            args.stream()
                    .filter { value -> value.contains("n") || value.contains("s") || value.contains("e") || value.contains("w") }
                    .forEach { v ->
                        val split = Splitter.fixedLength(1).splitToList(v);
                        if (StringUtils.isNumeric(split[1])) {
                            arrayDistance[Direction.getDirection(split[0])] = Integer.valueOf(split[1])
                            args.remove(v)
                        } else {
                            player.sendMessage("$v is an invalid input type.")
                        }
                    }
        }

    }

    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {

    }
}
