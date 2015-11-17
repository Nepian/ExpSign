package com.Nepian.ExpSign.PlayerData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;

import com.Nepian.ExpSign.ExpSign;
import com.Nepian.ExpSign.Configuration.Files;
import com.Nepian.ExpSign.Configuration.Logger;
import com.Nepian.ExpSign.Events.PlayerDataLoadEvent;
import com.Nepian.ExpSign.Events.PlayerDataSaveEvent;

public class PlayerDataManager implements Listener {
	private static final ExpSign plugin;
	private static final File folder;
	private static Map<UUID, PlayerData> playerDatas;

	static {
		plugin = ExpSign.getPlugin();
		folder = Files.FOLDER_PLAYERDATA;
		playerDatas = new HashMap<UUID, PlayerData>();
	}

	/* Methods --------------------------------------------------------------*/

	public static void load() {
		readPlayerData();
	}

	public static void save() {
		writePlayerData();
	}

	public static void reload() {
		save();
		load();
	}

	public static boolean register(Player player) {
		return registerPlayerData(player.getUniqueId());
	}

	/**
	 * 指定したプレイヤーの PlayerData を取得する
	 * @param player PlayerData を取得するプレイヤー
	 * @param regist true:プレイヤーが存在しない場合に新しく追加する
	 * @return regist:false で PlayerData が存在しない場合は null を返す
	 */
	public static PlayerData get(OfflinePlayer player, boolean regist) {
		return get(player.getUniqueId(), regist);
	}

	/* Private Methods ------------------------------------------------------*/

	private static void readPlayerData() {
		Map<UUID, PlayerData> datas = new HashMap<UUID, PlayerData>();

		for (File file : folder.listFiles()) {
			UUID uuid = UUID.fromString(file.getName().replace(".yml", ""));
			PlayerData data = new PlayerData(file).read();

			datas.put(uuid, data);
		}
		playerDatas = datas;
		Logger.debug(Logger.PLAYERDATA__READ_ALL);
	}

	private static void writePlayerData() {
		for (PlayerData data : playerDatas.values()) {
			data.write();
		}
		Logger.debug(Logger.PLAYERDATA__WRITE_ALL);
	}

	private static boolean registerPlayerData(UUID uuid) {
		String fileName = uuid.toString() + ".yml";
		File file = new File(folder, fileName);

		if (!file.exists()) {
			try {
				if (file.getParent() != null) {
					file.getParentFile().mkdirs();
				}

				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		PlayerData data = new PlayerData(file).read();

		playerDatas.put(uuid, data);

		return true;
	}

	private static PlayerData get(UUID uuid, boolean regist) {
		PlayerData data = playerDatas.get(uuid);

		if (data != null) {
			return data;
		}

		if (!regist) {
			return data;
		}

		registerPlayerData(uuid);

		return playerDatas.get(uuid);
	}

	/* Listeners ------------------------------------------------------------*/

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerJoin(final PlayerJoinEvent event) {
		Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				Player player = event.getPlayer();
				PlayerData data = get(player, true);
				PlayerDataLoadEvent loadEvent = new PlayerDataLoadEvent(player, data);

				ExpSign.callEvent(loadEvent);
			}
		});
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerQuit(final PlayerQuitEvent event) {
		Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				Player player = event.getPlayer();
				PlayerData data = get(player, true);
				PlayerDataSaveEvent saveEvent = new PlayerDataSaveEvent(player, data);

				ExpSign.callEvent(saveEvent);
			}
		});
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onPluginDisable(PluginDisableEvent event) {
		if (!event.getPlugin().equals(plugin)) {
			return;
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerData data = get(player, true);
			PlayerDataSaveEvent saveEvent = new PlayerDataSaveEvent(player, data);
			ExpSign.callEvent(saveEvent);
		}
	}
}
