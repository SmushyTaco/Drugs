package com.smushytaco.drugs
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
@Suppress("UNUSED")
@Environment(EnvType.CLIENT)
object DrugsClient: ClientModInitializer {
    override fun onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Drugs.CANNABIS_CROP, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(Drugs.COCA_CROP, RenderLayer.getCutout())
    }
}