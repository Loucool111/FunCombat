package ch.reaamz.funcombat.metamorphoses;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.reaamz.funcombat.chat.UnsafeText;

public class MetamorphUtils 
{	
	public static ItemStack[] getItemsForMenu()
	{
		ItemStack[] items = new ItemStack[18];
		
		//----------
		ItemStack enlever = new ItemStack(Material.THIN_GLASS);
		ItemMeta enleverMeta = enlever.getItemMeta();
		
		enleverMeta.setDisplayName(UnsafeText.METAMORPH_ITEM_ENLEVER);
		enleverMeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour enlever votre Métamorphose !"));
		
		enlever.setItemMeta(enleverMeta);
		//----------
		
		
		int i = 0;
		
		for (MetamorphType type : MetamorphType.values())
		{
			ItemStack stack = new ItemStack(type.getItemMaterial());
			ItemMeta meta = stack.getItemMeta();
			
			meta.setDisplayName(ChatColor.GOLD + "Métamorphosez-vous en " + type.getText() + " !");
			meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour vous transformer en " + type.getText() + " !"));	
			
			stack.setItemMeta(meta);
			
			items[i] = stack;
			
			++i;
		}
		
		items[items.length - 1] = enlever;
		
		return items;
	}
}
