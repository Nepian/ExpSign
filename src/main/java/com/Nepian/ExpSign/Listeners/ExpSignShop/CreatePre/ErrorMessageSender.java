package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre;


import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Configuration.Messages;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent;

public class ErrorMessageSender implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onCreatePre(ExpSignShopCreatePreEvent event) {

		String message = null;
		if (!event.isCancelled()) {
			return;
		}


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
