package com.Nepian.ExpSign.Configuration;

import com.Nepian.Breeze.Configuration.Annotation.ConfigurationComment;
import com.Nepian.Breeze.Configuration.Annotation.PrecededBySpace;

public class Configs {
	@PrecededBySpace
	@ConfigurationComment("�A�h�~���V���b�v�̖��O")
	public static String ADMIN_SHOP_NAME = "Admin Shop";

	@PrecededBySpace
	@ConfigurationComment("EXPBANK�̏����l")
	public static int EXPBANK_INITIAL_MONEY = 1000;

	@PrecededBySpace
	@ConfigurationComment("�R���\�[���Ƀf�o�b�O���b�Z�[�W��\������")
	public static boolean DEBUG = true;
}
