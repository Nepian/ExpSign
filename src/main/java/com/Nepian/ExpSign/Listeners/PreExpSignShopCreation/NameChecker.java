package com.Nepian.ExpSign.Listeners.PreExpSignShopCreation;

import static com.Nepian.ExpSign.Permission.*;
import static com.Nepian.ExpSign.Signs.ExpSignShop.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Permission;
import com.Nepian.ExpSign.Configuration.Properties;
import com.Nepian.ExpSign.Events.PreExpSignShopCreationEvent;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class NameChecker implements Listener {
	@EventHandler(priority = EventPriority.LOWEST)
	public static void onPreExpSignShopCreation(PreExpSignShopCreationEvent event) {
		String name = event.getSignLine(NAME_LINE);
		Player player = event.getPlayer();

		if (name.isEmpty() || !name.equals(player.getName())) {
			if (Permission.has(player, ADMIN) && ExpSignShop.isAdminShop(name)) {
				name = Properties.ADMIN_SHOP_NAME;
			} else {
				name = player.getName();
			}
			event.setSignLine(NAME_LINE, name);
		}
	}
}
