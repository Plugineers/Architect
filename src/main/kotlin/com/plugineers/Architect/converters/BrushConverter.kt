package com.plugineers.Architect.converters

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.repositories.BrushRepository
import org.jooq.Converter
import javax.inject.Inject

class BrushConverter<T : CoreBrush> : Converter<String, T> {
    @get:Inject
    private lateinit var brushRepo: BrushRepository<T>

    override fun from(brushName: String): T? {
        return brushRepo.getBrush(brushName)
    }

    override fun to(userObject: T): String? {
        return userObject.name
    }

    override fun fromType(): Class<String>? {
        return String::class.java
    }

    override fun toType(): Class<T>? {
        return null
    }
}
