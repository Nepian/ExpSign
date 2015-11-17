package com.Nepian.ExpSign;

import static com.Nepian.ExpSign.Configuration.Files.*;
import static com.Nepian.ExpSign.Configuration.Logger.*;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.ExpSign.Configuration.Config;
import com.Nepian.ExpSign.Configuration.Logger;
import com.Nepian.ExpSign.Configuration.Messages;
import com.Nepian.ExpSign.Economy.Eco;
import com.Nepian.ExpSign.PlayerData.PlayerDataManager;

public class ExpSign extends JavaPlugin {
	private static ExpSign plugin;

	public ExpSign() {
		plugin = this;
	}

	/* Methods --------------------------------------------------------------*/

	public void onEnable() {
		economyCheck();
		Config.load(FILE_CONFIG);
		Messages.load(FILE_LANG);
		PlayerDataManager.load();
		EventRegister.load();

		Logger.log(PLUGIN__ENABLE.get());
	}

	public void onDisable() {
		Config.save(FILE_CONFIG);
		Messages.save(FILE_LANG);
		PlayerDataManager.save();

		getServer().getScheduler().cancelTasks(this);

		Logger.log(PLUGIN__DISABLE.get());
	}

	public static void callEvent(Event event) {
		Logger.debug("[&dEvent&r] " + event.getEventName());
		Bukkit.getPluginManager().callEvent(event);
	}

	/* Getter ---------------------------------------------------------------*/

	public static ExpSign getPlugin() {
		return plugin;
	}

	/* Private Methods ------------------------------------------------------*/

	private void economyCheck() {
		if (!Eco.hasEconomy()) {
			Logger.log(PLUGIN__NO_ECONOMY.get());
		}
	}
}
