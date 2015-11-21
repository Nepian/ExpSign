package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre;

import static com.Nepian.ExpSign.Configuration.Messages.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Configuration.Messages;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent;

public class ErrorMessageSender implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onCreatePre(ExpSignShopCreatePreEvent event) {

		if (!event.isCancelled()) {
			return;
		}

		Messages message = null;

		switch(event.getOutCome()) {
		case INVALID_PRICE:    message = EXPSIGNSHOP__INVALID_DETECTED; break;
		case INVALID_QUANTITY: message = EXPSIGNSHOP__INVALID_DETECTED; break;
		case NO_PERMISSION:    message = PLAYER__NO_PERMISSION;         break;
		default:
		}

		if (message != null) {
			Messages.sendPrefixMessage(event.getPlayer(), message);
			event.getSign().getBlock().breakNaturally();
		}
	}
}
