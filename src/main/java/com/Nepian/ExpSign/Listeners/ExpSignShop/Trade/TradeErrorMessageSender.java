package com.Nepian.ExpSign.Listeners.ExpSignShop.Trade;

import static com.Nepian.ExpSign.Configuration.Messages.*;

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

		Messages message = null;

		switch (event.getOutCome()) {
		case NO_OWNER_EXP:    message = EXPSIGNSHOP__NO_OWNER_EXP;   break;
		case NO_OWNER_MONEY:  message = EXPSIGNSHOP__NO_OWNER_MONEY; break;
		case NO_PLAYER_EXP:   message = PLAYER__NO_EXP;              break;
		case NO_PLAYER_MONEY: message = PLAYER__NO_MONEY;            break;
		case NO_PERMISSION:   message = PLAYER__NO_PERMISSION;       break;
		case MAX_EXP:         message = PLAYER__MAX_EXP;             break;
		case OWNER_IS_PLAYER: message = EXPSIGNSHOP__OWNER_MINE;     break;
		case CANT_BUY:        message = EXPSIGNSHOP__CANNOT_BUY;     break;
		case CANT_SELL:       message = EXPSIGNSHOP__CANNOT_SELL;    break;
		default:
		}

		Messages.sendPrefixMessage(event.getPlayer(), message);
	}
}
