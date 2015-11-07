package com.Nepian.Breeze.Utils;

public class PriceUtil {
	public static final double NO_PRICE = -1;
	public static final double FREE = 0;

	public static final String FREE_TEXT = "free";

	public static final char BUY_INDICATOR = 'b';
	public static final char SELL_INDICATOR = 's';

	public static double get(String text, char indicator) {
		String[] split = text.replace(" ", "").toLowerCase().split(":");
		String character = String.valueOf(indicator).toLowerCase();

		for (String part : split) {
			if (!part.startsWith(character) && !part.endsWith(character)) continue;

			part = part.replace(character, "");

			if (part.equals(FREE_TEXT)) return FREE;

			if (NumberUtil.isDouble(part)) {
				double price = Double.valueOf(part);

				if (Double.isInfinite(price) || price <= 0) return NO_PRICE;
				else return price;
			}
		}

		return NO_PRICE;
	}

	public static double getBuyPrice(String text) {
		return get(text, BUY_INDICATOR);
	}

	public static double getSellPrice(String text) {
		return get(text, SELL_INDICATOR);
	}

	public static boolean hasBuyPrice(String text) {
		return hasPrice(text, BUY_INDICATOR);
	}

	public static boolean hasSellPrice(String text) {
		return hasPrice(text, SELL_INDICATOR);
	}

	public static boolean hasPrice(String text, char indicator) {
		return get(text, indicator) != NO_PRICE;
	}

	public static boolean isPrice(String text) {
		if (NumberUtil.isDouble(text)) return true;

		return text.trim().equalsIgnoreCase(FREE_TEXT);
	}
}
