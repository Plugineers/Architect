package com.plugineers.Architect.commands.layers


import com.plugineers.Architect.util.cmdManager.Cmd
import com.plugineers.Architect.util.cmdManager.CommandHandler

import org.bukkit.command.CommandException
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Cmd(name = "ld", label = "delete", usage = "/ld <layername> <Primary Color> <Secondary Color>", aliases = arrayOf(), enabled = true, permission = "delete")
class Delete : CommandHandler() {

    override fun execute(sender: CommandSender, s: String, args: Array<String>): Boolean {
        if (args.size >= 1) {
            var player : Player = sender as Player
            try {
                layerServices.deleteLayer(player.uniqueId, args[0])
            } catch (e : CommandException) {
                sender.sendMessage(e.message)
            }
        } else {
            sender.sendMessage("Invalid argument size.")
        }
        return true
    }
}
