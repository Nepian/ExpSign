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

	EXPSIGNSHOP__INVALID_DETECTED("[ExpShop]�Ƃ��Ďg�p�ł��܂���I"),
	EXPSIGNSHOP__CANNOT_CREATE("[ExpShop]��ݒu�ł��܂���I"),
	EXPSIGNSHOP__CANNOT_BUY("���̃V���b�v�͌o���l���w���ł��܂���I"),
	EXPSIGNSHOP__CANNOT_SELL("���̃V���b�v�͌o���l�𔄋p�ł��܂���I"),
	EXPSIGNSHOP__SUCCESS_CREATED("[ExpShop]�̊Ŕ�ݒu���܂����I"),
	EXPSIGNSHOP__SUCCESS_BUY("�o���l���w�����܂����I"),
	EXPSIGNSHOP__SUCCESS_SELL("�o���l�𔄋p���܂����I"),
	EXPSIGNSHOP__NO_OWNER_MONEY("���̃V���b�v�̎c��������܂���I"),
	EXPSIGNSHOP__NO_OWNER_EXP("���̃V���b�v�̌o���l�͔���؂�ł��I"),
	EXPSIGNSHOP__OWNER_MINE("�����̃V���b�v�ł̔����͂ł��܂���I"),

	PROTECT__SIGN_NOT_OWNER("���̊Ŕ͐ݒu�҂����j��ł��܂���I"),
	PROTECT__SIGN_ATTACHED_EXPSIGN("���̃u���b�N�͔j��ł��܂���I"),
	PROTECT__SIGN_OTHER("���̓����[ExpSign]�ɂ���ĕی삳��Ă��܂�"),

	PLAYER__NO_PERMISSION("���̓���̌����������Ă��܂���I"),
	PLAYER__NO_MONEY("�������s�����Ă��܂��I"),
	PLAYER__NO_EXP("�o���l���s�����Ă��܂��I"),
	PLAYER__MAX_EXP("�o���l�̏���ɒB���Ă��܂��I"),

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
