package com.plugineers.Architect.commands

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.exceptions.BrushException
import com.plugineers.Architect.exceptions.CommandException
import com.plugineers.Architect.services.CommandServices
import com.plugineers.Architect.util.cmdManager.CommandHandler
import org.apache.commons.lang.StringUtils
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import javax.inject.Inject

class BrushCommand<T :  CoreBrush> : CommandHandler<T>() {
    override fun execute(sender: CommandSender?, str: String?, args: Array<out String>): Boolean {
        var player: Player = sender as Player
        if (args.isEmpty()) {
            val currentBrush: T = commandService.getPlayerBrush(player.uniqueId)
            val brushSize: Int = commandService.getCurrentSize(player.uniqueId)!!

            player.sendMessage("name: ${currentBrush.name}")
            player.sendMessage("size: $brushSize")
            player.sendMessage("Terraformer: $currentBrush.cu")
        }
        if (args.size == 1) {
            if (StringUtils.isNumeric(args[0])) {
                try {
                    commandService.setSize(player.uniqueId, args[0].toInt())
                } catch (e : CommandException) {
                    sender.sendMessage(e.getFormattedMessage())
                }
            } else {
                try {
                    //sets the brush.
                    commandService.setBrush(player.uniqueId, args[0])
                } catch (e : CommandException) {
                    sender.sendMessage(e.getFormattedMessage())
                }
            }
        }
        return true
    }
}
