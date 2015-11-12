package com.Nepian.ExpSign.Events;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.Nepian.Breeze.Utils.PriceUtil;
import com.Nepian.ExpSign.Signs.ExpSignShop;

public class ExpSignShopTradeEvent extends Event {
	private static final HandlerList handlers = new HandlerList();

	private Outcome outcome;

	private final TradeType tradeType;
	private final ShopType shopType;
	private Player player;
	private final String ownerName;
	private final int quantity;
	private final double price;

	/* Constructor ----------------------------------------------------------*/

	public ExpSignShopTradeEvent(Player player, Sign sign, TradeType type) {
		this.tradeType = type;
		this.player = player;
		this.ownerName = sign.getLine(ExpSignShop.NAME_LINE);
		this.quantity = Integer.valueOf(sign.getLine(ExpSignShop.QUANTITY_LINE));
		this.price = this.setPrice(sign.getLine(ExpSignShop.PRICE_LINE));
		this.shopType = this.setShopType(ownerName);

		this.outcome = Outcome.SIGN_CREATED_SUCCESSFULLY;
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
		return this.outcome;
	}

	public TradeType getTradeType() {
		return this.tradeType;
	}

	public ShopType getShopType() {
		return this.shopType;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public double getPrice() {
		return this.price;
	}

	public Player getPlayer() {
		return this.player;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	/* Private Methods ------------------------------------------------------*/

	private double setPrice(String line) {
		switch (this.tradeType) {
		case BUY:  return PriceUtil.getBuyPrice(line);
		case SELL: return PriceUtil.getSellPrice(line);
		default:   return PriceUtil.NO_PRICE;
		}
	}

	private ShopType setShopType(String name) {
		if (ExpSignShop.isAdminShop(name)) {
			return ShopType.ADMIN;
		}

		return ShopType.NORMAL;
	}

	/* Enums ----------------------------------------------------------------*/

	public static enum TradeType { BUY, SELL }

	public static enum ShopType { NORMAL, ADMIN }

	public static enum Outcome {
		NO_OWNER_EXP,
		NO_OWNER_MONEY,
		NO_PLAYER_EXP,
		NO_PLAYER_MONEY,
		NO_PERMISSION,
		MAX_EXP,
		OWNER_IS_PLAYER,

		OTHER,

		SIGN_CREATED_SUCCESSFULLY
	}

}
