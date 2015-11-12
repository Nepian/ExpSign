package com.Nepian.ExpSign.Listeners.ExpSignShop.Trade;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.PlayerUtil;
import com.Nepian.ExpSign.Configuration.Properties;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.Outcome;
import com.Nepian.ExpSign.Userdata.Userdata;
import com.Nepian.ExpSign.Userdata.UserdataManager;

public class TradeQuantityChecker implements Listener {
	private static ExpSignShopTradeEvent event;
	private static int quantity;
	private static int playerExp;

	@EventHandler(priority = EventPriority.NORMAL)
	public static void onTrade(ExpSignShopTradeEvent eve) {
		event = eve;

		if (event.isCancelled()) {
			return;
		}

		quantity = event.getQuantity();
		playerExp = PlayerUtil.getExp(event.getPlayer());

		switch (event.getTradeType()) {
		case BUY:
			buyQuantityCheck();
			break;
		case SELL:
			sellQuantityCheck();
			break;
		}
	}

	/* Private Methods ------------------------------------------------------*/

	private static void buyQuantityCheck() {
		final UUID uuid = PlayerUtil.getUUID(event.getOwnerName());
		final Userdata userdata = UserdataManager.getUserdata(uuid);
		final int ownerExp = userdata.getInteger(Properties.EXPBANK_PATH);

		if (PlayerUtil.MAX_EXP < playerExp + quantity) {
			event.setOutcome(Outcome.MAX_EXP);
		}

		if (ownerExp - quantity < 0) {
			event.setOutcome(Outcome.NO_OWNER_EXP);
		}
	}

	private static void sellQuantityCheck() {
		if (playerExp - quantity < 0) {
			event.setOutcome(Outcome.NO_PLAYER_EXP);
		}
	}
}
