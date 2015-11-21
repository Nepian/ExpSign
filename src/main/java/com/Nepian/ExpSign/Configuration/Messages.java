package com.Nepian.ExpSign.Configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.Nepian.ExpSign.ExpSign;

public enum Messages {
	PREFIX("&3[&a{plugin}&3]&r "),

	EXPSIGNSHOP__INVALID_DETECTED("[ExpShop]として使用できません！"),
	EXPSIGNSHOP__CANNOT_CREATE("[ExpShop]を設置できません！"),
	EXPSIGNSHOP__CANNOT_BUY("このショップは経験値を購入できません！"),
	EXPSIGNSHOP__CANNOT_SELL("このショップは経験値を売却できません！"),
	EXPSIGNSHOP__SUCCESS_CREATED("[ExpShop]の看板を設置しました！"),
	EXPSIGNSHOP__SUCCESS_BUY("経験値を購入しました！"),
	EXPSIGNSHOP__SUCCESS_SELL("経験値を売却しました！"),
	EXPSIGNSHOP__NO_OWNER_MONEY("このショップの残金がありません！"),
	EXPSIGNSHOP__NO_OWNER_EXP("このショップの経験値は売り切れです！"),
	EXPSIGNSHOP__OWNER_MINE("自分のショップでの売買はできません！"),

	PROTECT__SIGN_NOT_OWNER("この看板は設置者しか破壊できません！"),
	PROTECT__SIGN_ATTACHED_EXPSIGN("このブロックは破壊できません！"),
	PROTECT__SIGN_OTHER("この動作は[ExpSign]によって保護されています"),

	PLAYER__NO_PERMISSION("この動作の権限を持っていません！"),
	PLAYER__NO_MONEY("お金が不足しています！"),
	PLAYER__NO_EXP("経験値が不足しています！"),
	PLAYER__MAX_EXP("経験値の上限に達しています！"),

	OTHER("");

	private static final ExpSign plugin;
	private String message;

	static {
		plugin = ExpSign.getPlugin();
	}

	/* Constructor ----------------------------------------------------------*/

	Messages(String message) {
		this.message = message;
	}

	/* Methods --------------------------------------------------------------*/

	public String get() {
		return this.message;
	}

	public static void sendPrefixMessage(CommandSender sender, String message) {
		sendMessage(sender, prefix(message));
	}

	public static void sendPrefixMessage(CommandSender sender, Messages message) {
		sendMessage(sender, message.message);
	}

	public static void load(File file) {
		read(file);
		save(file);
	}

	public static void save(File file) {
		write(file);
	}

	/* Private Methods ------------------------------------------------------*/

	private static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}

	private static String prefix(String message) {
		return PREFIX.get().replace("{plugin}", plugin.getName()) + message;
	}

	private String toPath() {
		return this.toString().toLowerCase().replace("__", ".");
	}

	private static void read(File file) {
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);

		for (Messages key : values()) {
			String path = key.toPath();

			if (conf.contains(path)) {
				key.message = (String) conf.get(path);
			}
		}

		Logger.debug(Logger.LANG__LOAD);
	}

	private static void write(File file) {
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);

		for (Messages key : values()) {
			conf.set(key.toPath(), key.message);
		}

		try {
			conf.save(file);
			Logger.debug(Logger.LANG__WRITE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
