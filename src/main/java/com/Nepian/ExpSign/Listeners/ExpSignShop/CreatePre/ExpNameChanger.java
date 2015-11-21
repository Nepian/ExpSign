package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre;

import static com.Nepian.ExpSign.Signs.ExpSignShop.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent;

public class ExpNameChanger implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onCreatePre(ExpSignShopCreatePreEvent event) {

		if (event.isCancelled()) {
			return;
		}

		event.setSignLine(EXP_LINE, "[" + EXPSIGNSHOP_NAME + "]");
	}
}
