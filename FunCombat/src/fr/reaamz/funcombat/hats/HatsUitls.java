package fr.reaamz.funcombat.hats;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.reaamz.funcombat.chat.UnsafeText;

public class HatsUitls 
{
	public static ItemStack[] getItemForMenu()
	{
		ItemStack[] stack = new ItemStack[18];
		int i = 0;
		
		for (HatsType type : HatsType.values())
		{
			stack[i] = new ItemStack(type.getType());
			ItemMeta meta = stack[i].getItemMeta();
			
			meta.setDisplayName(ChatColor.GOLD + type.getCaption());
			meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour porter " + type.getText() + " !"));
			
			stack[i].setItemMeta(meta);
			++i;
		}
		
		ItemStack clear = new ItemStack(Material.THIN_GLASS);
		ItemMeta clearMeta = clear.getItemMeta();
		
		clearMeta.setDisplayName(UnsafeText.HATS_ITEM_ENLEVER);
		clearMeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour enlever votre chapeau"));
		
		clear.setItemMeta(clearMeta);
		
		stack[17] = clear;
		
		return stack;
	}
}
