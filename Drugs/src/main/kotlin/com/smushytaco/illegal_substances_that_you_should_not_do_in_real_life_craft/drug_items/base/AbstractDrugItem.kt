package com.smushytaco.illegal_substances_that_you_should_not_do_in_real_life_craft.drug_items.base
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
abstract class AbstractDrugItem(settings: Settings): Item(settings) {
    companion object {
        private val Array<StatusEffectInstance>.fullClone: ArrayList<StatusEffectInstance>
            get() {
                val clone = arrayListOf<StatusEffectInstance>()
                this.forEach {
                    clone.add(StatusEffectInstance(it.effectType, it.duration, it.amplifier))
                }
                return clone
            }
        fun createStatusEffect(type: StatusEffect, duration: Int, amplifier: Int) = StatusEffectInstance(type, duration * 20, amplifier - 1)
    }
    protected abstract val statusEffects: Array<StatusEffectInstance>
    protected open fun statusEffectCondition(user: PlayerEntity) {}
    protected abstract val overdoseStatusEffects: Array<StatusEffectInstance>
    protected open fun overdoseStatusEffectCondition(user: PlayerEntity) {}
    protected abstract val soundEvent: SoundEvent
    protected abstract val soundCategory: SoundCategory
    private val volume = 1.0F
    private val pitch = 1.0F
    protected abstract fun condition(user: PlayerEntity): Boolean
    protected abstract fun conditionFailedText(user: PlayerEntity): String
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val itemStack = user.getStackInHand(hand)
        if (!condition(user)) {
            user.sendMessage(Text.of(conditionFailedText(user)), true)
            return TypedActionResult.fail(itemStack)
        }
        if (!user.isCreative) itemStack.decrement(1)
        if (!world.isClient) {
            val statusEffect = statusEffects.fullClone.random()
            if (user.hasStatusEffect(statusEffect.effectType)) {
                user.addStatusEffect(overdoseStatusEffects.fullClone.random())
                overdoseStatusEffectCondition(user)
            } else {
                user.addStatusEffect(statusEffect)
                statusEffectCondition(user)
            }
        }
        user.playSound(soundEvent, soundCategory, volume, pitch)
        return TypedActionResult.consume(itemStack)
    }
}