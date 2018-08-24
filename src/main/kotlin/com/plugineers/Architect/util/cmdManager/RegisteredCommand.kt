package com.plugineers.Architect.util.cmdManager

import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class RegisteredCommand(name: String, parent: String, description: String, usageMessage: String, aliases: List<String>) : Command(name, description, usageMessage, aliases) {
    var parent: String? = null

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<String>): Boolean {
        return false
    }
}
