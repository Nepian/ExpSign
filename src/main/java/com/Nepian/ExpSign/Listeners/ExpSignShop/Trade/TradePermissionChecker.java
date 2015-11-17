package com.Nepian.ExpSign.Listeners.ExpSignShop.Trade;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Permission;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.Outcome;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.TradeType;

public class TradePermissionChecker implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public static void onTrade(ExpSignShopTradeEvent event) {

		if (event.isCancelled()) {
			return;
		}

		final Player player = event.getPlayer();

		if (Permission.has(player, Permission.ADMIN)) {
			return;
		}

		final Permission permission = (event.getTradeType() == TradeType.BUY) ?
				Permission.SHOP_BUY : Permission.SHOP_SELL;

		if (!Permission.has(player, permission)) {
			event.setOutcome(Outcome.NO_PERMISSION);
		}
	}
}
