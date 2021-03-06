package com.smushytaco.drugs.tools.material
import net.minecraft.item.Items
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
object CustomToolMaterial: ToolMaterial {
    override fun getDurability() = 64
    override fun getMiningSpeedMultiplier() = 0.0F
    override fun getAttackDamage() = 0.0F
    override fun getMiningLevel() = 0
    override fun getEnchantability() = 0
    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(Items.IRON_INGOT)
}