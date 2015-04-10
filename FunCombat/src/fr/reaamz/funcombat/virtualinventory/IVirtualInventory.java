package fr.reaamz.funcombat.virtualinventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface IVirtualInventory 
{
	/** 
	 * @return the inventory
	 */
	Inventory getInventory();
	
	/**
	 * @param items Les items présents dans l'inventory
	 * @return L'inventory créé
	 */
	Inventory createInventory(ItemStack[] items);
	/**
	 * @param player le player qui va ouvrir l'inventory
	 * @param inventory L'inventory
	 */
	void showInventory(Player player, Inventory inventory);
}
