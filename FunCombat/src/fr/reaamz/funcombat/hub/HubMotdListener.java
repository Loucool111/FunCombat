package fr.reaamz.funcombat.hub;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class HubMotdListener implements Listener
{
	@EventHandler
	public void onServerPingEvent(ServerListPingEvent event)
	{
		event.setMotd("§eBienvenue sur FunCombat !\n§41.8 only !");
	}
}
