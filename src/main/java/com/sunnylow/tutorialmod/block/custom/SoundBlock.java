package com.sunnylow.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class SoundBlock extends Block {
	public SoundBlock(Properties p_49795_) {
		super(p_49795_);
	}

	@Override
	public InteractionResult useWithoutItem(
			BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer,
			BlockHitResult pHit) {
		pLevel.playSound(pPlayer, pPos,
				SoundEvents.NOTE_BLOCK_BELL.get(), SoundSource.BLOCKS,
				1.0F, 1.0F);
		return InteractionResult.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
		pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.sound_block.tooltip"));
		super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
	}
}
