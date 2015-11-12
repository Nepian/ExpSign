package com.Nepian.ExpSign.Listeners.ExpSignShop.Trade;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Configuration.Messages;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.TradeType;

public class TradeMessageSender implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onTradeBuy(ExpSignShopTradeEvent event) {

		if (event.isCancelled()) {
			return;
		}

		if (event.getTradeType() != TradeType.BUY) {
			return;
		}

		Messages.sendPrefixMessage(event.getPlayer(), Messages.SUCCESS_EXPSIGNSHOP_BUY);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onTradeSell(ExpSignShopTradeEvent event) {

		if (event.isCancelled()) {
			return;
		}

		if (event.getTradeType() != TradeType.SELL) {
			return;
		}

		Messages.sendPrefixMessage(event.getPlayer(), Messages.SUCCESS_EXPSIGNSHOP_SELL);
	}
}
