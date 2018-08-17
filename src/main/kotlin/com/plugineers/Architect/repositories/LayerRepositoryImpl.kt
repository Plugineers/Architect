package com.plugineers.Architect.repositories

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import com.google.common.cache.RemovalListener
import com.google.common.collect.HashBasedTable
import com.google.common.collect.ImmutableTable
import com.google.common.collect.Table
import com.google.inject.Inject
import com.google.inject.Singleton
import com.plugineers.Architect.entities.Layer
import com.plugineers.Architect.entities.PlayerAction
import org.jooq.DSLContext
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

/**
 * LayerRepository is the cache storage method for all information pertaining to layers and undo functionality.
 * The undo functionallity is linked specifically to the layers, locked layers will stop all undo functionality.
 * In addition all layers are saved no matter if they are deleted or not, though the are tagged with the -Deleted name tag on them,
 * names are not unique and are specifically linked to the user.
 */
@Singleton
class LayerRepositoryImpl() : LayerRepository {
    @get:Inject
    lateinit var context: DSLContext


    final var maxLayersLoaded = 2
    private var stackData: LoadingCache<String, Stack<PlayerAction>>? = CacheBuilder.newBuilder()
            .concurrencyLevel(4)
            .expireAfterWrite(30, TimeUnit.DAYS)
            .removalListener(RemovalListener<String, Stack<PlayerAction>> { r ->

            }).build(object : CacheLoader<String, Stack<PlayerAction>>() {
                override fun load(key: String?): Stack<PlayerAction> {
                    //context.select(Tables.PLAYER_ACTION.)
                    return Stack()
                }
            })

    private var layerTable: Table<UUID, String, Layer> = HashBasedTable.create()


    override fun getActiveLayer(uuid: UUID): Layer {
        return layerTable.rowMap()[uuid]?.entries!!.stream()
                .filter { v -> v.value.isSelected }
                .map { v -> v.value }
                .findFirst()
                .orElse(null)
    }

    private fun buildKey(uuid: UUID, name: String): String {
        return "${uuid.toString()}:name"
    }

    override fun getStack(uuid: UUID, layerName: String): Stack<PlayerAction>? {
        return stackData?.get(buildKey(uuid, layerName))
    }

    override fun addPlayerAction(uuid: UUID, name: String, action: PlayerAction) {
        val key: String = buildKey(uuid, name)
        if (!stackData?.asMap()!!.containsKey(key)) {
            stackData!!.put(key, Stack())
        }
        stackData?.get(key)!!.add(action)
    }

    override fun addPlayerActions(uuid: UUID, name: String, actions: Stack<PlayerAction>) {
        val key: String = buildKey(uuid, name)
        if (!stackData?.asMap()!!.containsKey(key)) {
            stackData!!.put(key, Stack())
        }
        actions.forEach { ac -> stackData?.get(key)!!.addAll(actions) }
    }

    override fun invalidateAction(uuid: UUID, name: String): Unit? {
        return stackData?.invalidate(buildKey(uuid, name))
    }

    override fun getLayer(uuid: UUID, name: String): Layer {
        return layerTable.get(uuid, name)
    }

    override fun getLayers(): ImmutableTable<UUID, String, Layer> {
        return ImmutableTable.copyOf(layerTable)
    }

    override fun evictAction(uuid: UUID, name: String): PlayerAction {
        return stackData!!.get(name).pop()
    }

    override fun evictActions(uuid: UUID, name: String, count: Int): List<PlayerAction> {
        val actionList: MutableList<PlayerAction> = ArrayList()
        val it = 0
        while (it <= count) {
            actionList.add(stackData?.get(buildKey(uuid, name))?.pop()!!)
        }
        return actionList
    }


}

