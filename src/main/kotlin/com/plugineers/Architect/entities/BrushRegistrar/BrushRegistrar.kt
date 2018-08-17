package com.plugineers.Architect.entities.BrushRegistrar


import com.google.inject.Inject
import com.plugineers.Architect.repositories.BrushRepository

import java.util.function.Supplier

class BrushRegistrar<T : CoreBrush> {
    @get:Inject
    lateinit var property: String

    @get:Inject
    private lateinit var brushRepository: BrushRepository<T>

    fun registerBrush(supplier: Supplier<T>): BrushRegistrar<T> {
        //val table = c.annotations.find { it.annotationClass == Table::class } as? Table
        val brush = supplier::get.annotations.find { it.annotationClass == Brush::class } as? Brush

        val brushData: T = supplier.get()

        if (brush != null) {
            brushData.name = brush.name
            brushData.command = brush.command
            brushData.maxLiteSize = brush.liteMaxSize
            brushData.maxSize = brush.maxSize
            brushData.allowsCustomBrush = brush.allowsBrushSelection
        }
        brushRepository.registerBrush(brushData)
        return this
    }

}
