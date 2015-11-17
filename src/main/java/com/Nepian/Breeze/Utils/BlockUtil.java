package com.Nepian.Breeze.Utils;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.material.Attachable;

public class BlockUtil {
	/**
	 * ブロックが看板かどうかを判定する
	 * @param block 判定するブロック
	 * @return ブロックが看板かどうか
	 */
	public static boolean isSign(Block block) {
		return block.getState() instanceof Sign;
	}

	/**
	 * 看板は設置されているブロックを取得する
	 * @param sign 設置されている看板
	 * @return 看板が設置されているブロック
	 */
	public static Block getAttachedBlock(Sign sign) {
		return sign.getBlock().getRelative(((Attachable) sign.getData()).getAttachedFace());
	}
}
