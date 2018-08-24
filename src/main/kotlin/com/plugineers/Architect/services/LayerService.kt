package com.plugineers.Architect.services

import com.google.common.collect.ImmutableList
import com.plugineers.Architect.entities.Layer
import com.plugineers.Architect.entities.PlayerAction
import com.plugineers.Architect.entities.enums.PlayerAccess
import com.plugineers.Architect.generated.Tables
import com.plugineers.Architect.repositories.LayerRepository
import com.plugineers.Architect.repositories.PlayerDataRepository
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.command.CommandException
import java.util.*
import java.util.stream.Collectors
import javax.inject.Inject
import kotlin.collections.ArrayList

class LayerService {
    @get:Inject
    private lateinit var layerRepository: LayerRepository
    @get:Inject
    private lateinit var playerDataRepository: PlayerDataRepository<*>
    val defaultRegexCheck: Regex = "([A-Za-z0-9\\-_]+)".toRegex()

    public fun undoPlayerAction(uuid: UUID, name: Optional<String>) {
        if (!playerDataRepository.getPlayerData(uuid).playerAccess.equals(PlayerAccess.NONE)) {
            val currentLayer: String = name.orElse(layerRepository.getActiveLayer(uuid).layerName)

            var actions: PlayerAction = layerRepository.evictAction(uuid, currentLayer)

            //TODO
        }
    }

    //TODO this needs to be updated to allow for threading.
    private fun undoAction(blockList: ImmutableList<Block>) {
        blockList.forEach { b ->
            val block: Block = b.world.getBlockAt(b.location)
            block.setType(b.type, false)
            block.blockData = b.blockData
        }
    }

    fun undoActions(uuid: UUID, name: String, amount: Int) {

    }

    fun redoAction(uuid: UUID, name: String) {

    }

    fun redoAction(uuid: UUID, name: String, amount: Int) {

    }

    fun getAllLayers(uniqueId: UUID?, page: Int): MutableList<String> {
        val resultsPerPage: Long = 10
        val layerData: ImmutableList<Layer> = layerRepository.getAllLayers(uniqueId)
        val offset: Long = (page - 1) * resultsPerPage + 1

        return layerData.stream()
                .skip(offset)
                .limit(resultsPerPage)
                .sorted { s1, s2 -> s1.layerName.compareTo(s2.layerName) }
                .map { l -> "${l.primaryColor}  ${l.layerName} ${l.isLocked} ${l.isVisible}" }
                .collect(Collectors.toList())
    }

    fun getNearbyLayers(uniqueId: UUID, playerLocation: Location, distance: Int = 100, page: Int = 0) : List<Layer> {
        return layerRepository.getAllLayers(uniqueId).stream()
                .filter { p -> playerLocation.distance(playerLocation) <= distance }
                .sorted {s1, s2 -> s1.layerName.compareTo(s2.layerName)}
                .collect(Collectors.toList())
    }

    //white white is default.
    fun createNewLayer(uuid: UUID, name: String, baseLocation: Location, primaryColor: ChatColor = ChatColor.WHITE, secondaryColor: ChatColor = ChatColor.WHITE) {
        var errorMessage: String = ""
        if (name.matches(defaultRegexCheck)) {
            if (layerRepository.getAllLayers(uuid).stream().noneMatch { m -> m.primaryColor.equals(ChatColor.WHITE) && m.secondaryColor.equals(ChatColor.WHITE) }) {
                throw CommandException("${ChatColor.RED} White / White cannot be used and is reserved as default.")
            }
            if (layerRepository.getAllLayers(uuid).stream().noneMatch { m ->
                        m.primaryColor.equals(primaryColor) && m.secondaryColor.equals(secondaryColor) &&
                                m.primaryColor.equals(ChatColor.WHITE) && m.secondaryColor.equals(ChatColor.WHITE)
                    }) {
                throw CommandException("$primaryColor ${primaryColor.name} ${ChatColor.RED} and $secondaryColor $secondaryColor.name ${ChatColor.RED} are already being used by another layer.")
            }
            var layer: Layer = Layer(name, baseLocation, primaryColor, secondaryColor, ArrayList(), true, true, false)
            layerRepository.createLayer(uuid, layer)
        } else {
            throw CommandException("${ChatColor.RED} Invalid format, please make sure you have letters, nummbers or dashes.")
        }
    }

    fun deleteLayer(uuid : UUID, layerName : String) {
        if (layerRepository.hasLayer(uuid, layerName)) {
            var newName : String = " ${layerName}:${System.nanoTime()}"
            layerRepository.update(uuid, layerName, Tables.LAYER.LAYER_NAME, "${layerName}:${System.nanoTime()}")
        }
    }


}
