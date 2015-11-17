package com.Nepian.ExpSign.Listeners.PlayerDataLoad;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Configuration.Config;
import com.Nepian.ExpSign.Configuration.Properties;
import com.Nepian.ExpSign.Events.PlayerDataLoadEvent;
import com.Nepian.ExpSign.PlayerData.PlayerData;

public class NameLoading implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onPlayerDataLoad(PlayerDataLoadEvent event) {

		if (!Config.PLAYERDATA__LOAD__NAME.getBoolean()) {
			return;
		}

		Player player = event.getPlayer();
		PlayerData data = event.getPlayerData();

		data.setData(Properties.PATH_NAME, player.getName());
	}
}
