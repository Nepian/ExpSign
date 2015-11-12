package com.Nepian.ExpSign.Events;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ExpSignShopBuyPostEvent extends Event {
	private static final HandlerList handlers = new HandlerList();

	private Player player;
    private Sign sign;
    private String[] signLines;

    /* Constructor ----------------------------------------------------------*/

	public ExpSignShopBuyPostEvent(Player player, Sign sign) {
		this.player = player;
		this.sign = sign;
		this.signLines = sign.getLines();
	}

	/* Getter ---------------------------------------------------------------*/

	public Player getPlayer() {
		return player;
	}

	public Sign getSign() {
		return sign;
	}

	public String[] getSignLines() {
		return signLines;
	}

	public String getSignLine(byte line) {
		return signLines[line];
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public HandlerList getHandlers() {
		return handlers;
	}
}
