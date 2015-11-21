package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.NumberUtil;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent.Outcome;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class QuantityChecker implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onCreatePre(ExpSignShopCreatePreEvent event) {

		if (event.isCancelled()) {
			return;
		}

		String quantity = event.getSignLine(ExpSignShop.QUANTITY_LINE);

		if (!NumberUtil.isInteger(quantity)) {
			event.setOutcome(Outcome.INVALID_QUANTITY);
		}
	}
}
