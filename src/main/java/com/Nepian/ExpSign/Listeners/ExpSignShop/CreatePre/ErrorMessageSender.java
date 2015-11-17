package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre;

import static org.bukkit.event.EventPriority.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Configuration.Messages;
import com.Nepian.ExpSign.Events.PreExpSignShopCreationEvent;

public class ErrorMessageSender implements Listener {

	@EventHandler(priority = MONITOR)
	public static void onPreExpSignShopCreation(PreExpSignShopCreationEvent event) {
		if (!event.isCancelled()) return;

		String message = null;

		switch(event.getOutCome()) {
		case INVALID_PRICE:
			message = Messages.EXPSIGNSHOP__INVALID_DETECTED.get();
			break;
		case INVALID_QUANTITY:
			message = Messages.EXPSIGNSHOP__INVALID_DETECTED.get();
			break;
		case NO_PERMISSION:
			message = Messages.PLAYER__NO_PERMISSION.get();
			break;
		default:
			break;
		}

		if (message != null) {
			Messages.sendPrefixMessage(event.getPlayer(), message);
			event.getSign().getBlock().breakNaturally();
		}
	}
}
