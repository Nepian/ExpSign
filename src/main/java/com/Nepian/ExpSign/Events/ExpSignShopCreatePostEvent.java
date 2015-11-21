package com.Nepian.ExpSign.Events;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ExpSignShopCreatePostEvent extends Event {
	private static final HandlerList handlers = new HandlerList();

	private final Player creator;
	private final Sign sign;
	private final String[] signLines;

	/* Constructor ----------------------------------------------------------*/

	public ExpSignShopCreatePostEvent(Player creator, Sign sign, String[] signLines) {
		this.creator = creator;
		this.sign = sign;
		this.signLines = signLines.clone();
	}

	/* Getter ---------------------------------------------------------------*/

	public String getSignLine(short line) {
		return signLines[line];
	}

	public String[] getSignLines() {
		return signLines;
	}

	public Player getPlayer() {
		return creator;
	}

	public Sign getSign() {
		return sign;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
