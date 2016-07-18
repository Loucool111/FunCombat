package ch.reaamz.funcombat.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class HubEvent extends Event
{
	public static final HandlerList handlers = new HandlerList();
	private String serverFrom;
	private Player player;
	
	public HubEvent(String serverFrom, Player player) 
	{
		this.serverFrom = serverFrom;
		this.player = player;
	}
	
	@Override
	public HandlerList getHandlers() 
	{
		return handlers;
	}
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}
	
	public String getServerFrom()
	{
		return serverFrom;
	}
	
	public Player getPlayer()
	{
		return player;
	}
}
