package com.Nepian.ExpSign.Configuration;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.Nepian.Breeze.Configuration.Annotation.ConfigurationComment;
import com.Nepian.Breeze.Configuration.Annotation.PrecededBySpace;
import com.Nepian.ExpSign.ExpSign;

public class Messages {
	@PrecededBySpace
	public static String PREFIX = "&3[&a" + ExpSign.getPlugin().getName() + "&3]&r ";

	@PrecededBySpace
	@ConfigurationComment("ショップ作成関連のメッセージ")
	public static String INVALID_EXPSIGNSHOP_DETECTED = "経験値SHOPとして使用できません！";
	public static String YOU_CANNOT_CREATE_EXPSIGNSHOP = "この種類の経験値SHOPを設置できません！";
	public static String SUCCESS_CREATE_EXPSGINSHOP = "経験値SHOPの看板を設置しました！";

	@PrecededBySpace
	@ConfigurationComment("売買関連のメッセージ")
	public static String SUCCESS_EXPSIGNSHOP_BUY = "経験値を購入しました！";
	public static String SUCCESS_EXPSIGNSHOP_SELL = "経験値を売却しました！";
	public static String NO_EXPSIGNSHOP_OWNER_MONEY = "このショップの残金がありません！";
	public static String NO_EXPSIGNSHOP_OWNER_EXP = "このショップの経験値は売り切れです！";

	@PrecededBySpace
	@ConfigurationComment("保護関連のメッセージ")
	public static String YOU_ARE_NOT_OWNER = "この看板は設置者しか破壊できません！";
	public static String EXPSIGN_ATTACHED = "このブロックは経験値看板が設置されているので破壊できません！";

	@PrecededBySpace
	@ConfigurationComment("プレイヤー関連のメッセージ")
	public static String NO_PERMISSION = "あなたはこの動作の権限を持っていません！";
	public static String NO_PLAYER_MONEY = "お金が不足しています！";
	public static String NO_PLAYER_EXP = "経験値が不足しています！";
	public static String MAX_PLAYER_EXP = "経験値の上限に達しています！";

	public static String prefix(String message) {
		return PREFIX + message;
	}

	public static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}

	public static void sendPrefixMessage(CommandSender sender, String message) {
		sendMessage(sender, prefix(message));
	}
}
