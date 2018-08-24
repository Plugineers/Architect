package com.plugineers.Architect.repositories

import com.google.common.base.Splitter
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import com.google.common.cache.RemovalListener
import com.google.common.collect.HashBasedTable
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableTable
import com.google.common.collect.Table
import com.google.inject.Inject
import com.google.inject.Singleton
import com.plugineers.Architect.entities.Layer
import com.plugineers.Architect.entities.PlayerAction
import com.plugineers.Architect.generated.Tables.*
import com.plugineers.Architect.generated.tables.records.LayerRecord
import org.bukkit.block.Block
import org.jooq.DSLContext
import org.jooq.Field
import org.jooq.TableField
import org.jooq.impl.UpdatableRecordImpl
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
                override fun load(key: String): Stack<PlayerAction> {
                    var layer : MutableList<String> = Splitter.on(":").splitToList(key)
                    var uuid : UUID = UUID.fromString(layer[0])

                    return getLayerActions(uuid, layer[1])
                }
            })

    private var layerTable: Table<UUID, String, Layer> = HashBasedTable.create()

    private fun getLayerActions(uuid : UUID, layer : String) : Stack<PlayerAction> {
        var map : MutableMap<PlayerAction, MutableList<Block>>? =
                context.select(PLAYER_ACTION.ID, PLAYER_ACTION.EXEC_TIME, PLAYER_ACTION.WORLD, PLAYER_ACTION.CURRENT)
                .from(PLAYER_ACTION)
                .leftJoin(BLOCK_DATA).on(BLOCK_DATA.ACTION_ID.eq(PLAYER_ACTION.ID))
                        .orderBy(PLAYER_ACTION.EXEC_TIME.desc())
                .fetchGroups(PlayerAction::class.java, Block::class.java)

        var stack : Stack<PlayerAction> = Stack()

        map?.forEach { (k, v)->
            k.modifiedBlocks.addAll(v)
            stack.push(k)
        }
        return stack
    }

    /*
     @Override
    public <T> boolean update(TableField<GroupDataRecord, T> accountDataRecordTableField, T t) {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() ->
                context.insertInto(gd)
                        .set(accountDataRecordTableField, t)
        ).handleAsync((query, throwable) -> query.execute());

        return completableFuture.join() > 0;
    }

     */
    override fun <T> update(uuid: UUID, layer_name: String, table : TableField<LayerRecord, T>,  value: T) {
        context.insertInto(table.table)
                .set(table, value)
                .execute()
    }

    override fun getActiveLayer(uuid: UUID): Layer {

        return layerTable.rowMap()[uuid]?.entries!!.stream()
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

    override fun getAllLayers(uniqueId: UUID?): ImmutableList<Layer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createLayer(uuid: UUID, layer: Layer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasLayer(uuid: UUID, layer: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

