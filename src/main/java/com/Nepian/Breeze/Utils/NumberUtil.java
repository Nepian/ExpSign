package com.Nepian.Breeze.Utils;

public class NumberUtil {
	/**
	 * 文字列がDouble型に変換可能か調べる
	 * @param string 変換する文字列
	 * @return 文字列がDouble型に変換可能か
	 */
	public static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

	/**
	 * 文字列がInteger型に変換可能か調べる
	 * @param string 変換する文字列
	 * @return 文字列がInteger型に変換可能か
	 */
	public static boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
