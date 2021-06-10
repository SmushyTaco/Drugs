package com.smushytaco.illegal_substances_that_you_should_not_do_in_real_life_craft.tools
import com.smushytaco.illegal_substances_that_you_should_not_do_in_real_life_craft.IllegalSubstancesThatYouShouldNotDoInRealLifeCraft
import com.smushytaco.illegal_substances_that_you_should_not_do_in_real_life_craft.tools.base.AbstractToolItem
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items
import net.minecraft.item.ToolMaterial
class SpoonToolItem(material: ToolMaterial, settings: Settings): AbstractToolItem(material, settings) {
    override val itemToGive = IllegalSubstancesThatYouShouldNotDoInRealLifeCraft.HEROINE_IN_A_NEEDLE
    override val itemsToRemove = arrayOf(IllegalSubstancesThatYouShouldNotDoInRealLifeCraft.NEEDLE, IllegalSubstancesThatYouShouldNotDoInRealLifeCraft.HEROINE)
    override fun condition(user: PlayerEntity) = user.inventory.contains(Items.FLINT_AND_STEEL.defaultStack)
    override fun conditionFailedText(user: PlayerEntity) = "You need ${Items.FLINT_AND_STEEL.name.string}."
}