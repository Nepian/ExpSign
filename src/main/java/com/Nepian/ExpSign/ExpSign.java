package com.Nepian.ExpSign;

import static com.Nepian.ExpSign.Configuration.Logger.*;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.ExpSign.Configuration.Logger;
import com.Nepian.ExpSign.Economy.Eco;
import com.Nepian.ExpSign.Listeners.ExpSignShop.ExpSignShopCreate;
import com.Nepian.ExpSign.Listeners.ExpSignShop.ExpSignShopProtect;
import com.Nepian.ExpSign.Listeners.ExpSignShop.ExpSignShopTrade;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePost.ShopCreatedLogger;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.ErrorMessageSender;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.ExpNameChanger;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.NameChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.PermissionChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.PriceChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.QuantityChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeErrorMessageSender;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeExecute;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeMessageSender;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeMoneyChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeNameChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradePermissionChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeQuantityChecker;

public class ExpSign extends JavaPlugin {
	private static ExpSign plugin;
	private static File dataFolder;
	private static File userdataFolder;

	public ExpSign() {
		plugin = this;
		dataFolder = getDataFolder();
		userdataFolder = loadFolder("userdata");
	}

	/* Methods --------------------------------------------------------------*/

	public void onEnable() {
		if (!Eco.hasEconomy()) {
			Logger.log(PLUGIN__NO_ECONOMY.get());
		}

		registerEvents();

		Logger.log(PLUGIN__ENABLE.get());
	}

	public void onDisable() {
		getServer().getScheduler().cancelTasks(this);

		Logger.log(PLUGIN__DISABLE.get());
	}

	public static void callEvent(Event event) {
		Bukkit.getPluginManager().callEvent(event);
	}

	public static File loadFolder(String string) {
		File file = new File(dataFolder, string);

		return loadFolder(file);
	}

	public static File loadFolder(File file) {
		if (!file.exists()) {
			if (file.getParent() != null) {
				file.getParentFile().mkdirs();
			}
			file.mkdir();
		}

		return file;
	}

	public static File loadFile(String string) {
		File file = new File(dataFolder, string);

		return loadFile(file);
	}

	public static File loadFile(File file) {
		if (!file.exists()) {
			try {
				if (file.getParent() != null) {
					file.getParentFile().mkdirs();
				}

				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return file;
	}

	/* Event registers ------------------------------------------------------*/

	private void registerEvents() {

		registerEvent(new ExpSignShopCreate());
		registerExpSignShopCreatePreEvent();
		registerExpSignShopCreatePostEvent();

		registerEvent(new ExpSignShopTrade());
		registerExpSignShopTradeEvent();

		registerEvent(new ExpSignShopProtect());
	}

	private void registerEvent(Listener listener) {
		getServer().getPluginManager().registerEvents(listener, this);
	}

	private void registerExpSignShopCreatePreEvent() {
		registerEvent(new PermissionChecker());
		registerEvent(new QuantityChecker());
		registerEvent(new PriceChecker());
		registerEvent(new NameChecker());
		registerEvent(new ExpNameChanger());
		registerEvent(new ErrorMessageSender());
	}

	private void registerExpSignShopCreatePostEvent() {
		registerEvent(new ShopCreatedLogger());
	}

	private void registerExpSignShopTradeEvent() {
		registerEvent(new TradeNameChecker());
		registerEvent(new TradePermissionChecker());
		registerEvent(new TradeMoneyChecker());
		registerEvent(new TradeQuantityChecker());
		registerEvent(new TradeErrorMessageSender());
		registerEvent(new TradeExecute());
		registerEvent(new TradeMessageSender());
	}

	/* Getter ---------------------------------------------------------------*/

	public static ExpSign getPlugin() {
		return plugin;
	}

	public static File getUserdataFolder() {
		return userdataFolder;
	}
}
