package com.plugineers.Architect.util.cmdManager

import com.google.common.base.Splitter
import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import com.google.inject.Singleton
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandMap
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

import java.lang.reflect.Field
import java.util.*
import java.util.function.Supplier

@Singleton
class RegisteredCommands<T : RegisteredCommand, C : CommandHandler> : Listener {
    protected var cmap: CommandMap? = null
    private val subCommands = ArrayListMultimap.create<String, C>()

    @EventHandler
    private fun onCommandExec(event: PlayerCommandPreprocessEvent) {
        var messages: MutableList<String> = LinkedList()
        messages = LinkedList(Splitter.on(" ").splitToList(event.message))

        if (messages.size >= 2) {

            val name = messages[0].replace("/", "")

            if (subCommands.containsKey(name)) {
                val second = messages[1]
                val options = subCommands.get(name).stream()
                        .filter { c -> c.name.equals(second, ignoreCase = true) }
                        .findFirst()
                messages.removeAt(0)
                messages.removeAt(0)
                if (options.isPresent) {
                    val c = options.get()
                    c.execute(event.player, c.name, messages.toTypedArray())
                }
            }
        } else {
            event.player.sendMessage("Invalid command.")
        }
    }

    fun with(regCmd: Supplier<C>): RegisteredCommands<*, *> {
        val c = regCmd.get()
        println(regCmd.get().javaClass.isAnnotationPresent(SubCommand::class.java))
        if (regCmd.get().javaClass.isAnnotationPresent(Cmd::class.java)) {
            val reg = regCmd.get().javaClass.getAnnotation(Cmd::class.java)
            val registeredCommand = RegisteredCommand(reg.name(), "", "", reg.usage(), Arrays.asList(*reg.aliases()))

            registerCommand(registeredCommand, reg)

        } else if (regCmd.get().javaClass.isAnnotationPresent(SubCommand::class.java)) {
            val reg = regCmd.get().javaClass.getAnnotation(SubCommand::class.java)
            c.name = reg.name()
            c.aliases = Arrays.asList(*reg.aliases())
            c.label = reg.label()
            c.permission = reg.permission()
            c.usage = reg.usage()
            subCommands.put(reg.parent(), c)
        }
        return this
    }

    private fun registerCommand(registeredCommand: RegisteredCommand, reg: Cmd) {
        try {
            val bukkitCommandMap = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
            bukkitCommandMap.isAccessible = true
            val commandMap = bukkitCommandMap.get(Bukkit.getServer()) as CommandMap
            if (reg.enabled()) {
                commandMap.register(reg.name(), registeredCommand)
            } else {
                commandMap.register(reg.name(), DisabledCommand(reg.name()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun <T> createDescription(regCmd: Supplier<T>): String {
        return if (regCmd.get().javaClass.isAnnotationPresent(Description::class.java)) {
            regCmd.get().javaClass.getAnnotation(Description::class.java).desc()
        } else ""
    }
}