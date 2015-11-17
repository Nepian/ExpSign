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
			message = Messages.EXPSIGNSHOP__NO_OWNER_EXP.get();
			break;
		case NO_OWNER_MONEY:
			message = Messages.EXPSIGNSHOP__NO_OWNER_MONEY.get();
			break;
		case NO_PLAYER_EXP:
			message = Messages.PLAYER__NO_EXP.get();
			break;
		case NO_PLAYER_MONEY:
			message = Messages.PLAYER__NO_MONEY.get();
			break;
		case NO_PERMISSION:
			message = Messages.PLAYER__NO_PERMISSION.get();
			break;
		case MAX_EXP:
			message = Messages.PLAYER__MAX_EXP.get();
			break;
		case OWNER_IS_PLAYER:
			message = Messages.EXPSIGNSHOP__OWNER_MINE.get();
			break;
		case CANT_BUY:
			message = Messages.EXPSIGNSHOP__CANNOT_BUY.get();
			break;
		case CANT_SELL:
			message = Messages.EXPSIGNSHOP__CANNOT_SELL.get();
			break;
		default:
		}

		Messages.sendPrefixMessage(event.getPlayer(), message);
	}
}
