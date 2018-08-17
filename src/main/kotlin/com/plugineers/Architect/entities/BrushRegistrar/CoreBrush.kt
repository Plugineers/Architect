package com.plugineers.Architect.entities.BrushRegistrar

import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.ActionType
import com.plugineers.Architect.exceptions.BrushException
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.util.Vector

abstract class CoreBrush {
    /**
     * The name that will show up when a user does /b without any arguments.
     * @return [String]
     */
    /**
     * Sets Name
     * @param s
     */
    var name: String? = null
        public set
    /**
     * The command argument used in the /b command to set the brush.
     * @return [String]
     */
    var command: String? = null
        internal set
    /**
     * This is the global set value that can not be exceeded in any way, though if it's defined as zero it will be unlimited.
     * @return [Integer]
     */
    var maxSize: Int = 0
        internal set
    /**
     * The maximum size that a Lite user can reach with this brush, if there is a global value set in the configuration
     * this will be overwritten.
     * @return [Integer]
     */
    var maxLiteSize: Int = 0
        internal set
    private val brushTerraformer: Terraformer? = null


    var allowsCustomBrush: Boolean = false
        internal set


    /**
     * This function handles how commands are handled when the brush is called.
     * @param player
     * @param args
     */
    @Throws(BrushException::class)
    abstract fun onCommand(player: Player, args: MutableList<String>)

    internal fun configureDefaultBrush(name: String, command: String, maxSize: Int, max_lite_size: Int) {
        this.name = name
        this.command = command
        this.maxSize = maxSize
        this.maxLiteSize = max_lite_size
    }

    /**
     * Executor called by [com.plugineers.Architect.listeners.OnClickEvent] the /b <command> is being removed from the list.
     * @param user
     * @param actionType
     * @param world
     * @param clickPoint
     * @param isFaceClicked
    </command> */
    @Throws(BrushException::class)
    abstract fun onAction(user: User, actionType: ActionType, world: World, clickPoint: Vector, isFaceClicked: Boolean)


}
