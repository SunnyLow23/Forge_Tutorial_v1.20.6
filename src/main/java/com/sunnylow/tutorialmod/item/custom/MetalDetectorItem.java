package com.sunnylow.tutorialmod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MetalDetectorItem extends Item {
	public MetalDetectorItem(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public InteractionResult useOn(UseOnContext pContext) {
		if (!pContext.getLevel().isClientSide()) {
			BlockPos positionClicked = pContext.getClickedPos();
			Player pPlayer = pContext.getPlayer();
			ItemStack itemStack = pContext.getItemInHand();
			boolean foundBlock = false;

			for (int i = 0; i <= positionClicked.getY() + 64; i++) {
				BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

				if (isValuableBlock(state)) {
					outputValuableCoordinates(positionClicked.below(i), pPlayer, state.getBlock());
					foundBlock = true;

					break;
				}
			}

			if (!foundBlock) {
				pPlayer.sendSystemMessage(Component.literal("No valuables found!"));
			}

			if (pPlayer != null) {
				itemStack.hurtAndBreak(1, pPlayer, LivingEntity.getSlotForHand(pContext.getHand()));
			}
		}

		return InteractionResult.SUCCESS;
	}

	private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
		player.sendSystemMessage(
				Component.literal(
						"Found " + I18n.get(block.getDescriptionId()) + " at " +
								"(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"
				)
		);
	}

	private boolean isValuableBlock(BlockState state) {
		return state.is(Blocks.IRON_ORE) ||
				state.is(Blocks.GOLD_ORE) ||
				state.is(Blocks.DIAMOND_ORE) ||
				state.is(Blocks.EMERALD_ORE) ||
				state.is(Blocks.NETHER_QUARTZ_ORE) ||
				state.is(Blocks.LAPIS_ORE);
	}
}