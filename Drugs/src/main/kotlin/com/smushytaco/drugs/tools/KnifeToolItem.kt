package com.smushytaco.drugs.tools
import com.smushytaco.drugs.Drugs
import com.smushytaco.drugs.tools.base.AbstractToolItem
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ToolMaterial
class KnifeToolItem(material: ToolMaterial, settings: Settings): AbstractToolItem(material, settings) {
    override val itemToGive = Drugs.COCAINE
    override val itemsToRemove = arrayOf(Drugs.COCA_LEAF)
    override fun condition(user: PlayerEntity) = true
    override fun conditionFailedText(user: PlayerEntity): String? = null
}