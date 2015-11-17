package com.Nepian.Breeze.Utils;

import org.bukkit.event.block.Action;

public class ActionUtil {

	/**
	 * �A�N�V�������u���b�N���N���b�N�����A�N�V�������𔻒�
	 * @param action �Ώۂ̃A�N�V����
	 * @return �u���b�N���N���b�N���Ă��Ȃ��ꍇ�� false
	 */
	public static boolean isClickedBlock(Action action) {
		return (action == Action.LEFT_CLICK_BLOCK)
				|| (action == Action.RIGHT_CLICK_BLOCK);
	}

	/**
	 * �A�N�V�������E�N���b�N���𔻒�
	 * @param action �Ώۂ̃A�N�V����
	 * @return �E�N���b�N�ȊO�̏ꍇ�� false
	 */
	public static boolean isRightClicked(Action action) {
		return (action == Action.RIGHT_CLICK_AIR)
				|| (action == Action.RIGHT_CLICK_BLOCK);
	}

	/**
	 * �A�N�V���������N���b�N���𔻒�
	 * @param action �Ώۂ̃A�N�V����
	 * @return ���N���b�N�ȊO�̏ꍇ�� false
	 */
	public static boolean isLeftClicked(Action action) {
		return (action == Action.LEFT_CLICK_AIR)
				|| (action == Action.LEFT_CLICK_BLOCK);
	}
}
