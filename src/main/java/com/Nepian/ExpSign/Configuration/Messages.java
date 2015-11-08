package com.Nepian.ExpSign.Configuration;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.Nepian.Breeze.Configuration.Annotation.PrecededBySpace;
import com.Nepian.ExpSign.ExpSign;

public class Messages {
	@PrecededBySpace
	public static String PREFIX = "&3[&a" + ExpSign.getPlugin().getName() + "&3]&r ";

	@PrecededBySpace
	public static String INVALID_EXPSIGNSHOP_DETECTED = "�o���lSHOP�Ƃ��Ďg�p�ł��܂���I";
	public static String YOU_CANNOT_CREATE_EXPSIGNSHOP = "���̎�ނ̌o���lSHOP��ݒu�ł��܂���I";
	public static String SUCCESS_CREATE_EXPSGINSHOP = "�o���lSHOP�̊Ŕ�ݒu���܂����I";

	@PrecededBySpace
	public static String NO_PERMISSION = "���Ȃ��͂��̓���̌����������Ă��܂���I";

	public static String prefix(String message) {
		return PREFIX + message;
	}

	public static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
}
