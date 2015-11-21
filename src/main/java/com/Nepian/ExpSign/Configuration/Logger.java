package com.Nepian.ExpSign.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.Nepian.ExpSign.ExpSign;

public class Logger {
	private static final ExpSign plugin = ExpSign.getPlugin();
	private static final String NAME    = plugin.getName();
	private static final String VERSION = plugin.getDescription().getVersion();

	public static final String DEBUG;
	public static final String PREFIX;
	public static final String EXPSHOP;
	public static final String EVENT;
	public static final String READ;
	public static final String WRITE;

	public static final String PLUGIN__ENABLE;
	public static final String PLUGIN__DISABLE;
	public static final String PLUGIN__NO_ECONOMY;

	public static final String PLAYERDATA__READ;
	public static final String PLAYERDATA__WRITE;
	public static final String PLAYERDATA__READ_ALL;
	public static final String PLAYERDATA__WRITE_ALL;

	public static final String CONFIG__LOAD;
	public static final String CONFIG__WRITE;

	public static final String LANG__LOAD;
	public static final String LANG__WRITE;

	public static String EXPSIGNSHOP__CREATED;

	static {
		DEBUG   = "&7[&eDEBUG&7]&r ";
		PREFIX  = "&3[&a" + NAME + "&3]&a ";
		EXPSHOP = "[&aExpShop&r] ";
		EVENT   = "[&dEvent&r] ";
		READ    = "[&2Read&r] ";
		WRITE   = "[&3WRITE&r] ";

		PLUGIN__ENABLE     = NAME + " v" + VERSION + " Enabled!";
		PLUGIN__DISABLE    = NAME + " Disabled!";
		PLUGIN__NO_ECONOMY = "Not found Economy plugin";

		PLAYERDATA__READ      = READ + "UUID.yml -> PlayerData";
		PLAYERDATA__WRITE     = WRITE + "PlayerData -> UUID.yml";
		PLAYERDATA__READ_ALL  = READ + "All UUID.yml -> All PlayerData";
		PLAYERDATA__WRITE_ALL = WRITE + "All PlayerData -> All UUID.yml";

		CONFIG__LOAD  = READ + "config.yml -> Config";
		CONFIG__WRITE = WRITE + "Config -> config.yml";

		LANG__LOAD  = READ + "lang-xx.yml -> Messages";
		LANG__WRITE = WRITE + "Messages -> lang-xx.yml";

		EXPSIGNSHOP__CREATED = EXPSHOP + "{creater}:{type}";
	}

	public static void log(String msg) {
		if (Config.LOG.getBoolean()) {
			msg = ChatColor.translateAlternateColorCodes('&', PREFIX + msg);
			Bukkit.getServer().getConsoleSender().sendMessage(msg);
		}
	}

	public static void debug(String msg) {
		if (Config.DEBUG.getBoolean()) {
			Logger.log(DEBUG + msg);
		}
	}
}
