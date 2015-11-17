package com.Nepian.Breeze.Utils;

import org.bukkit.Location;

public class LocationUtil {

	public static String locationToString(Location location) {
		return '[' + location.getWorld().getName() + "] "
					+ location.getBlockX() + ", "
					+ location.getBlockY() + ", "
					+ location.getBlockZ();
	}
}
