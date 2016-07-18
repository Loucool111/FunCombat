package ch.reaamz.funcombat.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GenericContainer
{
	private String name;
	private int slots;
	private ItemStack[] items;
	
	public GenericContainer(String name, int slots, ItemStack[] items)
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
