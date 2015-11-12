package com.Nepian.ExpSign.ExpBank;

import java.util.UUID;

import org.bukkit.OfflinePlayer;

import com.Nepian.Breeze.Utils.PlayerUtil;
import com.Nepian.ExpSign.Configuration.Properties;
import com.Nepian.ExpSign.Userdata.Userdata;
import com.Nepian.ExpSign.Userdata.UserdataManager;

public class ExpBank {

	/**
	 * ExpBankからお金を引き出す
	 * @param userdata ExpBankのデータをもつユーザデータ
	 * @param quantity 引き出す金額
	 */
	public static void withdraw(Userdata userdata, int quantity) {
		int bankMoney = userdata.getInteger(Properties.EXPBANK_PATH);

		userdata.set(Properties.EXPBANK_PATH, Math.max(0, bankMoney + quantity));
	}

	public static void withdraw(UUID uuid, int quantity) {
		withdraw(UserdataManager.getUserdata(uuid), quantity);
	}

	public static void withdraw(OfflinePlayer player, int quantity) {
		withdraw(player.getUniqueId(), quantity);
	}

	public static void withdraw(String name, int quantity) {
		withdraw(PlayerUtil.getUUID(name), quantity);
	}

	/**
	 * ExpBankにお金を預ける
	 * @param userdata ExpBankのデータをもつユーザデータ
	 * @param quantity 預ける金額
	 */
	public static void deposit(Userdata userdata, int quantity) {
		int bankMoney = userdata.getInteger(Properties.EXPBANK_PATH);

		userdata.set(Properties.EXPBANK_PATH, bankMoney + quantity);
	}

	public static void deposit(UUID uuid, int quantity) {
		deposit(UserdataManager.getUserdata(uuid), quantity);
	}

	public static void deposit(OfflinePlayer player, int quantity) {
		deposit(player.getUniqueId(), quantity);
	}

	public static void deposit(String name, int quantity) {
		deposit(PlayerUtil.getUUID(name), quantity);
	}
}
