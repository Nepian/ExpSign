package com.Nepian.ExpSign.Listeners.ExpSignShop.Trade;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.PlayerUtil;
import com.Nepian.ExpSign.Economy.Eco;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.ShopType;
import com.Nepian.ExpSign.Events.ExpSignShopTradeEvent.TradeType;
import com.Nepian.ExpSign.ExpBank.ExpBank;

public class TradeExecute implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public static void onTradeBuy(ExpSignShopTradeEvent event) {

		if (event.isCancelled()) {
			return;
		}

		if (event.getTradeType() != TradeType.BUY) {
			return;
		}

		final int quantity = event.getQuantity();
		final double price = event.getPrice();
		Economy economy = Eco.getEconomy();

		Player player = event.getPlayer();

		PlayerUtil.addExp(player, quantity);
		economy.withdrawPlayer(player, price);

		if (event.getShopType() != ShopType.ADMIN) {
			OfflinePlayer owner = PlayerUtil.getOfflinePlayer(event.getOwnerName());
			ExpBank.withdraw(owner, quantity);
			economy.depositPlayer(owner, price);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public static void onTradeSell(ExpSignShopTradeEvent event) {

		if (event.isCancelled()) {
			return;
		}

		if (event.getTradeType() != TradeType.SELL) {
			return;
		}

		final int quantity = event.getQuantity();
		final double price = event.getPrice();
		Economy economy = Eco.getEconomy();

		Player player = event.getPlayer();

		PlayerUtil.subtractExp(player, quantity);
		economy.depositPlayer(player, price);

		if (event.getShopType() != ShopType.ADMIN) {
			OfflinePlayer owner = PlayerUtil.getOfflinePlayer(event.getOwnerName());
			ExpBank.deposit(owner, quantity);
			economy.withdrawPlayer(owner, price);
		}
	}
}
