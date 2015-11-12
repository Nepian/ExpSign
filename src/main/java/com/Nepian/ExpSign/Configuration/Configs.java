package com.Nepian.ExpSign.Configuration;

import com.Nepian.Breeze.Configuration.Annotation.ConfigurationComment;
import com.Nepian.Breeze.Configuration.Annotation.PrecededBySpace;

public class Configs {
	@PrecededBySpace
	@ConfigurationComment("アドミンショップの名前")
	public static String ADMIN_SHOP_NAME = "Admin Shop";

	@PrecededBySpace
	@ConfigurationComment("EXPBANKの初期値")
	public static int EXPBANK_INITIAL_MONEY = 1000;

	@PrecededBySpace
	@ConfigurationComment("コンソールにデバッグメッセージを表示する")
	public static boolean DEBUG = true;
}
