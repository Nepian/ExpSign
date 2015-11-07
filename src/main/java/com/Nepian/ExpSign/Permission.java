package com.Nepian.ExpSign;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Permission {
	SIGN_CREATION_BUY("NepianEXP.sign.create.buy"),
	SIGN_CREATION_SELL("NepianEXP.sign.create.sell"),

	OTHER_NAME("NepianEXP.name."),
	ADMIN("NepianEXP.admin"),

	NOFEE("NepianEXP.nofee");

	private final String permission;

	/* Constructor ----------------------------------------------------------*/

	Permission(String permission) {
		this.permission = permission;
	}

	/* Methods --------------------------------------------------------------*/

	public static boolean has(CommandSender sender, Permission permission) {
		return has(sender, permission.permission);
	}

	public static boolean has(CommandSender sender, String node) {
		return sender.hasPermission(node) || sender.hasPermission(node.toLowerCase());
	}

	public static boolean otherName(Player player, String name) {
		if (has(player, Permission.ADMIN)) return false;

		String node = OTHER_NAME + name;

		return hasPermissionSet(player, node) || hasPermissionSet(player, node.toLowerCase());
	}

	public static boolean hasPermissionSet(CommandSender sender, String permission) {
		return sender.isPermissionSet(permission) && sender.hasPermission(permission);
	}

	public String toString() {
		return permission;
	}
}
