package com.plugineers.Architect.entities.BrushRegistrar

import com.plugineers.Architect.entities.PlayerAction
import javafx.geometry.Point3D
import org.bukkit.World

/**
 * Utility brushes are designed as copy and pasta, Stencils, line brush, Polar Array.
 */

abstract class ToolBrush : CoreBrush() {
    abstract fun onAction(clickType: PlayerAction, world: World, clickPoint: Point3D, isFaceClicked: Boolean)
}
