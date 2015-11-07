package com.Nepian.ExpSign.Signs;

import java.util.regex.Pattern;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import com.Nepian.Breeze.Utils.BlockUtil;
import com.Nepian.ExpSign.Configuration.Properties;

public class ExpShopSign {
	public static final byte EXP_LINE = 0;
	public static final byte QUANTITY_LINE = 1;
	public static final byte PRICE_LINE = 2;
	public static final byte NAME_LINE = 3;

	public static final Pattern[] SHOP_SIGN_PATTERN = {
			Pattern.compile("(\\[exp\\])", Pattern.CASE_INSENSITIVE),
			Pattern.compile("^[1-9][0-9]*$"),
			Pattern.compile("^[\\d(free):]+$"),
			Pattern.compile("^?[\\w -.]*$")
	};

	/* Methods --------------------------------------------------------------*/

	public static boolean isExpSign(String[] line) {
		return SHOP_SIGN_PATTERN[EXP_LINE].matcher(line[EXP_LINE]).matches();
	}

	public static boolean isAdminShop(String owner) {
		return owner.replace(" ", "").equalsIgnoreCase(Properties.ADMIN_SHOP_NAME.replace(" ", ""));
	}

	public static boolean isValid(Block sign) {
		return BlockUtil.isSign(sign) && isValid((Sign) sign.getState());
	}

	public static boolean isValid(Sign sign) {
		return isValid(sign.getLines());
	}

	public static boolean isValid(String[] line) {
		return isValidPreparedSign(line) && hasPrice(line[PRICE_LINE]) && (!line[NAME_LINE].isEmpty());
	}

	public static boolean isValidPreparedSign(String[] lines) {
		for (int i = 0; i < 4; i++) {
			if (!SHOP_SIGN_PATTERN[i].matcher(lines[i]).matches()) {
				return false;
			}
		}
		return lines[PRICE_LINE].indexOf(':') == lines[PRICE_LINE].lastIndexOf(':');
	}

	/* Private Methods ------------------------------------------------------*/

	private static boolean hasPrice(String line) {
		return (line.toUpperCase().contains("B")) || (line.toUpperCase().contains("S"));
	}
}
