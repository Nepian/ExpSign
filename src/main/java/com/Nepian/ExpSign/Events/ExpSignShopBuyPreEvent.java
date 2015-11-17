package com.Nepian.ExpSign.Events;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ExpSignShopBuyPreEvent extends Event {
	private static final HandlerList handlers = new HandlerList();

	private Outcome outcome = Outcome.SIGN_CREATED_SUCCESSFULLY;
	private Player player;
    private Sign sign;
    private String[] signLines;

    /* Constructor ----------------------------------------------------------*/

	public ExpSignShopBuyPreEvent(Player player, Sign sign) {
		this.player = player;
		this.sign = sign;
		this.signLines = sign.getLines();
	}

	/* Methods --------------------------------------------------------------*/

	public boolean isCancelled() {
		return outcome != Outcome.SIGN_CREATED_SUCCESSFULLY;
	}

	/* Setter ---------------------------------------------------------------*/

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	/* Getter ---------------------------------------------------------------*/

	public Outcome getOutCome() {
		return outcome;
	}

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

	/* Enum -----------------------------------------------------------------*/

	public static enum Outcome {
		NO_OWNER_EXP,
		NO_PLAYER_MONEY,
		NO_PERMISSION,
		MAX_EXP,

		OTHER,

		SIGN_CREATED_SUCCESSFULLY
	}
}
