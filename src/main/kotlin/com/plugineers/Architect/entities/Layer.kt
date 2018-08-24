package com.plugineers.Architect.entities

import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import java.awt.Color
import java.util.*

class Layer(var layerName: String, var baseLoc : Location, var  primaryColor: ChatColor, var secondaryColor : ChatColor,
            var  blockData: List<Block>, var isSelected : Boolean, var isVisible : Boolean, var isLocked : Boolean) {

    constructor(layerName: String, world : World, x : Int, y : Int, z : Int, mutableList : MutableList<Block>,  primaryColor: ChatColor, secondaryColor : ChatColor,
                blockData: List<Block>, isSelected : Boolean, isVisible : Boolean, isLocked : Boolean) :
            this(layerName,  Location(world, x.toDouble(),y.toDouble(),z.toDouble()), primaryColor, secondaryColor, mutableList, isSelected, isVisible, isLocked)
}