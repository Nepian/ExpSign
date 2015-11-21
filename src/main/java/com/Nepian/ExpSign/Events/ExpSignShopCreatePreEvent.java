package com.Nepian.ExpSign.Events;

import static com.Nepian.ExpSign.Events.ExpSignShopCreatePreEvent.Outcome.*;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ExpSignShopCreatePreEvent extends Event {
	private static final HandlerList handlers = new HandlerList();

	private Outcome outcome = SIGN_CREATED_SUCCESSFULLY;
	private Player creator;
    private Sign sign;
    private String[] signLines;

    /* Constructor ----------------------------------------------------------*/

	public ExpSignShopCreatePreEvent(Player creator, Sign sign, String[] signLines) {
		this.creator = creator;
        this.sign = sign;
        this.signLines = signLines.clone();
    }

	/* Methods --------------------------------------------------------------*/

	public boolean isCancelled() {
		return outcome != SIGN_CREATED_SUCCESSFULLY;
	}

	/* Setter ---------------------------------------------------------------*/

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	public void setSign(Sign sign) {
		this.sign = sign;
	}

	public void setSignLines(String[] signLines) {
		this.signLines = signLines;
	}

	public void setSignLine(byte line, String text) {
		this.signLines[line] = text;
	}

	/* Getter ---------------------------------------------------------------*/

	public Outcome getOutCome() {
		return outcome;
	}

	public Player getPlayer() {
		return creator;
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
		INVALID_PRICE,
		INVALID_QUANTITY,
		NO_PERMISSION,

		OTHER,

		SIGN_CREATED_SUCCESSFULLY
	}
}
