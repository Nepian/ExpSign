package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Signs.ExpSignShop;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent;

public class ExpNameChanger implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
		event.setSignLine(ExpSignShop.EXP_LINE, "[" + ExpSignShop.EXPSIGNSHOP_NAME + "]");
	public static void onCreatePre(ExpSignShopCreatePreEvent event) {

		if (event.isCancelled()) {
			return;
		}

	}
}
