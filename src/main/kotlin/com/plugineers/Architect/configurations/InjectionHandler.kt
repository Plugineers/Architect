package com.plugineers.Architect.configurations

import com.google.inject.AbstractModule
import com.plugineers.Architect.repositories.BrushRepository
import com.plugineers.Architect.repositories.BrushRepositoryImpl
import com.plugineers.Architect.repositories.PlayerDataRepository
import com.plugineers.Architect.repositories.PlayerRepositoryImpl

class InjectionHandler : AbstractModule() {
    override fun configure() {
        bind(BrushRepository::class.java).to(BrushRepositoryImpl::class.java)
        bind(PlayerDataRepository::class.java).to(PlayerRepositoryImpl::class.java)


    }
}
