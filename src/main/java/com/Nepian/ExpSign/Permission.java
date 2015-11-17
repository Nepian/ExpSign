package com.Nepian.ExpSign;

import org.bukkit.command.CommandSender;

public enum Permission {
	SHOP_BUY("ExpSign.shop.buy"),
	SHOP_SELL("ExpSign.shop.sell"),
	SIGN_CREATION_BUY("NepianEXP.sign.create.buy"),
	SIGN_CREATION_SELL("NepianEXP.sign.create.sell"),

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

	public static boolean hasPermissionSet(CommandSender sender, String permission) {
		return sender.isPermissionSet(permission) && sender.hasPermission(permission);
	}

	public String toString() {
		return permission;
	}
}
