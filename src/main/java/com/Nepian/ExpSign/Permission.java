package com.Nepian.ExpSign;

import org.bukkit.command.CommandSender;

public enum Permission {
	SHOP_BUY("ExpSign.shop.buy"),
	SHOP_SELL("ExpSign.shop.sell"),
	SIGN_CREATION_BUY("ExpSign.sign.create.buy"),
	SIGN_CREATION_SELL("ExpSign.sign.create.sell"),

	ADMIN("ExpSign.admin");

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

	public String toString() {
		return permission;
	}
}
