package com.Nepian.ExpSign.Listeners.PreExpSignShopCreation;

import static com.Nepian.ExpSign.Events.PreExpSignShopCreationEvent.CreationOutcome.*;
import static com.Nepian.ExpSign.Signs.ExpShopSign.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.NumberUtil;
import com.Nepian.ExpSign.Events.PreExpSignShopCreationEvent;

public class QuantityChecker implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onPreExpSignCreation(PreExpSignShopCreationEvent event) {
		String quantity = event.getSignLine(QUANTITY_LINE);

		if (!NumberUtil.isInteger(quantity)) {
			event.setOutcome(INVALID_QUANTITY);
		}
	}
}
