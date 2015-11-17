package com.Nepian.ExpSign;

import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Listeners.ExpSignShop.ExpSignShopCreate;
import com.Nepian.ExpSign.Listeners.ExpSignShop.ExpSignShopProtect;
import com.Nepian.ExpSign.Listeners.ExpSignShop.ExpSignShopTrade;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePost.ShopCreatedLogger;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.ErrorMessageSender;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.ExpNameChanger;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.NameChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.PermissionChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.PriceChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePre.QuantityChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeErrorMessageSender;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeExecute;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeMessageSender;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeMoneyChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeNameChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradePermissionChecker;
import com.Nepian.ExpSign.Listeners.ExpSignShop.Trade.TradeQuantityChecker;
import com.Nepian.ExpSign.Listeners.PlayerDataLoad.NameLoading;
import com.Nepian.ExpSign.Listeners.PlayerDataLoad.PlayerDataLoadWriting;
import com.Nepian.ExpSign.Listeners.PlayerDataSave.PlayerDataSaveWriting;
import com.Nepian.ExpSign.PlayerData.PlayerDataManager;

public class EventRegister {
	private static final ExpSign plugin;

	static {
		plugin = ExpSign.getPlugin();
	}

	public static void load() {
		registerEvent(new ExpSignShopCreate());
		registerExpSignShopCreatePreEvent();
		registerExpSignShopCreatePostEvent();

		registerEvent(new ExpSignShopTrade());
		registerExpSignShopTradeEvent();

		registerEvent(new PlayerDataManager());
		registerPlayerDataLoadEvent();
		registerPlayerDataSaveEvent();

		registerEvent(new ExpSignShopProtect());
	}

	/* Private Methods ------------------------------------------------------*/

	private static void registerExpSignShopCreatePreEvent() {
		registerEvent(new PermissionChecker());
		registerEvent(new QuantityChecker());
		registerEvent(new PriceChecker());
		registerEvent(new NameChecker());
		registerEvent(new ExpNameChanger());
		registerEvent(new ErrorMessageSender());
	}

	private static void registerExpSignShopCreatePostEvent() {
		registerEvent(new ShopCreatedLogger());
	}

	private static void registerExpSignShopTradeEvent() {
		registerEvent(new TradeNameChecker());
		registerEvent(new TradePermissionChecker());
		registerEvent(new TradeMoneyChecker());
		registerEvent(new TradeQuantityChecker());
		registerEvent(new TradeErrorMessageSender());
		registerEvent(new TradeExecute());
		registerEvent(new TradeMessageSender());
	}

	private static void registerPlayerDataLoadEvent() {
		registerEvent(new NameLoading());
		registerEvent(new PlayerDataLoadWriting());
	}

	private static void registerPlayerDataSaveEvent() {
		registerEvent(new PlayerDataSaveWriting());
	}

	private static void registerEvent(Listener listener) {
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);
	}
}
