package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre;

import static com.Nepian.Breeze.Utils.PriceUtil.*;
import static com.Nepian.ExpSign.Events.PreExpSignShopCreationEvent.CreationOutcome.*;
import static com.Nepian.ExpSign.Signs.ExpSignShop.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.PriceUtil;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent;

public class PriceChecker implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onCreatePre(ExpSignShopCreatePreEvent event) {
		String line = event.getSignLine(PRICE_LINE).toUpperCase().replaceAll(" ", "");

		if (!isOnlyOneColon(line)) {
			event.setOutcome(INVALID_PRICE);
			return;
		}

		String[] part = line.split(":");

		if (isSellPriceOnly(part)) {
			line = line.replace(':', ' ');
		}

		if (isPrice(part[0])) {
			line = "B " + line;
		}

		if (part.length > 1 && isPrice(part[1])) {
			line += " S";
		}

		if (line.length() > 15) {
			event.setOutcome(INVALID_PRICE);
			return;
		}

		event.setSignLine(PRICE_LINE, line);

		if (!PriceUtil.hasBuyPrice(line) && !PriceUtil.hasSellPrice(line)) {
			event.setOutcome(INVALID_PRICE);
		}
	}

	private static boolean isOnlyOneColon(String line) {
		return (line.indexOf(':') == line.lastIndexOf(':'));
	}

	private static boolean isSellPriceOnly(String[] part) {
		return part[0].equals("");
	}
}
