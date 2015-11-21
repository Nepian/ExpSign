package com.Nepian.ExpSign.Listeners.ExpSignShop;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.Nepian.Breeze.Utils.BlockUtil;
import com.Nepian.Breeze.Utils.StringUtil;
import com.Nepian.ExpSign.ExpSign;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePostEvent;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class ExpSignShopCreate implements Listener {

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
		ExpSignShopCreatePreEvent preEvent = new ExpSignShopCreatePreEvent(event.getPlayer(), sign, lines);

		ExpSign.callEvent(preEvent);

		if (preEvent.isCancelled()) return;

		for (byte i = 0; i < event.getLines().length; i++) {
			event.setLine(i, preEvent.getSignLine(i));
		}

		ExpSignShopCreatePostEvent postEvent = new ExpSignShopCreatePostEvent(preEvent.getPlayer(), preEvent.getSign(), preEvent.getSignLines());

		ExpSign.callEvent(postEvent);
	}
}
