package com.plugineers.Architect

import com.google.inject.Inject
import com.google.inject.Provides
import com.plugineers.Architect.entities.BrushRegistrar.CoreBrush
import com.plugineers.Architect.entities.brushes.Utils.*
import com.plugineers.Architect.entities.brushes.Utils.Array
import com.plugineers.Architect.entities.brushes.terraforming.*
import com.plugineers.Architect.services.BrushServices
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class Core<T : CoreBrush> : JavaPlugin() {
    @get:Inject
    lateinit var brushServices: BrushServices<in CoreBrush>

    override fun onEnable() {
        registerBrushes()


    }

    @Provides
    fun pluginProvider() : Plugin {
        return this;
    }

    fun registerBrushes() {
        brushServices.register(Ball())
                .register(BiomeTerraformerBrush())
                .register(CreateTemplateBrush())
                .register(Disc())
                .register(Erode())
                .register(FillCavern())
                .register(Melt())
                .register(Overlay())
                .register(Pull())
                .register(Push())
                .register(Smooth())
                .register(SplatterBall())
                .register(Tunnel())
                .register(Underlay())
                .register(Voxel())
                .register(Arc())
                .register(Array())
                .register(Copy())
                .register(CopyLine())
                .register(Explode())
                .register(Fillet())
                .register(Mirror())
                .register(Move())
                .register(MultiSelection())
                .register(Plane())
                .register(PolarArray())
                .register(PolyLine())
                .register(Pyramid())
                .register(Rombus())
                .register(Rotate())
                .register(Spawn())
                .register(Spiral())
                .register(Spline())
                .register(Stencil())
                .register(StencilList())
                .register(ThreePointCircle())
                .register(Triangle())
                .register(Trim())
    }

    override fun onDisable() {

    }


}
