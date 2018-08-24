package com.plugineers.Architect.services

import com.google.inject.Inject
import com.plugineers.Architect.entities.BrushRegistrar.Brush
import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.Terraformer
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import com.plugineers.Architect.entities.enums.PlayerAccess
import com.plugineers.Architect.entities.enums.Response
import com.plugineers.Architect.entities.enums.Response.*
import com.plugineers.Architect.exceptions.CommandException
import com.plugineers.Architect.repositories.BrushRepository
import com.plugineers.Architect.repositories.PlayerDataRepository
import com.plugineers.Architect.util.Tuple2
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.configuration.Configuration
import org.bukkit.entity.Player
import org.bukkit.util.Vector
import java.util.*
import java.util.logging.Level


class CommandServices<T : CoreBrush> {
    @get:Inject
    private lateinit var playerData: PlayerDataRepository<T>
    @get:Inject
    private lateinit var brushRepo: BrushRepository<T>
    @get:Inject
    private lateinit var configuration: Configuration


    /**
     * @return [Response.SUCCESS], [Response.EXCEEDS_LITE_MAX_SIZE]
     */
    @Throws(CommandException::class)
    fun setSize(uuid: UUID, size: Int): Int {
        val user: User? = playerData.getPlayerData(uuid)

        val brush: T = playerData.getCurrentBrush(uuid)


        if (user?.playerAccess?.equals(PlayerAccess.LITE)!! && brush.maxLiteSize > user.size) {
            user.size = brush.maxLiteSize
            return brush.maxLiteSize

        } else if (user.playerAccess == PlayerAccess.RESIDENT && brush.maxSize > size) {
            user.size = brush.maxSize
            return brush.maxSize
        } else {
            user.size = size
        }
        playerData.saveUser(uuid, user)
        return size
    }

    /**
     * @args [UUID] userId, [String] brush Name
     * @response [Tuple2]<[Response.SUCCESS] | [Response.EXCEEDS_LITE_MAX_SIZE] | [Response.INVALID_BRUSH], [Int]>
     */
    @Throws(CommandException::class)
    fun setBrush(uuid: UUID, brushName: String) {
        val brush: T? = brushRepo.getBrush(brushName)
        val user: User? = playerData.getPlayerData(uuid)
        var response: Response? = null
        var retValue: Int = 0

        if (null != brush) {
            if (user?.playerAccess?.equals(PlayerAccess.LITE)!!) {
                if (brush.maxLiteSize > user.size) {
                    user.size = brush.maxLiteSize
                    retValue = brush.maxLiteSize
                    throw CommandException(Level.WARNING,"You have exceeded the max limit for lite user, defaulting to " +
                            "max size of ${brush.maxLiteSize}")
                }
            } else if (user.playerAccess == PlayerAccess.RESIDENT) {
                if (brush.maxSize > user.size) {
                    user.size = brush.maxSize
                    retValue = brush.maxSize
                    throw CommandException(Level.WARNING,"You have exceeded the max limit for lite user, defaulting to " +
                            "max size of ${brush.maxLiteSize}")
                }
                return
            }
        } else {
            throw CommandException(Level.WARNING, INVALID_BRUSH.defaultMessage())
        }
    }

    fun getCurrentSize(uuid: UUID): Int? {
        val user: User? = playerData.getPlayerData(uuid)
        return user?.size
    }

    fun getCurrentTerraformer(uuid: UUID) {

    }

    /**
     * @args uniqueId : [UUID], terrName [String]
     * @response [Response.SUCCESS], [Response.INVALID_TERRAFORMER], [Response.NO_CUSTOM_TERRAFORMER], [Response.NO_ACCESS]
     */
    fun setTerraformer(uniqueId: UUID?, terrName: String): Boolean {
        val terraformer: Terraformer? = brushRepo.getTerraformer(terrName)

        if (null != terraformer) {
            val user: User? = playerData.getPlayerData(uniqueId!!)
            user?.terraformer = terraformer
        }
        throw CommandException(Level.SEVERE, INVALID_TERRAFORMER.defaultMessage());
    }

    fun getCurrentMaterial(uniqueId: UUID?): Material? {
        val user: User = playerData.getPlayerData(uniqueId!!)

        if (null != user) {
            throw CommandException(Level.SEVERE, NO_ACCESS.defaultMessage())
        }
        return user.material
    }

    fun setMaterial(uniqueId: UUID?, material: Material): Boolean {
        val user: User? = playerData.getPlayerData(uniqueId!!)

        if (configuration.getList("mat.bannedMaterials").contains(material.name)) {
            throw CommandException(Level.SEVERE, "The material $material is on the banned list.")
        }
        user?.material = material
        return true
    }

    @Throws(CommandException::class)
    fun onBrushCommandExecute(player: Player, brushName: String, mutableList: MutableList<String>) {
        var user: User = playerData.getPlayerData(player.uniqueId)
        val brush: T? = brushRepo.getBrush(brushName)

        brush!!.onCommand(player, mutableList)
    }

    @Throws(CommandException::class)
    fun performBrush(uniqueId: UUID, world: World, clickedBlock: Vector, actionType: ActionType, isFace: Boolean) {
        val user: User = playerData.getPlayerData(uniqueId)
        val brush: T? = playerData.getCurrentBrush(uniqueId)
        brush?.onAction(user, actionType, world, clickedBlock, isFace)
    }

    fun getPlayerBrush(uniqueId: UUID?): T {
       return playerData.getCurrentBrush(uniqueId!!)
    }

}

