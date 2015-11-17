package com.Nepian.ExpSign.Listeners.PlayerDataLoad;

import static com.Nepian.ExpSign.Configuration.Properties.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Configuration.Config;
import com.Nepian.ExpSign.Events.PlayerDataLoadEvent;
import com.Nepian.ExpSign.PlayerData.PlayerData;

public class ExpBankLoading implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onPlayerDataLoad(PlayerDataLoadEvent event) {

		if (!Config.PLAYERDATA__LOAD__EXPBANK.getBoolean()) {
			return;
		}

		PlayerData data = event.getPlayerData();

		if (!data.hasData(PATH__EXPBANK__EXP)) {
			data.setData(PATH__EXPBANK__EXP, Config.EXPBANK_INITIAL_MONEY.getInt());
		}
	}
}
