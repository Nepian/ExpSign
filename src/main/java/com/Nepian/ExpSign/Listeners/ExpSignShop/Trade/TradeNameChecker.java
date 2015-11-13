package com.Nepian.ExpSign.Listeners.ExpSignShop.Trade;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.Outcome;

public class TradeNameChecker implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onTrade(ExpSignShopTradeEvent event) {

		if (event.isCancelled()) {
			return;
		}

		final String owner = event.getOwnerName();
		final String player = event.getPlayer().getName();

		if (player.equals(owner)) {
			event.setOutcome(Outcome.OWNER_IS_PLAYER);
		}
	}
}
