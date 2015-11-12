package com.Nepian.ExpSign.Listeners.UserdataLoad;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Configuration.Properties;
import com.Nepian.ExpSign.Events.UserdataLoadEvent;
import com.Nepian.ExpSign.Userdata.Userdata;

public class NameLoading implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onUserdataLoad(UserdataLoadEvent event) {
		Userdata userdata = event.getUserdata();
		String path = Properties.NAME_PATH;

		if (!userdata.has(path)) {
			userdata.set(path, event.getPlayer().getName());
		}
	}
}
