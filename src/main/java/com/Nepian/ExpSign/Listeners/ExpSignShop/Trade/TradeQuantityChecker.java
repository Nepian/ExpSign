package com.Nepian.ExpSign.Listeners.ExpSignShop.Trade;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.PlayerUtil;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.Outcome;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.ShopType;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.TradeType;
import com.Nepian.ExpSign.ExpBank.ExpBank;

public class TradeQuantityChecker implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public static void onTradeBuy(ExpSignShopTradeEvent event) {

		if (event.isCancelled()) {
			return;
		}

		if (event.getTradeType() != TradeType.BUY) {
			return;
		}

		final int quantity = event.getQuantity();
		final int playerExp = PlayerUtil.getExp(event.getPlayer());

		if (PlayerUtil.MAX_EXP < playerExp + quantity) {
			event.setOutcome(Outcome.MAX_EXP);
		}

		if (event.getShopType() != ShopType.ADMIN) {
			final int ownerExp = ExpBank.check(event.getOwnerName());

			if (ownerExp - quantity < 0) {
				event.setOutcome(Outcome.NO_OWNER_EXP);
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public static void onTradeSell(ExpSignShopTradeEvent event) {

		if (event.isCancelled()) {
			return;
		}

		if (event.getTradeType() != TradeType.SELL) {
			return;
		}

		final int quantity = event.getQuantity();
		final int playerExp = PlayerUtil.getExp(event.getPlayer());

		if (playerExp - quantity < 0) {
			event.setOutcome(Outcome.NO_PLAYER_EXP);
		}
	}
}
