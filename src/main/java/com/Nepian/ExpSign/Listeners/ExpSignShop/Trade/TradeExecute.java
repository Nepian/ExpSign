package com.Nepian.ExpSign.Listeners.ExpSignShop.Trade;

import java.util.UUID;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
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
			UUID uuid = PlayerUtil.getUUID(event.getOwnerName());
			ExpBank.withdraw(uuid, quantity);
			economy.depositPlayer(Bukkit.getOfflinePlayer(uuid), price);
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
			UUID uuid = PlayerUtil.getUUID(event.getOwnerName());
			ExpBank.deposit(uuid, quantity);
			economy.withdrawPlayer(Bukkit.getOfflinePlayer(uuid), price);
		}
	}
}
