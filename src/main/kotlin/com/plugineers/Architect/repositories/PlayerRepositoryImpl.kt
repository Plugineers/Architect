package com.plugineers.Architect.repositories

import com.google.common.cache.*
import com.google.gson.Gson
import com.google.inject.Inject
import com.google.inject.Singleton
import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.User
import com.plugineers.Architect.entities.enums.PlayerAccess
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.plugin.Plugin
import org.jooq.DSLContext
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.concurrent.TimeUnit

@Singleton
class PlayerRepositoryImpl<T : CoreBrush> : PlayerDataRepository<T> {
    private val gson: Gson = Gson()
    @get:Inject
    lateinit var dslContext: DSLContext
    @get:Inject
    private lateinit var plugin: Plugin

    private val playerData: LoadingCache<UUID, User>? = CacheBuilder.newBuilder()
            .removalListener(RemovalListener<UUID, User> { notification ->
                saveUser(notification.key, notification.value)
            })
            .build(object : CacheLoader<UUID, User>() {
                override fun load(key: UUID): User {
                    return getUserData(key) as User
                }
            })


    private var currentBrush: Cache<UUID, T> = CacheBuilder.newBuilder()
            .concurrencyLevel(4)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build()


    private fun getUserData(uuid : UUID) : User {
        //TODO
    }
    //TODO implement
    private fun getBrushData(uuid: UUID): T? {
        return currentBrush.getIfPresent(uuid)
    }

    override fun getCurrentBrush(uuid: UUID): T {
        return getCurrentBrush(uuid)
    }


    override fun update(uuid: UUID, user: User): Boolean {
        if (playerData?.asMap()?.containsKey(uuid)!!) {
            playerData.put(uuid, user)
            return true
        }
        return false
    }

    override fun getPlayerData(uuid: UUID): User {
        return playerData!!.get(uuid)
    }

    override fun invalidateData(uuid: UUID) {
        playerData?.invalidate(uuid)
    }

    override fun addNewUser(uuid: UUID, user: User): Boolean {
        if (playerData != null) {
            playerData.put(uuid, user)
            saveUser(uuid, user)
            return true
        }
        return false
    }


    override fun saveUser(uuid: UUID, user: User) {
        val path: Path = Paths.get(plugin.dataFolder.toString() + "/access/" + uuid + ".json")
        val json: String = gson.toJson(user)
        Files.write(path, json.toByteArray())
    }


}
