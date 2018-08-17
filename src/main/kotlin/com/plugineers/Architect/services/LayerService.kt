package com.plugineers.Architect.services

import com.google.common.collect.ImmutableList
import com.plugineers.Architect.entities.PlayerAction
import com.plugineers.Architect.entities.enums.PlayerAccess
import com.plugineers.Architect.repositories.LayerRepository
import com.plugineers.Architect.repositories.PlayerDataRepository
import org.bukkit.block.Block
import java.util.*
import javax.inject.Inject

class LayerService {
    @get:Inject
    private lateinit var layerRepository: LayerRepository
    @get:Inject
    private lateinit var playerDataRepository: PlayerDataRepository<*>

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

    public fun undoPlayerActions(uuid: UUID, name: String, amount: Int) {

    }

    public fun redoAction(uuid: UUID, name: String) {

    }
}
