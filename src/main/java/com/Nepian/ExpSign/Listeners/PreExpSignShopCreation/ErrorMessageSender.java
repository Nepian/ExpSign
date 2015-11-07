package com.Nepian.ExpSign.Listeners.PreExpSignShopCreation;

import static org.bukkit.event.EventPriority.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Configuration.Messages;
import com.Nepian.ExpSign.Events.PreExpSignShopCreationEvent;

public class ErrorMessageSender implements Listener {

	@EventHandler(priority = MONITOR)
	public static void onPreExpSignCreation(PreExpSignShopCreationEvent event) {
		if (!event.isCancelled()) return;

		String message = null;

		switch(event.getOutCome()) {
		case SIGN_CREATED_SUCCESSFULLY:
			message = Messages.SUCCESS_CREATE_SIGN;
			break;
		case INVALID_PRICE:
			message = Messages.INVALID_SIGN_DETECTED;
			break;
		case INVALID_QUANTITY:
			message = Messages.INVALID_SIGN_DETECTED;
			break;
		case NO_PERMISSION:
			message = Messages.NO_PERMISSION;
			break;
		default:
			break;
		}

		if (message != null) {
			Messages.sendMessage(event.getPlayer(), Messages.prefix(message));
			event.getSign().getBlock().breakNaturally();
		}
	}
}
