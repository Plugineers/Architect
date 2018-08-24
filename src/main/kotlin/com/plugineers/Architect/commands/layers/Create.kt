package com.plugineers.Architect.commands.layers


import com.plugineers.Architect.services.LayerService
import com.plugineers.Architect.util.cmdManager.CommandHandler
import com.plugineers.Architect.util.cmdManager.SubCommand
import org.bukkit.ChatColor
import org.bukkit.command.CommandException
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

import javax.inject.Inject

/**
 * Layer Create Command
 * @Usage:
 * @Coloring - The layer system is using the ingame bukkit color coding, however to combat the lack of colors two separate colors
 * can be assigned to a layers
 */
@SubCommand(name = "c", parent = "l", label = "create", usage = "/l c <layername> <Primary Color> <Secondary Color>", enabled = true, permission = "create")
class Create : CommandHandler() {
    @get:Inject lateinit var layerService : LayerService

    fun createNewLayer(player : Player, args : MutableList<String>) {
        var color : ChatColor = ChatColor.WHITE
        var color2 : ChatColor = ChatColor.WHITE

        if (args.size == 2) {
            color = ChatColor.valueOf(args[1])
        }
        if (args.size == 3) {
            color2 = ChatColor.valueOf(args[2])
        }
        try {
            layerService.createNewLayer(player.uniqueId, args[0], player.location, color, color2)
        } catch (e : CommandException) {
            player.sendMessage(e.message)
        }
    }

    override fun execute(sender: CommandSender?, name: String, args : Array<out String>): Boolean {
        if (args.size >= 1) {
            //l c <name>
            var player : Player = sender as Player
            if (args[1].equals("c") || args[1].equals("create")) {

            } else if (args[1].equals("d") || args.equals("delete")) {
                //l e <field> <name>
            } else if (args[1].equals("") || args.equals("")) {

            }

        }
        if (args.size >= 1) {
            var player : Player = sender as Player
            var color : ChatColor = ChatColor.WHITE
            var color2 : ChatColor = ChatColor.WHITE

            if (args.size == 2) {
                color = ChatColor.valueOf(args[1])
            }
            if (args.size == 3) {
                color2 = ChatColor.valueOf(args[2])
            }
            layerService.createNewLayer(player.uniqueId, args[0], player.location, color, color2)
        }
        return true
    }
}
