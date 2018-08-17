package com.plugineers.Architect.commands

import com.google.inject.Inject
import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.services.CommandServices
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TerraformerCommand<T : CoreBrush> : CommandExecutor {
    @get:Inject
    private lateinit var commandServices: CommandServices<T>

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>): Boolean {
        val player: Player = sender as Player
        if (args.isEmpty()) {

        } else {

        }
        return true
    }
}