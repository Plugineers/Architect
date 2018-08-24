package com.plugineers.Architect.services

import com.google.inject.Inject
import com.plugineers.Architect.entities.BrushRegistrar.Brush
import com.plugineers.Architect.entities.BrushRegistrar.BrushRegistrar
import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.repositories.BrushRepository
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.command.CommandException
import java.util.*
import java.util.function.Supplier

class BrushServices< T : CoreBrush> {
    @get:Inject
    lateinit var property: String

    @get:Inject
    private lateinit var brushRepository: BrushRepository<T>

    fun register(brushData : T): BrushServices<T> {
        //val table = c.annotations.find { it.annotationClass == Table::class } as? Table
        val brush = brushData.javaClass.annotations.find { it.annotationClass == Brush::class } as? Brush

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

    fun getBrush(brushName : String) : T? {
        if (!brushRepository.containsBrush(brushName)) {
            throw CommandException("The brush does not exist.")
        }
        return brushRepository.getBrush(brushName)
    }
    //TODO
    fun unloadBrush(brushName: String) : Boolean {
        if (!brushRepository.containsBrush(brushName)) {

        }
        return true
    }
}
