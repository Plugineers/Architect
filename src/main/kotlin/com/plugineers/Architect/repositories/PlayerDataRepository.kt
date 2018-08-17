package com.plugineers.Architect.repositories

import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import java.util.*

interface PlayerDataRepository<out T : CoreBrush> {
    fun update(uuid: UUID, user: User): Boolean
    fun getPlayerData(uuid: UUID): User
    fun invalidateData(uuid: UUID)
    fun addNewUser(uuid: UUID, user: User): Boolean
    fun saveUser(uuid: UUID, user: User)
    fun getCurrentBrush(uuid: UUID): T
}
