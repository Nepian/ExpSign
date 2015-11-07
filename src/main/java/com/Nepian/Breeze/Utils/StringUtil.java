package com.Nepian.Breeze.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class StringUtil {
	/**
	 * 文字列からカラーコードを切り抜く
     * @param string 切り抜く文字列
     * @return 切り抜かれた文字列
     */
    public static String stripColourCodes(String string) {
        return ChatColor.stripColor(string);
    }

    /**
     * 文字列配列からカラーコードを切り抜く
     * @param strings 切り抜く文字列配列
     * @return 切り抜かれた文字列配列
     */
    public static String[] stripColourCodes(String[] strings) {
        List<String> output = new ArrayList<String>();

        for (String string : strings) {
            output.add(stripColourCodes(string));
        }

        return output.toArray(new String[output.size()]);
    }
}
