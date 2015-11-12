package com.Nepian.ExpSign.Listeners.ExpSignShop.Trade;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Configuration.Messages;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent;

public class TradeErrorMessageSender implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public static void onTrade(ExpSignShopTradeEvent event) {

		if (!event.isCancelled()) {
			return;
		}

		String message = null;

		switch (event.getOutCome()) {
		case NO_OWNER_EXP:
			message = Messages.NO_EXPSIGNSHOP_OWNER_EXP;
			break;
		case NO_OWNER_MONEY:
			message = Messages.NO_EXPSIGNSHOP_OWNER_MONEY;
			break;
		case NO_PLAYER_EXP:
			message = Messages.NO_PLAYER_EXP;
			break;
		case NO_PLAYER_MONEY:
			message = Messages.NO_PLAYER_MONEY;
			break;
		case NO_PERMISSION:
			message = Messages.NO_PERMISSION;
			break;
		case MAX_EXP:
			message = Messages.MAX_PLAYER_EXP;
			break;
		case OWNER_IS_PLAYER:
			message = Messages.EXPSIGNSHOP_OWNER_IS_PLAYER;
		default:
		}

		Messages.sendPrefixMessage(event.getPlayer(), message);
	}
}
