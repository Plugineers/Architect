package com.plugineers.Architect.util.cmdManager

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.services.BrushServices
import com.plugineers.Architect.services.CommandServices
import com.plugineers.Architect.services.LayerService
import org.bukkit.command.defaults.BukkitCommand

import javax.inject.Inject
import java.util.ArrayList

abstract class CommandHandler<T : CoreBrush>() :  BukkitCommand("", "", "", ArrayList()) {
    @get:Inject lateinit var commandService: CommandServices<T>
    @get:Inject lateinit var brushServices: BrushServices<T>
    @get:Inject lateinit var layerServices: LayerService

    protected constructor(name: String, description: String, usageMessage: String, aliases: List<String>) : this() {

    }
}
