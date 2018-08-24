package com.plugineers.Architect.repositories

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.Terraformer

interface BrushRepository<T : CoreBrush> {
    fun getBrush(name: String): T?

    fun registerBrush(brushData: T?)

    fun disableBrush(name: String): Boolean

    fun containsBrush(brush: String): Boolean

    fun containsTerraformer(template: String): Boolean

    fun getTerraformer(terraformer: String): Terraformer?

    fun addNewTerraformer(terraformer: Terraformer)
}
