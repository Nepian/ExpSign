package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre;

import static com.Nepian.ExpSign.Events.PreExpSignShopCreationEvent.CreationOutcome.*;
import static com.Nepian.ExpSign.Signs.ExpSignShop.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.NumberUtil;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent;

public class QuantityChecker implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
		String quantity = event.getSignLine(QUANTITY_LINE);
	public static void onCreatePre(ExpSignShopCreatePreEvent event) {

		if (!NumberUtil.isInteger(quantity)) {
			event.setOutcome(INVALID_QUANTITY);
		}
	}
}
