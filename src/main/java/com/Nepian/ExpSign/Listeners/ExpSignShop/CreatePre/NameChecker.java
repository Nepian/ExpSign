package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre;

import static com.Nepian.ExpSign.Permission.*;
import static com.Nepian.ExpSign.Signs.ExpSignShop.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Permission;
import com.Nepian.ExpSign.Configuration.Config;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class NameChecker implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onPreExpSignShopCreation(ExpSignShopCreatePreEvent event) {

		if (event.isCancelled()) {
			return;
		}

		String name = event.getSignLine(NAME_LINE);
		Player player = event.getPlayer();

		if (name.isEmpty() || !name.equals(player.getName())) {
			if (Permission.has(player, ADMIN) && ExpSignShop.isAdminShop(name)) {
				name = Config.ADMINSHOP_NAME.getString();
			} else {
				name = player.getName();
			}
			event.setSignLine(NAME_LINE, name);
		}
	}
}
