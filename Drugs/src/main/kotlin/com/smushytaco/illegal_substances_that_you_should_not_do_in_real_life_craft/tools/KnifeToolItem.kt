package com.smushytaco.illegal_substances_that_you_should_not_do_in_real_life_craft.tools
import com.smushytaco.illegal_substances_that_you_should_not_do_in_real_life_craft.IllegalSubstancesThatYouShouldNotDoInRealLifeCraft
import com.smushytaco.illegal_substances_that_you_should_not_do_in_real_life_craft.tools.base.AbstractToolItem
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ToolMaterial
class KnifeToolItem(material: ToolMaterial, settings: Settings): AbstractToolItem(material, settings) {
    override val itemToGive = IllegalSubstancesThatYouShouldNotDoInRealLifeCraft.COCAINE
    override val itemsToRemove = arrayOf(IllegalSubstancesThatYouShouldNotDoInRealLifeCraft.COCA_LEAF)
    override fun condition(user: PlayerEntity) = true
    override fun conditionFailedText(user: PlayerEntity): String? = null
}