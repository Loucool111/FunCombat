package ch.reaamz.funcombat.virtualinventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GenericVirtualInventory
{
	private String name;
	private int slots;
	private ItemStack[] items;
	
	public GenericVirtualInventory(String name, int slots, ItemStack[] items)
	{
		this.name = name;
		this.slots = slots;
		this.items = items;
	}
	
	public Inventory getInventory()
	{
		Inventory inv = Bukkit.createInventory(null, slots, name);
		
		inv.setContents(items);
		
		return inv;
	}
}
