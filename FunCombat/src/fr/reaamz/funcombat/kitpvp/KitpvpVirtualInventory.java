package fr.reaamz.funcombat.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.reaamz.funcombat.virtualinventory.IVirtualInventory;

public class KitpvpVirtualInventory implements IVirtualInventory
{	
	Inventory inventory;
	
	@Override
	public Inventory getInventory() 
	{
		return this.inventory;
	}

	@Override
	public Inventory createInventory(ItemStack[] items) 
	{
		this.inventory = Bukkit.createInventory(null, 9, ChatColor.UNDERLINE + "S�l�cteur de kit");
		
		this.inventory.setContents(items);
		
		return this.inventory;
	}

	@Override
	public void showInventory(Player player, Inventory inventory)
	{
		player.openInventory(inventory);
	}
}