package com.plugineers.Architect.commands

import com.google.inject.Inject
import com.plugineers.Architect.services.CommandServices
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class VoxelCommand : CommandExecutor {

    @get:Inject
    private lateinit var commandService: CommandServices<*>

    private val currentValue: Int = 0

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player: Player = sender as Player
        if (args.isEmpty()) {
            var returnVal: Material? = commandService.getCurrentMaterial(player.uniqueId)


        } else {
            val material: Material = Material.getMaterial(args[0])
            var response: Boolean = commandService.setMaterial(player.uniqueId, material)


        }
        return true
    }

}
