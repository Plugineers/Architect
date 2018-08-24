package com.plugineers.Architect.entities


import com.plugineers.Architect.entities.enums.PlayerAccess
import org.bukkit.Material
import org.bukkit.block.data.BlockData
import java.util.*

class User(var uuid: UUID, var playerAccess: PlayerAccess, var size: Int, var terraformer: Terraformer?, var material: Material, var blockData: BlockData) {

    fun asNoAccessUser(uuid: UUID): User {
        this.uuid = uuid
        playerAccess = PlayerAccess.NONE
        size = 0
        material = Material.AIR
        return this
    }


}
