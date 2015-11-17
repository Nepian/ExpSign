package com.Nepian.ExpSign.Signs;

import java.util.regex.Pattern;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import com.Nepian.Breeze.Utils.BlockUtil;
import com.Nepian.ExpSign.Configuration.Config;

public class ExpSignShop {
	public static final String EXPSIGNSHOP_NAME = "EXPSHOP";

	public static final byte EXP_LINE = 0;
	public static final byte QUANTITY_LINE = 1;
	public static final byte PRICE_LINE = 2;
	public static final byte NAME_LINE = 3;

	public static final Pattern[] SHOP_SIGN_PATTERN = {
			Pattern.compile("^.*(" + EXPSIGNSHOP_NAME + ").*$", Pattern.CASE_INSENSITIVE),
			Pattern.compile("^[1-9][0-9]*$"),
			Pattern.compile("^[\\dbs(free) :]+$", Pattern.CASE_INSENSITIVE),
			Pattern.compile("^?[\\w -.]*$")
	};

	/* Methods --------------------------------------------------------------*/

	public static boolean isExpSign(String[] line) {
		return SHOP_SIGN_PATTERN[EXP_LINE].matcher(line[EXP_LINE]).matches();
	}

	public static boolean isExpSign(Sign sign) {
		return isExpSign(sign.getLine(EXP_LINE));
	}

	public static boolean isExpSign(String line) {
		return SHOP_SIGN_PATTERN[EXP_LINE].matcher(line).matches();
	}

	public static boolean isAdminShop(String owner) {
		String adminshop = Config.ADMINSHOP_NAME.getString().replace(" ", "");
		return owner.replace(" ", "").equalsIgnoreCase(adminshop);
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
