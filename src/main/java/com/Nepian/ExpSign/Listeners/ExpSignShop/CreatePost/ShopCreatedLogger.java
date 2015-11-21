package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePost;

import static com.Nepian.ExpSign.Signs.ExpSignShop.*;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.ExpSign;
import com.Nepian.ExpSign.Configuration.Logger;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePostEvent;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class ShopCreatedLogger implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onCreatePost(final ExpSignShopCreatePostEvent event) {
		Bukkit.getScheduler().runTaskAsynchronously(ExpSign.getPlugin(), new Runnable() {

			@Override
			public void run() {
				String creater = event.getPlayer().getName();
				String shopOwner = event.getSignLine(NAME_LINE);
				String typeOfShop = (ExpSignShop.isAdminShop(shopOwner))
						? "ExpSignShop-Admin" : "ExpSignShop";

				String message = Logger.EXPSIGNSHOP__CREATED
						.replace("{creater}", creater)
						.replace("{type}", typeOfShop);

				Logger.log(message);
			}

		});

	}
}
