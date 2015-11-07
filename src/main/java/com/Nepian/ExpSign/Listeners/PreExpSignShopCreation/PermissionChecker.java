package com.Nepian.ExpSign.Listeners.PreExpSignShopCreation;

import static com.Nepian.ExpSign.Events.PreExpSignShopCreationEvent.CreationOutcome.*;
import static com.Nepian.ExpSign.Permission.*;
import static org.bukkit.event.EventPriority.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.PriceUtil;
import com.Nepian.ExpSign.Permission;
import com.Nepian.ExpSign.Events.PreExpSignShopCreationEvent;
import com.Nepian.ExpSign.Signs.ExpShopSign;

public class PermissionChecker implements Listener {

	@EventHandler(priority = HIGH)
	public static void onPreExpSignCreation(PreExpSignShopCreationEvent event) {
		Player player = event.getPlayer();

		if (Permission.has(player, ADMIN)) return;

		String priceLine = event.getSignLine(ExpShopSign.PRICE_LINE);

		if (PriceUtil.hasBuyPrice(priceLine)) {
			if (!Permission.has(player, SIGN_CREATION_BUY)) {
				event.setOutcome(NO_PERMISSION);
				return;
			}
		}

		if (PriceUtil.hasSellPrice(priceLine)) {
			if (!Permission.has(player, SIGN_CREATION_SELL)) {
				event.setOutcome(NO_PERMISSION);
				return;
			}
		}
	}
}
