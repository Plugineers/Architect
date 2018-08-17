package com.plugineers.Architect.entities.brushes.terraforming

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import org.apache.commons.lang.StringUtils
import org.bukkit.World
import org.bukkit.block.Biome
import org.bukkit.entity.Player
import org.bukkit.util.Vector
import java.util.*
import java.util.stream.Collectors

/**
 * usage: /b biome <insert name>
 * usage: /b biome <Search> (Note this will allow wildcard searches
 *
 *@startuml
*/
class BiomeTerraformerBrush : CoreBrush() {
    override fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val resultsPerPage = 8

    override fun onCommand(player: Player, args: MutableList<String>) {
        if (args.size > 0) {
            if (StringUtils.isNumeric(args[0])) {
                val page = Integer.valueOf(args[0])
                val offset = page * resultsPerPage - 1
                if (page > 0) {
                    Arrays.stream(Biome.values())
                            .limit(resultsPerPage.toLong())
                            .skip(offset.toLong())
                            .forEach { s -> player.sendMessage(s.name) }
                    return
                }
                player.sendMessage("The page must be greater than 1.")
                return

            } else {
                val biome = Biome.valueOf(args[0])
                if (null == biome.name) {
                    //TODO Implement actual proper regex match.
                    val biomeList = Arrays.stream(Biome.values())
                            .filter { b -> b.name.contains(args[0]) }
                            .collect(Collectors.toList())

                    if (biomeList.size > 0) {
                        biomeList.forEach { m -> m.name }
                    } else {
                        player.sendMessage("No matches found.")
                    }
                }
            }
            player.sendMessage("Invalid format.")
            return
        }
    }


}
