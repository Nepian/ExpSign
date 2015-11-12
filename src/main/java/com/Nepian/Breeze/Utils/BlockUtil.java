package com.Nepian.Breeze.Utils;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.material.Attachable;

public class BlockUtil {
	/**
	 * �u���b�N���Ŕ��ǂ����𔻒肷��
	 * @param block ���肷��u���b�N
	 * @return �u���b�N���Ŕ��ǂ���
	 */
	public static boolean isSign(Block block) {
		return block.getState() instanceof Sign;
	}

	/**
	 * �Ŕ͐ݒu����Ă���u���b�N���擾����
	 * @param sign �ݒu����Ă���Ŕ�
	 * @return �Ŕ��ݒu����Ă���u���b�N
	 */
	public static Block getAttachedBlock(Sign sign) {
		return sign.getBlock().getRelative(((Attachable) sign.getData()).getAttachedFace());
	}
}
