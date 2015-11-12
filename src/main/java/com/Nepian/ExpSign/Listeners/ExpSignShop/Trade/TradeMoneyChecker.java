package com.Nepian.ExpSign.Listeners.ExpSignShop.Trade;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.PlayerUtil;
import com.Nepian.ExpSign.Economy.Eco;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.Outcome;

public class TradeMoneyChecker implements Listener {
	private static ExpSignShopTradeEvent event;
	private static double price;

	@EventHandler(priority = EventPriority.NORMAL)
	public static void onTrade(ExpSignShopTradeEvent eve) {
		event = eve;

		if (event.isCancelled()) {
			return;
		}

		price = event.getPrice();

		switch (event.getTradeType()) {
		case BUY:
			buyMoneyCheck();
			break;
		case SELL:
			sellMoneyCheck();
			break;
		}
	}

	/* Private Methods ------------------------------------------------------*/

	private static void buyMoneyCheck() {
		final double playerMoney = Eco.getEconomy().getBalance(event.getPlayer());

		if (playerMoney - price < 0) {
			event.setOutcome(Outcome.NO_PLAYER_MONEY);
		}
	}

	private static void sellMoneyCheck() {
		final OfflinePlayer owner = Bukkit.getOfflinePlayer(PlayerUtil.getUUID(event.getOwnerName()));
		final double ownerMoney = Eco.getEconomy().getBalance(owner);

		if (ownerMoney - price < 0) {
			event.setOutcome(Outcome.NO_OWNER_MONEY);
		}
	}
}
