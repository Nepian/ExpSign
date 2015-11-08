package com.Nepian.ExpSign.Listeners.PreExpSignShopCreation;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Events.PreExpSignShopCreationEvent;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class ExpNameChanger implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPreExpSignShopCreation(PreExpSignShopCreationEvent event) {
		event.setSignLine(ExpSignShop.EXP_LINE, "[" + ExpSignShop.EXPSIGNSHOP_NAME + "]");
	}
}
