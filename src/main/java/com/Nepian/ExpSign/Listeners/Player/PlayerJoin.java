package com.Nepian.ExpSign.Listeners.Player;

import static org.bukkit.event.EventPriority.*;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.Nepian.ExpSign.ExpSign;
import com.Nepian.ExpSign.Events.UserdataLoadEvent;
import com.Nepian.ExpSign.Userdata.Userdata;
import com.Nepian.ExpSign.Userdata.UserdataManager;

public class PlayerJoin implements Listener {

	@EventHandler(priority = MONITOR, ignoreCancelled = true)
	public static void onPlayerJoin(final PlayerJoinEvent event) {

		Bukkit.getScheduler().runTaskAsynchronously(ExpSign.getPlugin(), new Runnable() {

			@Override
			public void run() {
				Player player = event.getPlayer();
				UUID uuid = player.getUniqueId();
				Userdata userdata = UserdataManager.getUserdata(uuid);

				if (userdata == null) {
					userdata = new Userdata(uuid);
					UserdataManager.put(uuid, userdata);
				}

				UserdataLoadEvent loadEvent = new UserdataLoadEvent(player, userdata);

				ExpSign.callEvent(loadEvent);
				userdata.save();
			}

		});

	}
}
