package com.Nepian.ExpSign.Listeners.ExpSignShop;

import static org.bukkit.block.BlockFace.*;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.Nepian.Breeze.Utils.BlockUtil;
import com.Nepian.ExpSign.Permission;
import com.Nepian.ExpSign.Configuration.Logger;
import com.Nepian.ExpSign.Configuration.Messages;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class ExpSignShopProtect implements Listener {
	private static final BlockFace[] BLOCK_FACES = { UP, NORTH, SOUTH, WEST, EAST };

	/**
	 * �v���C���[�ɂ��Ŕ̔j��̕ی�
	 * @param event
	 */
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public static void onSignBreak(BlockBreakEvent event) {

		if (!BlockUtil.isSign(event.getBlock())) {
			return;
		}

		Sign sign = (Sign) event.getBlock().getState();

		if (!ExpSignShop.isValid(sign)) {
			Logger.debug("Not ExpSignShop");
			return;
		}

		Player player = event.getPlayer();

		if (Permission.has(player, Permission.ADMIN)) {
			Logger.debug("has permission of Admin");
			return;
		}

		if (sign.getLine(ExpSignShop.NAME_LINE).equals(player.getName())) {
			Logger.debug("Owner of this ExpSignShop is you");
			return;
		}

		event.setCancelled(true);
		Messages.sendPrefixMessage(player, Messages.YOU_ARE_NOT_OWNER);
	}

	/**
	 * �u���b�N�̔j��ɔ����Ŕ̔j��̕ی�
	 * @param event
	 */
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public static void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();

		if (ExpSignShop.isValid(block)) {
			return;
		}

		if (canBreakBlock(block)) {
			return;
		}

		event.setCancelled(true);
		Messages.sendPrefixMessage(event.getPlayer(), Messages.EXPSIGN_ATTACHED);
	}

	/**
	 * �s�X�g�����L�������ꍇ�ɔ����Ŕ̔j��̕ی�
	 * @param event
	 */
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public static void onPistonExtend(BlockPistonExtendEvent event) {
		List<Block> blocks = event.getBlocks();

		for (Block block : blocks) {
			if (!canBreakBlock(block)) {
				event.setCancelled(true);
				return;
			}
		}
	}

	/**
	 * �s�X�g�����k�񂾏ꍇ�ɔ����Ŕ̔j��̕ی�
	 * @param event
	 */
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public static void onPistonRetract(BlockPistonRetractEvent event) {
		List<Block> blocks = event.getBlocks();

		for (Block block : blocks) {
			if (!canBreakBlock(block)) {
				event.setCancelled(true);
				return;
			}
		}
	}

	/**
	 * �u���b�N�̏Ď��ɔ����Ŕ̔j��̕ی�
	 * @param event
	 */
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public static void onBlockBurn(BlockBurnEvent event) {
		Block block = event.getBlock();

		if (!canBreakBlock(block)) {
			event.setCancelled(true);
		}
	}

	/**
	 * �G���e�B�̔����ɔ����Ŕ̔j��̕ی�
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onEntityExplode(EntityExplodeEvent event) {
		List<Block> blocks = event.blockList();

		for (Block block : blocks) {
			if (canBreakBlock(block)) {
				continue;
			}

			event.setCancelled(true);
		}
	}

	/* Private Methods ------------------------------------------------------*/

	/**
	 * �u���b�N���j��\���ǂ����𔻒肷��
	 * @param block ���������u���b�N
	 * @return ExpSignShop���ݒu����Ă��Ȃ����true
	 */
	private static boolean canBreakBlock(Block block) {
		for (BlockFace blockFace : BLOCK_FACES) {
			Block connectionBlock = block.getRelative(blockFace);

			if (!BlockUtil.isSign(connectionBlock)) {
				continue;
			}

			Sign sign = (Sign) connectionBlock.getState();

			if (ExpSignShop.isValid(sign)) {
				return false;
			}
		}

		return true;
	}
}
