package com.plugineers.Architect.listeners

import com.plugineers.Architect.entities.enums.ActionType
import com.plugineers.Architect.services.CommandServices
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent
import javax.inject.Inject

class OnClickEvent {
    @get:Inject
    private lateinit var commandService: CommandServices<*>
    internal var fileConfiguration: FileConfiguration? = null

    @EventHandler
    fun onRightClickEvent(event: PlayerInteractEvent) {
        if ((event.item.type == Material.ARROW)) {
            //TODO Fully implement check.
            commandService.performBrush(event.player.uniqueId, event.player.world, event.clickedBlock.location.toVector(), ActionType.WAND, true)
        } else if (event.item.type == Material.SPECTRAL_ARROW) {
            commandService.performBrush(event.player.uniqueId, event.player.world, event.clickedBlock.location.toVector(), ActionType.GUN, true)

        }

    }
}