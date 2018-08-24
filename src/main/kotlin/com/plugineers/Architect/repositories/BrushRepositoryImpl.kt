package com.plugineers.Architect.repositories

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import com.google.gson.Gson
import com.google.inject.Inject
import com.google.inject.Singleton
import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.Terraformer
import org.bukkit.plugin.Plugin
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Singleton
class BrushRepositoryImpl<T : CoreBrush> : BrushRepository<T> {
    var gson: Gson = Gson()
    private var brushStorage: MutableMap<String?, T> = HashMap()
    @get:Inject
    lateinit var plugin: Plugin

    private var terraformerStorage: LoadingCache<String, Terraformer>? = CacheBuilder.newBuilder()
            .build(object : CacheLoader<String, Terraformer>() {
                override fun load(key: String): Terraformer {
                    val path: Path = Paths.get(plugin.dataFolder.toString() + "/templates/" + key + ".json")
                    val jsonResult: Terraformer = gson.fromJson(Files.readAllBytes(path).toString(), Terraformer::class.java)
                    return jsonResult
                }
            })

    override fun getBrush(name: String): T? {
        return brushStorage[name]
    }

    override fun registerBrush(brushData: T?) {
        brushStorage[brushData?.name] = brushData!!
    }

    override fun disableBrush(name: String): Boolean {
        if (brushStorage.containsKey(name)) {
            brushStorage.remove(name)
            return true
        }
        return false
    }

    override fun containsBrush(brush: String): Boolean {
        return brushStorage.containsKey(brush)
    }

    override fun containsTerraformer(template: String): Boolean {
        return terraformerStorage?.asMap()!!.containsKey(template)
    }

    override fun getTerraformer(terraformer: String): Terraformer? {
        return terraformerStorage?.get(terraformer)
    }

    override fun addNewTerraformer(terraformer: Terraformer) {
        terraformerStorage?.put(terraformer.name, terraformer)
    }
}