package fr.reaamz.funcombat.hats;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Banner;

import fr.reaamz.funcombat.chat.UnsafeText;

public class HatsUitls 
{
	@SuppressWarnings("deprecation")
	public static ItemStack[] getItemForMenu()
	{
		ItemStack[] stack = new ItemStack[27];
		int i = 0;
		
		for (HatsType type : HatsType.values())
		{
			stack[i] = new ItemStack(type.getType());
			ItemMeta meta = stack[i].getItemMeta();
			
			meta.setDisplayName(ChatColor.GOLD + type.getCaption());
			meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour porter " + type.getText() + " !"));
			if (type == HatsType.BANNER)
			{
				meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour porter " + type.getText() + " !",ChatColor.WHITE + "Couleur selon couleur préférée"));
				
				Banner banner = new Banner(Material.BANNER);
				banner.setData(DyeColor.WHITE.getData());
				stack[i] = banner.toItemStack(1);
			}
			if (type == HatsType.WOOL)
			{
				meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour porter " + type.getText() + " !",ChatColor.WHITE + "Couleur selon couleur préférée"));
			}
			if (type == HatsType.GLASS)
			{
				meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour porter " + type.getText() + " !",ChatColor.WHITE + "Couleur selon couleur préférée"));
			}
			
			stack[i].setItemMeta(meta);
			++i;
		}
		
		ItemStack clear = new ItemStack(Material.THIN_GLASS);
		ItemMeta clearMeta = clear.getItemMeta();
		
		clearMeta.setDisplayName(UnsafeText.HATS_ITEM_ENLEVER);
		clearMeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour enlever votre chapeau"));
		
		clear.setItemMeta(clearMeta);
		
		stack[26] = clear;
		
		return stack;
	}
}
