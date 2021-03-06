package com.Nepian.ExpSign.Listeners.ExpSignShop.CreatePost;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Nepian.ExpSign.Configuration.Messages;
import com.Nepian.ExpSign.Events.ExpSignShopCreatePostEvent;

public class MessageSender implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onCreatePost(ExpSignShopCreatePostEvent event) {
		final Messages message = Messages.EXPSIGNSHOP__SUCCESS_CREATED;

		Messages.sendPrefixMessage(event.getPlayer(), message);
	}
}
