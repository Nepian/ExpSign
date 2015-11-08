package com.Nepian.ExpSign.Configuration;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.Nepian.Breeze.Configuration.Annotation.PrecededBySpace;
import com.Nepian.ExpSign.ExpSign;

public class Messages {
	@PrecededBySpace
	public static String PREFIX = "&3[&a" + ExpSign.getPlugin().getName() + "&3]&r ";

	@PrecededBySpace
	public static String INVALID_EXPSIGNSHOP_DETECTED = "経験値SHOPとして使用できません！";
	public static String YOU_CANNOT_CREATE_EXPSIGNSHOP = "この種類の経験値SHOPを設置できません！";
	public static String SUCCESS_CREATE_EXPSGINSHOP = "経験値SHOPの看板を設置しました！";

	@PrecededBySpace
	public static String NO_PERMISSION = "あなたはこの動作の権限を持っていません！";

	public static String prefix(String message) {
		return PREFIX + message;
	}

	public static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
}
