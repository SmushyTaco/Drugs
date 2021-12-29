package com.smushytaco.drugs.drug_items
import com.smushytaco.drugs.drug_items.base.AbstractDrugItem
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
class HeroinInANeedleItem(settings: Settings): AbstractDrugItem(settings) {
    override val statusEffects = arrayOf(
        createStatusEffect(StatusEffects.HASTE, 15, 2),
        createStatusEffect(StatusEffects.SATURATION, 30, 2))
    override val overdoseStatusEffects = arrayOf(
        createStatusEffect(StatusEffects.NAUSEA, 30, 2),
        createStatusEffect(StatusEffects.LEVITATION, 15, 1))
    override val soundEvent: SoundEvent = SoundEvents.ITEM_CROSSBOW_SHOOT
    override val soundCategory = SoundCategory.PLAYERS
    override fun condition(user: PlayerEntity) = true
    override fun conditionFailedText(user: PlayerEntity) = ""
}