package com.Nepian.ExpSign.Listeners.Player;

import static org.bukkit.event.EventPriority.*;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.Nepian.ExpSign.ExpSign;
import com.Nepian.ExpSign.Events.UserdataSaveEvent;
import com.Nepian.ExpSign.Userdata.Userdata;
import com.Nepian.ExpSign.Userdata.UserdataManager;

public class PlayerQuit implements Listener {

	@EventHandler(priority = LOWEST)
	public static void onPlayerLeave(final PlayerQuitEvent event) {

		Bukkit.getScheduler().runTaskAsynchronously(ExpSign.getPlugin(), new Runnable() {

			@Override
			public void run() {
				Player player = event.getPlayer();
				UUID uuid = player.getUniqueId();
				Userdata userdata = UserdataManager.getUserdata(uuid);
				UserdataSaveEvent saveEvent = new UserdataSaveEvent(player, userdata);

				ExpSign.callEvent(saveEvent);
				userdata.save();
			}

		});

	}
}
