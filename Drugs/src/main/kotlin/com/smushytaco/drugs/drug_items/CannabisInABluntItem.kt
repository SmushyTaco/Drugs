package com.smushytaco.drugs.drug_items
import com.smushytaco.drugs.drug_items.base.AbstractDrugItem
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
class CannabisInABluntItem(settings: Settings): AbstractDrugItem(settings) {
    override val statusEffects = arrayOf(
        createStatusEffect(StatusEffects.HUNGER, 60, 1),
        createStatusEffect(StatusEffects.MINING_FATIGUE, 15, 1),
        createStatusEffect(StatusEffects.SLOWNESS, 22, 1),
        createStatusEffect(StatusEffects.REGENERATION, 33, 2))
    override val overdoseStatusEffects = arrayOf(
        createStatusEffect(StatusEffects.BLINDNESS, 30, 2),
        createStatusEffect(StatusEffects.NAUSEA, 15, 2))
    override val soundEvent: SoundEvent = SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE
    override val soundCategory = SoundCategory.AMBIENT
    override fun condition(user: PlayerEntity) = user.inventory.contains(Items.FLINT_AND_STEEL.defaultStack)
    override fun conditionFailedText(user: PlayerEntity) = "What are you going to light it with?"
}