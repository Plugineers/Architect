package com.plugineers.Architect.commands

import com.google.common.collect.ImmutableList
import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import com.plugineers.Architect.services.LayerService
import org.apache.commons.lang.StringUtils
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector
import javax.inject.Inject

class LayerCommand : CoreBrush() {
    @get:Inject
    lateinit var layerServices: LayerService

    override fun onCommand(player: Player, args: MutableList<String>) {
        if (args.size > 0) {
            if (args[0] == "li") { //list

            } else if (args[0] == "lia") {
                var page: Int = 0
                if (args.size == 2 && StringUtils.isNumeric(args[1])) {
                    page = args[1].toInt()
                }
                var layers: ImmutableList<String>
            } else if (args[0] == "c") { //create
                if (args[0].matches(Regex("^[a-zA-Z0-9_.-]*\$"))) {

                }
            } else if (args[0] == "r") { //remove

            } else {

            }
        }
    }

    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}