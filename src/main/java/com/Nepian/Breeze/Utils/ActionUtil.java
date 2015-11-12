package com.Nepian.Breeze.Utils;

import org.bukkit.event.block.Action;

public class ActionUtil {

	/**
	 * アクションがブロックをクリックしたアクションかを判定
	 * @param action 対象のアクション
	 * @return ブロックをクリックしていない場合は false
	 */
	public static boolean isClickedBlock(Action action) {
		return (action == Action.LEFT_CLICK_BLOCK)
				|| (action == Action.RIGHT_CLICK_BLOCK);
	}

	/**
	 * アクションが右クリックかを判定
	 * @param action 対象のアクション
	 * @return 右クリック以外の場合は false
	 */
	public static boolean isRightClicked(Action action) {
		return (action == Action.RIGHT_CLICK_AIR)
				|| (action == Action.RIGHT_CLICK_BLOCK);
	}

	/**
	 * アクションが左クリックかを判定
	 * @param action 対象のアクション
	 * @return 左クリック以外の場合は false
	 */
	public static boolean isLeftClicked(Action action) {
		return (action == Action.LEFT_CLICK_AIR)
				|| (action == Action.LEFT_CLICK_BLOCK);
	}
}
