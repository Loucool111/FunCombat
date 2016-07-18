package ch.reaamz.funcombat.party;

import java.util.List;

import org.bukkit.entity.Player;

public class Party
{
	private Player owner;
	private List<Player> members;
	private int id;
	
	public Party(Player owner, List<Player> members)
	{
		this.owner = owner;
		this.members = members;
	}
	
	public Player getOwner()
	{
		return this.owner;
	}
	
	public void setOwner(Player owner)
	{
		this.owner = owner;
	}
	
	public List<Player> getMembers()
	{
		return this.members;
	}
	
	public int getId()
	{
		return this.id;
	}
}
