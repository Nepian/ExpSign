package com.Nepian.ExpSign.ExpBank;

import java.util.UUID;

import org.bukkit.OfflinePlayer;

import com.Nepian.Breeze.Utils.PlayerUtil;
import com.Nepian.ExpSign.Configuration.Properties;
import com.Nepian.ExpSign.Userdata.Userdata;
import com.Nepian.ExpSign.Userdata.UserdataManager;

public class ExpBank {

	/**
	 * ExpBankから経験値を引き出す
	 * @param userdata ExpBankのデータをもつユーザデータ
	 * @param quantity 引き出す経験値量
	 */
	public static void withdraw(Userdata userdata, int quantity) {
		int bankMoney = userdata.getInteger(Properties.EXPBANK_PATH);

		userdata.set(Properties.EXPBANK_PATH, Math.max(0, bankMoney - quantity));
	}

	public static void withdraw(UUID uuid, int quantity) {
		withdraw(getUserdata(uuid), quantity);
	}

	public static void withdraw(OfflinePlayer player, int quantity) {
		withdraw(getUniqueId(player), quantity);
	}

	public static void withdraw(String name, int quantity) {
		withdraw(getUniqueId(name), quantity);
	}

	/**
	 * ExpBankに経験値を預ける
	 * @param userdata ExpBankのデータをもつユーザデータ
	 * @param quantity 預ける経験値量
	 */
	public static void deposit(Userdata userdata, int quantity) {
		int bankMoney = userdata.getInteger(Properties.EXPBANK_PATH);

		userdata.set(Properties.EXPBANK_PATH, bankMoney + quantity);
	}

	public static void deposit(UUID uuid, int quantity) {
		deposit(getUserdata(uuid), quantity);
	}

	public static void deposit(OfflinePlayer player, int quantity) {
		deposit(getUniqueId(player), quantity);
	}

	public static void deposit(String name, int quantity) {
		deposit(getUniqueId(name), quantity);
	}

	/**
	 * ExpBankの経験値を確認する
	 * @param userdata 確認するユーザのユーザデータ
	 */
	public static int check(Userdata userdata) {
		return userdata.getInteger(Properties.EXPBANK_PATH);
	}

	public static int check(UUID uuid) {
		return check(getUserdata(uuid));
	}

	public static int check(OfflinePlayer player) {
		return check(getUniqueId(player));
	}

	public static int check(String name) {
		return check(getUniqueId(name));
	}

	/*  Private Methods -----------------------------------------------------*/

	private static Userdata getUserdata(UUID uuid) {
		return UserdataManager.getUserdata(uuid);
	}

	private static UUID getUniqueId(OfflinePlayer player) {
		return player.getUniqueId();
	}

	private static UUID getUniqueId(String name) {
		return PlayerUtil.getUUID(name);
	}
}
