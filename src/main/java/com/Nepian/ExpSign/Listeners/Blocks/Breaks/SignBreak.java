package com.Nepian.ExpSign.Listeners.Blocks.Breaks;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.Nepian.Breeze.Utils.BlockUtil;
import com.Nepian.ExpSign.Permission;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class SignBreak implements Listener {

	@EventHandler
	public static void onBlockBreak(BlockBreakEvent event) {
		if (!BlockUtil.isSign(event.getBlock())) {
			return;
		}

		Sign sign = (Sign) event.getBlock().getState();

		if (!ExpSignShop.isExpSign(sign)) {
			return;
		}

		Player player = event.getPlayer();

		if (Permission.has(event.getPlayer(), Permission.ADMIN)) {
			return;
		}

		if (sign.getLine(ExpSignShop.NAME_LINE).equals(player.getName())) {
			return;
		}

		event.setCancelled(true);
	}
}
