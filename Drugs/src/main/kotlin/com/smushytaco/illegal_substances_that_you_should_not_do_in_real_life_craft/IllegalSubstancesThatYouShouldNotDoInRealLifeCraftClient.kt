package com.smushytaco.illegal_substances_that_you_should_not_do_in_real_life_craft
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
@Suppress("UNUSED")
@Environment(EnvType.CLIENT)
object IllegalSubstancesThatYouShouldNotDoInRealLifeCraftClient: ClientModInitializer {
    override fun onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(IllegalSubstancesThatYouShouldNotDoInRealLifeCraft.CANNABIS_CROP, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(IllegalSubstancesThatYouShouldNotDoInRealLifeCraft.COCA_CROP, RenderLayer.getCutout())
    }
}