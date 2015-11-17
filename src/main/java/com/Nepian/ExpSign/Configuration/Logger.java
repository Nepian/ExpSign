package com.Nepian.ExpSign.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.Nepian.ExpSign.ExpSign;

public enum Logger {
	PREFIX("&3[&a{plugin}&3]&a "),

	PLUGIN__ENABLE("{plugin} v{version} Enabled!"),
	PLUGIN__DISABLE("{plugin} Disabled!"),
	PLUGIN__NO_ECONOMY("Not found Economy plugin"),

	EXPSIGNSHOP__CREATED("[&aExpShop&r] {creater}:{type}:{amount}-{prices}:{location}"),

	PLAYERDATA__READ("[&2Read&r] UUID.yml -> PlayerData"),
	PLAYERDATA__WRITE("[&3WRITE&r] PlayerData -> UUID.yml"),
	PLAYERDATA__READ_ALL("[&2Read&r] All UUID.yml -> All PlayerData"),
	PLAYERDATA__WRITE_ALL("[&3WRITE&r] All PlayerData -> All UUID.yml"),

	CONFIG__LOAD("[&2Read&r] config.yml -> Config"),
	CONFIG__WRITE("[&3WRITE&r] Config -> config.yml"),

	LANG__LOAD("[&2Read&r] lang-xx.yml -> Messages"),
	LANG__WRITE("[&3WRITE&r] Messages -> lang-xx.yml");

	private static final ExpSign plugin;
	private String message;

	static {
		plugin = ExpSign.getPlugin();
	}

	Logger(String message) {
		this.message = message;
	}

	public String get() {
		this.message =
			this.message
			.replace("{plugin}", plugin.getName())
			.replace("{version}", plugin.getDescription().getVersion());
		return this.message;
	}

	public static void log(String msg) {
		if (Config.LOG.getBoolean()) {
			msg = ChatColor.translateAlternateColorCodes('&', PREFIX.get() + msg);
			Bukkit.getServer().getConsoleSender().sendMessage(msg);
		}
	}

	public static void debug(String msg) {
		if (Config.DEBUG.getBoolean()) {
			Logger.log("&7[&eDEBUG&7]&r " + msg);
		}
	}
}
