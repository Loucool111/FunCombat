package ch.reaamz.funcombat.hub;

import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import ch.reaamz.funcombat.Utils;

public class HubMotdListener implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onServerPingEvent(ServerListPingEvent event)
	{
		event.setMotd("§eBienvenue sur " + Utils.PLUGIN_NAME + " !\n§41.8 only ! " + new Date().toLocaleString());
	}
}
