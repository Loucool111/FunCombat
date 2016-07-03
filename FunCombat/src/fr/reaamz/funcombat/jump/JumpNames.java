package fr.reaamz.funcombat.jump;

import org.bukkit.ChatColor;

public enum JumpNames
{
	JUMP1("jump1", ChatColor.GREEN + "Jump #1"),
	JUMP2("jump2", ChatColor.GREEN + "Jump #2"),
	JUMP3("jump3", ChatColor.GREEN + "Jump #3"),
	;
	
	private String name;
	private String itemName;
	
	private JumpNames(String name, String itemName)
	{
		this.name = name;
		this.itemName = itemName;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getItemName()
	{
		return this.itemName;
	}
}
