package com.Nepian.ExpSign.ExpBank;

import org.bukkit.OfflinePlayer;

import com.Nepian.ExpSign.Configuration.Properties;
import com.Nepian.ExpSign.PlayerData.PlayerData;
import com.Nepian.ExpSign.PlayerData.PlayerDataManager;

public class ExpBank {
	private static final String path = Properties.PATH__EXPBANK__EXP;

	/**
	 * ExpBankから経験値を引き出す
	 * @param player 対象のプレイヤー
	 * @param quantity 引き出す経験値量
	 */
	public static void withdraw(OfflinePlayer player, int quantity) {
		withdraw(getPlayerData(player), quantity);
	}

	/**
	 * ExpBankに経験値を預ける
	 * @param player 対象のプレイヤー
	 * @param quantity 預ける経験値量
	 */
	public static void deposit(OfflinePlayer player, int quantity) {
		deposit(getPlayerData(player), quantity);
	}

	/**
	 * ExpBankの経験値を確認する
	 * @param userdata 確認するユーザのユーザデータ
	 */
	public static int check(OfflinePlayer player) {
		return check(getPlayerData(player));
	}

	/*  Private Methods -----------------------------------------------------*/

	private static void withdraw(PlayerData data, int quantity) {
		int exp = data.getInt(path);

		data.setData(path, Math.max(0, exp - quantity));
	}

	private static void deposit(PlayerData data, int quantity) {
		int exp = data.getInt(path);

		data.setData(path, exp + quantity);
	}

	private static int check(PlayerData data) {
		return data.getInt(path);
	}

	private static PlayerData getPlayerData(OfflinePlayer player) {
		return PlayerDataManager.get(player, true);
	}
}
