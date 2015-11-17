package com.Nepian.ExpSign.Configuration;

import static com.Nepian.ExpSign.Configuration.Properties.*;

import java.io.File;
import java.io.IOException;

import com.Nepian.ExpSign.ExpSign;

public class Files {
	private static final ExpSign plugin;

	public static final File FOLDER_MAIN;
	public static final File FOLDER_PLAYERDATA;

	public static final File FILE_CONFIG;
	public static final File FILE_LANG;

	enum FileType { FILE, FOLDER }

	static {
		plugin = ExpSign.getPlugin();
		FOLDER_MAIN = plugin.getDataFolder();
		FOLDER_PLAYERDATA = load(NAME_FOLDER_PLAYERDATA, FileType.FOLDER);
		FILE_CONFIG = load(NAME_FILE_CONFIG, FileType.FILE);
		FILE_LANG = load(Config.LANG.getString(), FileType.FILE);
	}

	/* Private Methods ------------------------------------------------------*/

	private static File load(String string, FileType type) {
		File file = new File(FOLDER_MAIN, string);

		return load(file, type);
	}

	private static File load(File file, FileType type) {
		if (!file.exists()) {
			try {
				if (file.getParent() != null) {
					file.getParentFile().mkdirs();
				}

				switch (type) {
				case FILE:
					file.createNewFile();
					break;
				case FOLDER:
					file.mkdir();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return file;
	}
}
