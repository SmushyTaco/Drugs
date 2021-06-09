package com.smushytaco.drugs
import com.smushytaco.drugs.drug_items.CannabisInABluntItem
import com.smushytaco.drugs.drug_items.CrackItem
import com.smushytaco.drugs.drug_items.HeroineInANeedleItem
import com.smushytaco.drugs.drug_items.LineOfCocaineItem
import com.smushytaco.drugs.tools.material.CustomToolMaterial
import com.smushytaco.drugs.tools.KnifeToolItem
import com.smushytaco.drugs.tools.SpoonToolItem
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.block.CropBlock
import net.minecraft.block.Material
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.*
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
object Drugs : ModInitializer {
    private const val MOD_ID = "drugs"
    override fun onInitialize() {
        // Cannabis
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "cannabis"), CANNABIS)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "cannabis_crop"), CANNABIS_CROP)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "cannabis_seed"), CANNABIS_SEED)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "cannabis_in_a_blunt"), CANNABIS_IN_A_BLUNT)
        // Cocaine
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "coca_leaf"), COCA_LEAF)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "coca_crop"), COCA_CROP)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "coca_seed"), COCA_SEED)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "cocaine"), COCAINE)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "line_of_cocaine"), LINE_OF_COCAINE)
        // Crack
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "crack_pipe"), CRACK_PIPE)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "crack"), CRACK)
        // Heroine
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "poppy_seed"), POPPY_SEED)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "needle"), NEEDLE)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "heroine"), HEROINE)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "heroine_in_a_needle"), HEROINE_IN_A_NEEDLE)
        // Tools
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "knife"), KNIFE)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "spoon"), SPOON)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "empty_blunt"), EMPTY_BLUNT)
        // Seed Drops
        LootTableLoadingCallback.EVENT.register(LootTableLoadingCallback { _, _, id, supplier, _ ->
            if ("minecraft:blocks/grass" == id.toString()) {
                val cannabisSeed = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .withEntry(ItemEntry.builder(CANNABIS_SEED).build())
                    .withCondition(RandomChanceLootCondition.builder(0.01F).build())
                    .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 2))
                supplier.pool(cannabisSeed)
                val cocaSeed = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .withEntry(ItemEntry.builder(COCA_SEED).build())
                    .withCondition(RandomChanceLootCondition.builder(0.01F).build())
                    .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 2))
                supplier.pool(cocaSeed)
            }
        })
    }
    private val DRUGS_GROUP = FabricItemGroupBuilder
        .build(Identifier(MOD_ID, "drugs")) { ItemStack(CANNABIS_IN_A_BLUNT) }
    // Cannabis
    private val CANNABIS_CROP = object:CropBlock(Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)) {
        override fun getMaxAge() = 5
    }
    private val CANNABIS_SEED = AliasedBlockItem(CANNABIS_CROP, Item.Settings().group(DRUGS_GROUP))
    private val CANNABIS = Item(Item.Settings().group(DRUGS_GROUP))
    private val CANNABIS_IN_A_BLUNT: Item = CannabisInABluntItem(Item.Settings().group(DRUGS_GROUP))
    // Cocaine
    private val COCA_CROP = object:CropBlock(Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)) {}
    private val COCA_SEED = AliasedBlockItem(COCA_CROP, Item.Settings().group(DRUGS_GROUP))
    val COCA_LEAF = Item(Item.Settings().group(DRUGS_GROUP))
    val COCAINE = Item(Item.Settings().group(DRUGS_GROUP))
    private val LINE_OF_COCAINE = LineOfCocaineItem(Item.Settings().group(DRUGS_GROUP))
    // Crack
    val CRACK_PIPE = Item(Item.Settings().maxCount(1).group(DRUGS_GROUP))
    private val CRACK = CrackItem(Item.Settings().group(DRUGS_GROUP))
    // Heroine
    private val POPPY_SEED = Item(Item.Settings().group(DRUGS_GROUP))
    val NEEDLE = Item(Item.Settings().group(DRUGS_GROUP))
    val HEROINE = Item(Item.Settings().group(DRUGS_GROUP))
    val HEROINE_IN_A_NEEDLE = HeroineInANeedleItem(Item.Settings().group(DRUGS_GROUP))
    // Tools
    private val KNIFE = KnifeToolItem(CustomToolMaterial, Item.Settings().group(DRUGS_GROUP))
    private val SPOON = SpoonToolItem(CustomToolMaterial, Item.Settings().group(DRUGS_GROUP))
    val EMPTY_BLUNT = Item(Item.Settings().group(DRUGS_GROUP))
}