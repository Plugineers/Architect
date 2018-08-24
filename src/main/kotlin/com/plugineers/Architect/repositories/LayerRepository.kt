package com.plugineers.Architect.repositories

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableTable
import com.plugineers.Architect.entities.Layer
import com.plugineers.Architect.entities.PlayerAction
import com.plugineers.Architect.generated.tables.records.LayerRecord
import org.jooq.TableField
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
    fun getAllLayers(uniqueId: UUID?): ImmutableList<Layer>
    fun createLayer(uuid: UUID, layer: Layer)
    fun hasLayer(uuid: UUID, layer: String): Boolean
    fun <T> update(uuid: UUID, layer : String, table: TableField<LayerRecord, T>,  value: T)
}