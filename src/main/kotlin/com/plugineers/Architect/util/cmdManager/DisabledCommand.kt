package com.plugineers.Architect.util.cmdManager

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

import java.util.ArrayList

class DisabledCommand(name: String) : Command(name, "", "", ArrayList()) {
    var test = 0
    override fun execute(sender: CommandSender, commandLabel: String, args: Array<String>): Boolean {
        return false
    }


}
