package com.Nepian.ExpSign;

import org.bukkit.command.CommandSender;

public enum Permission {
	SHOP_BUY("ExpSign.shop.buy"),
	SHOP_SELL("ExpSign.shop.sell"),
	SHOP_CREATE_BUY("ExpSign.shop.create.buy"),
	SHOP_CREATE_SELL("ExpSign.shop.create.sell"),

	BANK_CREATE_WITHDRAW("ExpSign.bank.create.withdraw"),
	BANK_CREATE_DEPOSIT("ExpSign.bank.create.deposit"),
	BANK_WITHDRAW("ExpSign.bank.withdraw"),
	BANK_DEPOSIT("ExpSign.bank.deposit"),
	BANK_CHECK("ExpSign.bank.check"),

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
