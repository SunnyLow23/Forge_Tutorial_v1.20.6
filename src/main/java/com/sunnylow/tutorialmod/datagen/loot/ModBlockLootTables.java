package com.sunnylow.tutorialmod.datagen.loot;

import com.sunnylow.tutorialmod.block.ModBlocks;
import com.sunnylow.tutorialmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
	public ModBlockLootTables() {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	protected void generate() {
		this.dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());
		this.dropSelf(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
		this.dropSelf(ModBlocks.SOUND_BLOCK.get());

		this.add(ModBlocks.SAPPHIRE_ORE.get(),
				block -> createCopperOreDrops(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
		this.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
				block -> createCopperOreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
		this.add(ModBlocks.NETHER_SAPPHIRE_ORE.get(),
				block -> createCopperOreDrops(ModBlocks.NETHER_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
		this.add(ModBlocks.END_STONE_SAPPHIRE_ORE.get(),
				block -> createCopperOreDrops(ModBlocks.END_STONE_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
	}

	protected LootTable.Builder createCopperOreDrops(Block pBlock, Item pItem) {
		return createSilkTouchDispatchTable(
				pBlock,
				this.applyExplosionDecay(
						pBlock,
						LootItem.lootTableItem(pItem)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
								.apply(ApplyBonusCount.addOreBonusCount(Enchantments.FORTUNE))
				)
		);
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
	}
}
