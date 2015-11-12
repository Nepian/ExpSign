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
	 * プレイヤーによる看板の破壊の保護
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
	 * ブロックの破壊に伴う看板の破壊の保護
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
	 * ピストンが広がった場合に伴う看板の破壊の保護
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
	 * ピストンが縮んだ場合に伴う看板の破壊の保護
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
	 * ブロックの焼失に伴う看板の破壊の保護
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
	 * エンティの爆発に伴う看板の破壊の保護
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
	 * ブロックが破壊可能かどうかを判定する
	 * @param block 判定をするブロック
	 * @return ExpSignShopが設置されていなければtrue
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
