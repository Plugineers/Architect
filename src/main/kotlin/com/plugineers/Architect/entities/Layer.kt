package com.plugineers.Architect.entities

import org.bukkit.block.Block
import java.awt.Color
import java.util.*

class Layer(name: String, col: Color, blockData: MutableMap<Integer, Block>) {
    var layerName: String = name
    var color: Color = col
    var isSelected: Boolean = false
    var history = TreeMap<String, PlayerAction>()
    private var stack: Stack<PlayerAction> = Stack()
    private var redoStack: Stack<PlayerAction> = Stack()
}