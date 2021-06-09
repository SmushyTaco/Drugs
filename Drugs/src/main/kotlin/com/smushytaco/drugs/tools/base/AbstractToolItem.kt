package com.smushytaco.drugs.tools.base
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import net.minecraft.item.ToolMaterial
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import java.util.*
abstract class AbstractToolItem(material: ToolMaterial, settings: Settings): ToolItem(material, settings) {
    companion object {
        private fun PlayerInventory.containsAll(items: Array<Item>): Boolean {
            items.forEach {
                if (!this.contains(it.defaultStack)) return false
            }
            return true
        }
        private fun PlayerInventory.missingItems(items: Array<Item>): ArrayList<Item> {
            val missingItems = arrayListOf<Item>()
            items.forEach {
                if (!this.contains(it.defaultStack)) missingItems.add(it)
            }
            return missingItems
        }
    }
    protected abstract val itemToGive: Item
    protected abstract val itemsToRemove: Array<Item>
    protected abstract fun condition(user: PlayerEntity): Boolean
    protected abstract fun conditionFailedText(user: PlayerEntity): String?
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        var tool = user.getStackInHand(hand)
        if (!user.inventory.containsAll(itemsToRemove) || user.inventory.emptySlot == -1 || tool.isEmpty || !tool.isDamageable || user !is ServerPlayerEntity || !condition(user)) {
            if (user.inventory.emptySlot == -1) {
                user.sendMessage(Text.of("Your inventory is full."), true)
            } else if (!user.inventory.containsAll(itemsToRemove)) {
                if (!world.isClient) return TypedActionResult.fail(tool)
                val stringBuilder = StringBuilder("You need ")
                val missingItems = user.inventory.missingItems(itemsToRemove)
                if (missingItems.size == 1) {
                    stringBuilder.append("${missingItems[0].name.string}.")
                } else if (missingItems.size == 2) {
                    stringBuilder.append("${missingItems[0].name.string} and ${missingItems[1].name.string}.")
                }
                user.sendMessage(Text.of(stringBuilder.toString()), true)
            } else if (!condition(user) && conditionFailedText(user) != null) {
                user.sendMessage(Text.of(conditionFailedText(user)), true)
            }
            return TypedActionResult.fail(tool)
        }
        itemsToRemove.forEach {
            val itemToRemoveReference = user.inventory.getStack(user.inventory.getSlotWithStack(it.defaultStack))
            itemToRemoveReference.decrement(1)
        }
        user.inventory.insertStack(itemToGive.defaultStack)
        if (!user.isCreative) {
            tool.damage(1, Random(), user)
            if (tool.damage >= 64) {
                tool = ItemStack.EMPTY
                user.incrementStat(Stats.BROKEN.getOrCreateStat(tool.item))
                user.playSound(SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F)
            }
        }
        return TypedActionResult.consume(tool)
    }
}