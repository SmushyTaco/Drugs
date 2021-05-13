package com.smushytaco.drugs.drug_items
import com.smushytaco.drugs.Drugs
import com.smushytaco.drugs.drug_items.base.AbstractDrugItem
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
class LineOfCocaineItem(settings: Settings): AbstractDrugItem(settings) {
    override val statusEffects = arrayOf(
        createStatusEffect(StatusEffects.SPEED, 15, 3),
        createStatusEffect(StatusEffects.STRENGTH, 30, 2))
    override val overdoseStatusEffects = arrayOf(
        createStatusEffect(StatusEffects.WITHER, 10, 5))
    override val soundEvent: SoundEvent = SoundEvents.BLOCK_SAND_BREAK
    override val soundCategory = SoundCategory.AMBIENT
    override fun condition(user: PlayerEntity) = user.inventory.contains(Drugs.EMPTY_BLUNT.defaultStack)
    override fun conditionFailedText(user: PlayerEntity) = "What are you going to snort it with?"
}