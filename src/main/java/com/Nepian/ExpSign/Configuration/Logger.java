package com.Nepian.ExpSign.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.Nepian.Breeze.Configuration.Annotation.PrecededBySpace;
import com.Nepian.ExpSign.ExpSign;

public class Logger {
	private static final ExpSign plugin = ExpSign.getPlugin();

	@PrecededBySpace
	public static String PREFIX = "&3[&a" + ExpSign.getPlugin().getName() + "&3] ";

	@PrecededBySpace
	public static String PLUGIN_ENABLE = plugin.getName() + " v" + plugin.getDescription().getVersion() + " Enabled!";
	public static String PLUGIN_DISABLE = plugin.getName() + " Disabled!";

	@PrecededBySpace
	public static String USERDATA_FOLDER_MAKING = "ユーザデータフォルダを生成しました";
	public static String USERDATA_UUID_LOAD = "UUIDを読み込みました";
	public static String USERDATA_LOAD = "ユーザデータを読み込みました";
	public static String USERDATA_SAVE_ALL = "ユーザデータを全て保存しました";

	@PrecededBySpace
	public static String EXPSIGNSHOP_CREATED_LOG = "{creater} が {type} を {amount} - {prices} で {location} に設置しました";

	public static void log(String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', PREFIX + msg);
		Bukkit.getServer().getConsoleSender().sendMessage(msg);
		return;
	}

	public static void debug(String msg) {
		Logger.log("&7[&eDEBUG&7]&r " + msg);
	}
}
