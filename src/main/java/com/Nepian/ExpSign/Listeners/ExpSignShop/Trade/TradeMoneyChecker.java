package com.Nepian.ExpSign.Listeners.ExpSignShop.Trade;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.PlayerUtil;
import com.Nepian.Breeze.Utils.PriceUtil;
import com.Nepian.ExpSign.Economy.Eco;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.Outcome;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.ShopType;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.TradeType;

public class TradeMoneyChecker implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public static void onTradeBuy(ExpSignShopTradeEvent event) {

		if (event.isCancelled()) {
			return;
		}

		if (event.getTradeType() != TradeType.BUY) {
			return;
		}

		final double price = event.getPrice();

		if (price == PriceUtil.NO_PRICE) {
			event.setOutcome(Outcome.CANT_BUY);
			return;
		}

		final double playerMoney = Eco.getEconomy().getBalance(event.getPlayer());

		if (playerMoney - price < 0) {
			event.setOutcome(Outcome.NO_PLAYER_MONEY);
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

		final double price = event.getPrice();

		if (price == PriceUtil.NO_PRICE) {
			event.setOutcome(Outcome.CANT_SELL);
			return;
		}

		if (event.getShopType() != ShopType.ADMIN) {
			final OfflinePlayer owner = PlayerUtil.getOfflinePlayer(event.getOwnerName());
			final double ownerMoney = Eco.getEconomy().getBalance(owner);

			if (ownerMoney - price < 0) {
				event.setOutcome(Outcome.NO_OWNER_MONEY);
			}
		}
	}
}