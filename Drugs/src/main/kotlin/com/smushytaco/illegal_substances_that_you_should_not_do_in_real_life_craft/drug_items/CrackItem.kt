package com.smushytaco.illegal_substances_that_you_should_not_do_in_real_life_craft.drug_items
import com.smushytaco.illegal_substances_that_you_should_not_do_in_real_life_craft.IllegalSubstancesThatYouShouldNotDoInRealLifeCraft
import com.smushytaco.illegal_substances_that_you_should_not_do_in_real_life_craft.drug_items.base.AbstractDrugItem
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
class CrackItem(settings: Settings): AbstractDrugItem(settings) {
    override val statusEffects = arrayOf(
        createStatusEffect(StatusEffects.SPEED, 30, 3),
        createStatusEffect(StatusEffects.STRENGTH, 60, 2))
    override val overdoseStatusEffects = arrayOf(
        createStatusEffect(StatusEffects.WITHER, 20, 5))
    override val soundEvent: SoundEvent = SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE
    override val soundCategory = SoundCategory.AMBIENT
    override fun condition(user: PlayerEntity) =
        user.inventory.contains(IllegalSubstancesThatYouShouldNotDoInRealLifeCraft.CRACK_PIPE.defaultStack) && user.inventory.contains(Items.FLINT_AND_STEEL.defaultStack)
    override fun conditionFailedText(user: PlayerEntity): String {
        val hasFlintAndSteel = user.inventory.contains(Items.FLINT_AND_STEEL.defaultStack)
        if (!hasFlintAndSteel && !user.inventory.contains(IllegalSubstancesThatYouShouldNotDoInRealLifeCraft.CRACK_PIPE.defaultStack)) {
            return "What are you going to smoke it and light it with?"
        } else if (!hasFlintAndSteel) {
            return "What are you going to light it with?"
        }
        return "What are you going to smoke it with?"
    }
}