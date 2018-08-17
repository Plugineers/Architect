package com.plugineers.Architect.entities.BrushRegistrar

annotation class Brush(
        val name: String,
        val command: String,
        val isEnabled: Boolean,
        val brushType: BrushType,
        val defaultBrush: String = "default",
        val allowsBrushSelection: Boolean = false,
        val maxSize: Int = 0,
        val liteMaxSize: Int = 200)
