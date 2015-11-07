package com.Nepian.ExpSign.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.Nepian.ExpSign.Userdata.Userdata;

public class UserdataLoadEvent extends Event {
	private static final HandlerList handlers = new HandlerList();

	private Player player;
	private Userdata userdata;

	/* Constructor ----------------------------------------------------------*/

	public UserdataLoadEvent(Player player, Userdata userdata) {
		this.player = player;
		this.userdata = userdata;
	}

	/* Getter ---------------------------------------------------------------*/

	public Player getPlayer() { return player; }

	public Userdata getUserdata() { return userdata; }

	public static HandlerList getHandlerList() { return handlers; }

	public HandlerList getHandlers() { return handlers; }

}
