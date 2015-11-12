package com.Nepian.ExpSign.Listeners.ExpSignShop;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.Nepian.Breeze.Utils.ActionUtil;
import com.Nepian.Breeze.Utils.BlockUtil;
import com.Nepian.ExpSign.ExpSign;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.TradeType;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class ExpSignShopTrade implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public static void onPlayerInt(PlayerInteractEvent event) {

		if (!event.getPlayer().isSneaking()) {
			return;
		}

		if (!ActionUtil.isClickedBlock(event.getAction())) {
			return;
		}

		if (!BlockUtil.isSign(event.getClickedBlock())) {
			return;
		}

		Sign sign = (Sign) event.getClickedBlock().getState();

		if (!ExpSignShop.isValid(sign)) {
			return;
		}

		Player player = event.getPlayer();
		TradeType tradeType = (ActionUtil.isLeftClicked(event.getAction())) ?
				TradeType.BUY : TradeType.SELL;

		ExpSignShopTradeEvent TradeEve = new ExpSignShopTradeEvent(player, sign, tradeType);

		ExpSign.callEvent(TradeEve);
	}
}
