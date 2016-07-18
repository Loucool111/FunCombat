package ch.reaamz.funcombat.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class JumpDoneEvent extends Event
{
	public static final HandlerList handlers = new HandlerList();
	private String jumpName;
	private Player player;
	private int time;
	
	public JumpDoneEvent(String jumpName, Player player, int time)
	{
		this.jumpName = jumpName;
		this.player = player;
		this.time = time;
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
	
	public String getJumpName()
	{
		return this.jumpName;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public int getTime()
	{
		return this.time;
	}
}
