package com.plugineers.Architect.entities

import org.bukkit.World
import org.bukkit.block.Block
import java.time.LocalDateTime

class PlayerAction(currentWorld: World, currentSelection: Boolean) {
    var execTime: LocalDateTime = LocalDateTime.now()
    var world: World = currentWorld
    var isCurrent: Boolean = false
    var modifiedBlocks: MutableList<Block> = ArrayList()
}
