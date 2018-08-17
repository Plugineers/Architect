package com.plugineers.Architect.converters

import org.bukkit.Material
import org.jooq.Converter

class MaterialConverter : Converter<String, Material> {
    override fun from(databaseObject: String?): Material {
        return Material.valueOf(databaseObject!!)
    }

    override fun to(userObject: Material?): String? {
        return userObject?.name
    }

    override fun fromType(): Class<String> {
        return String::class.java
    }

    override fun toType(): Class<Material> {
        return Material::class.java
    }
}
