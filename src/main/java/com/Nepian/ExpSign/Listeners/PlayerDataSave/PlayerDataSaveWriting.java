package com.Nepian.ExpSign.Listeners.PlayerDataSave;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Events.PlayerDataSaveEvent;

public class PlayerDataSaveWriting implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerDataSave(PlayerDataSaveEvent event) {
		event.getPlayerData().write();
	}
}
