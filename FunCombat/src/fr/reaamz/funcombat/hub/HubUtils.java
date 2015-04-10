package fr.reaamz.funcombat.hub;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HubUtils 
{
	public static void equipHubStuff(Player player)
	{
		ItemStack compass = new ItemStack(Material.COMPASS,1);
		ItemMeta compassMeta = compass.getItemMeta();
		
		compassMeta.setDisplayName(ChatColor.GREEN + "Menu principal");
		
		compassMeta.setLore(Arrays.asList(ChatColor.GRAY + "Clic droit pour ouvrir"));
		
		compass.setItemMeta(compassMeta);
		
		//------
		
		ItemStack[] items = new ItemStack[player.getInventory().getSize()];
		
		items[0] = compass;
		
		player.getInventory().setContents(items);
	}
}
