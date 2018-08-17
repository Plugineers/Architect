package com.plugineers.Architect.repositories

import com.google.common.collect.ImmutableTable
import com.plugineers.Architect.entities.Layer
import com.plugineers.Architect.entities.PlayerAction
import java.util.*

interface LayerRepository {
    fun getLayers(): ImmutableTable<UUID, String, Layer>
    fun getStack(uuid: UUID, layerName: String): Stack<PlayerAction>?
    fun addPlayerAction(uuid: UUID, name: String, action: PlayerAction)
    fun addPlayerActions(uuid: UUID, name: String, actions: Stack<PlayerAction>)
    fun invalidateAction(uuid: UUID, name: String): Unit?
    fun getLayer(uuid: UUID, name: String): Layer
    fun getActiveLayer(uuid: UUID): Layer
    fun evictAction(uuid: UUID, name: String): PlayerAction
    fun evictActions(uuid: UUID, name: String, count: Int): List<PlayerAction>
}