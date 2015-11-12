package com.Nepian.ExpSign.Listeners.UserdataLoad;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.PlayerUtil;
import com.Nepian.ExpSign.Configuration.Properties;
import com.Nepian.ExpSign.Events.UserdataLoadEvent;
import com.Nepian.ExpSign.Userdata.Userdata;

public class ExpLoading implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onUserdataLoad(UserdataLoadEvent event) {
		Player player = event.getPlayer();
		Userdata userdata = event.getUserdata();
		String path = Properties.EXP_PATH;

		if (!userdata.has(path)) {
			userdata.set(path, PlayerUtil.getExp(player));
		}

		PlayerUtil.setExp(player, userdata.getInteger(path));
	}
}
