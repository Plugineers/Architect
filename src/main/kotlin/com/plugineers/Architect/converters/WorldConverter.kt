package com.plugineers.Architect.converters

import org.bukkit.Bukkit
import org.bukkit.World
import org.jooq.Converter

class WorldConverter : Converter<String, World> {
    override fun from(databaseObject: String?): World {
        return Bukkit.getWorld(databaseObject)
    }

    override fun to(userObject: World?): String {
        return userObject.toString()
    }

    override fun fromType(): Class<String> {
        return String::class.java
    }

    override fun toType(): Class<World> {
        return World::class.java
    }
}

