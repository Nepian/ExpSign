package com.Nepian.ExpSign.Listeners.UserdataSave;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.Breeze.Utils.PlayerUtil;
import com.Nepian.ExpSign.Configuration.Properties;
import com.Nepian.ExpSign.Events.UserdataSaveEvent;
import com.Nepian.ExpSign.Userdata.Userdata;

public class ExpSaving implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onUserdataSave(UserdataSaveEvent event) {
		Player player = event.getPlayer();
		Userdata userdata = event.getUserdata();

		userdata.set(Properties.EXP_PATH, PlayerUtil.getExp(player));
	}
}
