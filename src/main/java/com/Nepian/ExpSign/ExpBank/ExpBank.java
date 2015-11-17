package com.Nepian.ExpSign.ExpBank;

import org.bukkit.OfflinePlayer;

import com.Nepian.ExpSign.Configuration.Properties;
import com.Nepian.ExpSign.PlayerData.PlayerData;
import com.Nepian.ExpSign.PlayerData.PlayerDataManager;

public class ExpBank {
	private static final String path = Properties.PATH__EXPBANK__EXP;

	/**
	 * ExpBank����o���l�������o��
	 * @param player �Ώۂ̃v���C���[
	 * @param quantity �����o���o���l��
	 */
	public static void withdraw(OfflinePlayer player, int quantity) {
		withdraw(getPlayerData(player), quantity);
	}

	/**
	 * ExpBank�Ɍo���l��a����
	 * @param player �Ώۂ̃v���C���[
	 * @param quantity �a����o���l��
	 */
	public static void deposit(OfflinePlayer player, int quantity) {
		deposit(getPlayerData(player), quantity);
	}

	/**
	 * ExpBank�̌o���l���m�F����
	 * @param userdata �m�F���郆�[�U�̃��[�U�f�[�^
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
