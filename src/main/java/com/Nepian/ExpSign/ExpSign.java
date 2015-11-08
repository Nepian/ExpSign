package com.Nepian.ExpSign;

import static com.Nepian.ExpSign.Configuration.Logger.*;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nepian.Breeze.Configuration.Configuration;
import com.Nepian.ExpSign.Configuration.Logger;
import com.Nepian.ExpSign.Configuration.Messages;
import com.Nepian.ExpSign.Configuration.Properties;
import com.Nepian.ExpSign.Listeners.ExpSignCreate;
import com.Nepian.ExpSign.Listeners.Player.PlayerJoin;
import com.Nepian.ExpSign.Listeners.Player.PlayerQuit;
import com.Nepian.ExpSign.Listeners.PostExpSignShopCreated.ShopCreatedLogger;
import com.Nepian.ExpSign.Listeners.PreExpSignShopCreation.ErrorMessageSender;
import com.Nepian.ExpSign.Listeners.PreExpSignShopCreation.NameChecker;
import com.Nepian.ExpSign.Listeners.PreExpSignShopCreation.PermissionChecker;
import com.Nepian.ExpSign.Listeners.PreExpSignShopCreation.PriceChecker;
import com.Nepian.ExpSign.Listeners.PreExpSignShopCreation.QuantityChecker;
import com.Nepian.ExpSign.Listeners.UserdataLoad.ExpLoading;
import com.Nepian.ExpSign.Listeners.UserdataLoad.NameLoading;
import com.Nepian.ExpSign.Listeners.UserdataSave.ExpSaving;
import com.Nepian.ExpSign.Userdata.UserdataManager;

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
		Configuration.pairFileAndClass(loadFile("config.yml"), Properties.class);
		Configuration.pairFileAndClass(loadFile("log-message.yml"), Logger.class);
		Configuration.pairFileAndClass(loadFile("local.yml"), Messages.class);

		registerEvents();
		UserdataManager.load();

		Logger.log(PLUGIN_ENABLE);
	}

	public void onDisable() {
		UserdataManager.save();

		Logger.log(PLUGIN_DISABLE);
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
		registerUserdatataLoadEvent();
		registerUserdataSaveEvent();

		registerEvent(new ExpSignCreate());
		registerPreExpSignShopCreationEvent();
		registerPostExpSignShopCreatedEvent();

		registerEvent(new PlayerJoin());
		registerEvent(new PlayerQuit());
	}

	private void registerEvent(Listener listener) {
		getServer().getPluginManager().registerEvents(listener, this);
	}

	private void registerUserdatataLoadEvent() {
		registerEvent(new NameLoading());
		registerEvent(new ExpLoading());
	}

	private void registerUserdataSaveEvent() {
		registerEvent(new ExpSaving());
	}

	private void registerPreExpSignShopCreationEvent() {
		registerEvent(new PermissionChecker());
		registerEvent(new QuantityChecker());
		registerEvent(new PriceChecker());
		registerEvent(new NameChecker());
		registerEvent(new ErrorMessageSender());
	}

	private void registerPostExpSignShopCreatedEvent() {
		registerEvent(new ShopCreatedLogger());
	}

	/* Getter ---------------------------------------------------------------*/

	public static ExpSign getPlugin() {
		return plugin;
	}

	public static File getUserdataFolder() {
		return userdataFolder;
	}
}
