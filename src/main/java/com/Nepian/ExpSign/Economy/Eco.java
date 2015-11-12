package com.Nepian.ExpSign.Economy;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Eco {
	private static Economy economy = setup();

	/* Methods --------------------------------------------------------------*/

	public static boolean hasEconomy() {
		return economy != null;
	}

	/* Getter ---------------------------------------------------------------*/

	public static Economy getEconomy() {
		return economy;
	}

	/* Private Methods ------------------------------------------------------*/

	private static Economy setup() {
		RegisteredServiceProvider<Economy> rsp =
				Bukkit.getServer().getServicesManager()
				.getRegistration(Economy.class);

		if (rsp != null) {
			return rsp.getProvider();
		}

		return null;
	}
}
