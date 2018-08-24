package com.plugineers.Architect.converters

import org.bukkit.ChatColor
import org.jooq.Converter

class ChatColorConverter : Converter<String, ChatColor> {
    override fun from(databaseObject: String): ChatColor {
        return ChatColor.valueOf(databaseObject)
    }

    override fun to(userObject: ChatColor?): String {
        return userObject?.name!!
    }

    override fun fromType(): Class<String> {
        return String::class.java
    }

    override fun toType(): Class<ChatColor> {
        return ChatColor::class.java
    }
}
