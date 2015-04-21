package fr.reaamz.funcombat.hats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.virtualinventory.IVirtualInventory;

public class HatsMenuVirtualInventory implements IVirtualInventory 
{
	private Inventory inventory;
	
	@Override
	public Inventory getInventory()
	{
		return this.inventory;
	}

	@Override
	public Inventory createInventory(ItemStack[] items) 
	{
		this.inventory = Bukkit.createInventory(null, 27, Utils.InventoryNames.FC_HATS.getName());
		
		this.inventory.setContents(items);
		
		return this.inventory;
	}

	@Override
	public void showInventory(Player player, Inventory inventory) 
	{
		player.openInventory(this.inventory);
	}
}
