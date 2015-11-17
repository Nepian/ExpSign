package com.Nepian.Breeze.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class StringUtil {
	/**
	 * �����񂩂�J���[�R�[�h��؂蔲��
     * @param string �؂蔲��������
     * @return �؂蔲���ꂽ������
     */
    public static String stripColourCodes(String string) {
        return ChatColor.stripColor(string);
    }

    /**
     * ������z�񂩂�J���[�R�[�h��؂蔲��
     * @param strings �؂蔲��������z��
     * @return �؂蔲���ꂽ������z��
     */
    public static String[] stripColourCodes(String[] strings) {
        List<String> output = new ArrayList<String>();

        for (String string : strings) {
            output.add(stripColourCodes(string));
        }

        return output.toArray(new String[output.size()]);
    }
}
