package com.plugineers.Architect

import com.google.inject.Provides
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class Core : JavaPlugin() {

    val plugin: Plugin
        @Provides
        get() = this

    override fun onEnable() {

    }

    override fun onDisable() {

    }

}
