package com.Nepian.ExpSign.Listeners.PostExpSignShopCreated;

import static com.Nepian.ExpSign.Signs.ExpSignShop.*;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.LocationUtil;
import com.Nepian.ExpSign.ExpSign;
import com.Nepian.ExpSign.Configuration.Logger;
import com.Nepian.ExpSign.Events.PostExpSignShopCreatedEvent;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class ShopCreatedLogger implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPostExpSignShopCreated (final PostExpSignShopCreatedEvent event) {
		Bukkit.getScheduler().runTaskAsynchronously(ExpSign.getPlugin(), new Runnable() {

			@Override
			public void run() {
				String creater = event.getPlayer().getName();
				String shopOwner = event.getSignLine(NAME_LINE);
				String typeOfShop = ExpSignShop.isAdminShop(shopOwner) ? "ExpSignShop-Admin" : "ExpSignShop";

				String quantity = event.getSignLine(QUANTITY_LINE);
				String prices = event.getSignLine(PRICE_LINE);
				String location = LocationUtil.locationToString(event.getSign().getLocation());

				String message = Logger.EXPSIGNSHOP_CREATED_LOG
						.replace("{creater}", creater)
						.replace("{type}", typeOfShop)
						.replace("{amount}", quantity)
						.replace("{prices}", prices)
						.replace("{location}", location);

				Logger.log(message);
			}

		});

	}
}
