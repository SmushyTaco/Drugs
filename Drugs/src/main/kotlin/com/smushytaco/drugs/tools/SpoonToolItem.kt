package com.smushytaco.drugs.tools
import com.smushytaco.drugs.Drugs
import com.smushytaco.drugs.tools.base.AbstractToolItem
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items
import net.minecraft.item.ToolMaterial
class SpoonToolItem(material: ToolMaterial, settings: Settings): AbstractToolItem(material, settings) {
    override val itemToGive = Drugs.HEROIN_IN_A_NEEDLE
    override val itemsToRemove = arrayOf(Drugs.NEEDLE, Drugs.HEROIN)
    override fun condition(user: PlayerEntity) = user.inventory.contains(Items.FLINT_AND_STEEL.defaultStack)
    override fun conditionFailedText(user: PlayerEntity) = "You need ${Items.FLINT_AND_STEEL.name.string}."
}