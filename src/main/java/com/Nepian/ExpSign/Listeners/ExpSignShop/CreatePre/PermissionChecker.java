package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre;

import static com.Nepian.ExpSign.Permission.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.PriceUtil;
import com.Nepian.ExpSign.Permission;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent.Outcome;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class PermissionChecker implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public static void onCreatePre(ExpSignShopCreatePreEvent event) {
		Player player = event.getPlayer();

		if (Permission.has(player, ADMIN)) {
			return;
		}

		String priceLine = event.getSignLine(ExpSignShop.PRICE_LINE);

		if (PriceUtil.hasBuyPrice(priceLine)) {
			if (!Permission.has(player, SHOP_CREATE_BUY)) {
				event.setOutcome(Outcome.NO_PERMISSION);
				return;
			}
		}

		if (PriceUtil.hasSellPrice(priceLine)) {
			if (!Permission.has(player, SHOP_CREATE_SELL)) {
				event.setOutcome(Outcome.NO_PERMISSION);
				return;
			}
		}
	}
}
