package com.plugineers.Architect.commands

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.exceptions.BrushException
import com.plugineers.Architect.services.CommandServices
import org.apache.commons.lang.StringUtils
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import javax.inject.Inject

class BrushCommand<T : CoreBrush> : CommandExecutor {

    @get:Inject
    private lateinit var commandService: CommandServices<T>

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = sender as Player

        if (args.isEmpty()) {
            val brushSize = commandService.getCurrentSize(player.uniqueId)
            player.sendMessage("Your current brush size is: $brushSize")
        } else if (args.size == 1 && StringUtils.isNumeric(args[0])) {
            var returnData: Int = commandService.setSize(player.uniqueId, args[0].toInt())

        } else {
            val mutableList: MutableList<String> = args.toMutableList()

            commandService.onBrushCommandExecute(player, args[0], mutableList)
        }

        if (args.isEmpty()) {

        } else if (args.size == 1) {
            if (StringUtils.isNumeric(args[0])) {


            } else {
                try {

                } catch (be: BrushException) {
                    sender.sendMessage(be.message)
                }
            }
        } else {
            var response: Unit = commandService.setBrush(player.uniqueId, args[0])


        }
        return true
    }
}
