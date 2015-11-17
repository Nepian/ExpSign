package com.Nepian.ExpSign.Listeners.PlayerDataLoad;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Events.PlayerDataLoadEvent;

public class PlayerDataLoadWriting implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerDataLoad(PlayerDataLoadEvent event) {
		event.getPlayerData().write();
	}
}
