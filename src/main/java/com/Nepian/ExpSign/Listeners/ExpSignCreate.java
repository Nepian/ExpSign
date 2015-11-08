package com.Nepian.ExpSign.Listeners;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.Nepian.Breeze.Utils.BlockUtil;
import com.Nepian.Breeze.Utils.StringUtil;
import com.Nepian.ExpSign.ExpSign;
import com.Nepian.ExpSign.Events.PostExpSignShopCreatedEvent;
import com.Nepian.ExpSign.Events.PreExpSignShopCreationEvent;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class ExpSignCreate implements Listener {

	@EventHandler
	public static void onSignChange(SignChangeEvent event) {
		if (!BlockUtil.isSign(event.getBlock())) {
			return;
		}

		String[] lines = StringUtil.stripColourCodes(event.getLines());

		if (!ExpSignShop.isExpSign(lines)) {
			return;
		}

		Sign sign = (Sign) event.getBlock().getState();
		PreExpSignShopCreationEvent preEvent = new PreExpSignShopCreationEvent(event.getPlayer(), sign, lines);

		ExpSign.callEvent(preEvent);

		if (preEvent.isCancelled()) return;

		for (byte i = 0; i < event.getLines().length; i++) {
			event.setLine(i, preEvent.getSignLine(i));
		}

		PostExpSignShopCreatedEvent postEvent = new PostExpSignShopCreatedEvent(preEvent.getPlayer(), preEvent.getSign(), preEvent.getSignLines());

		ExpSign.callEvent(postEvent);
	}
}
