package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePost;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Configuration.Messages;
import com.Nepian.ExpSign.Events.PostExpSignShopCreatedEvent;

public class MessageSender implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPostExpSignShopCreated(PostExpSignShopCreatedEvent event) {
		Messages.sendMessage(event.getPlayer(), Messages.prefix(Messages.SUCCESS_CREATE_EXPSGINSHOP));
	}
}