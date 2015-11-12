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
	@ConfigurationComment("�V���b�v�쐬�֘A�̃��b�Z�[�W")
	public static String INVALID_EXPSIGNSHOP_DETECTED = "�o���lSHOP�Ƃ��Ďg�p�ł��܂���I";
	public static String YOU_CANNOT_CREATE_EXPSIGNSHOP = "���̎�ނ̌o���lSHOP��ݒu�ł��܂���I";
	public static String SUCCESS_CREATE_EXPSGINSHOP = "�o���lSHOP�̊Ŕ�ݒu���܂����I";

	@PrecededBySpace
	@ConfigurationComment("�����֘A�̃��b�Z�[�W")
	public static String SUCCESS_EXPSIGNSHOP_BUY = "�o���l���w�����܂����I";
	public static String SUCCESS_EXPSIGNSHOP_SELL = "�o���l�𔄋p���܂����I";
	public static String NO_EXPSIGNSHOP_OWNER_MONEY = "���̃V���b�v�̎c��������܂���I";
	public static String NO_EXPSIGNSHOP_OWNER_EXP = "���̃V���b�v�̌o���l�͔���؂�ł��I";

	@PrecededBySpace
	@ConfigurationComment("�ی�֘A�̃��b�Z�[�W")
	public static String YOU_ARE_NOT_OWNER = "���̊Ŕ͐ݒu�҂����j��ł��܂���I";
	public static String EXPSIGN_ATTACHED = "���̃u���b�N�͌o���l�Ŕ��ݒu����Ă���̂Ŕj��ł��܂���I";

	@PrecededBySpace
	@ConfigurationComment("�v���C���[�֘A�̃��b�Z�[�W")
	public static String NO_PERMISSION = "���Ȃ��͂��̓���̌����������Ă��܂���I";
	public static String NO_PLAYER_MONEY = "�������s�����Ă��܂��I";
	public static String NO_PLAYER_EXP = "�o���l���s�����Ă��܂��I";
	public static String MAX_PLAYER_EXP = "�o���l�̏���ɒB���Ă��܂��I";

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
