package fr.reaamz.funcombat.jump;

import java.io.File;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.reaamz.funcombat.FunCombat;

public class JumpUtils
{
	public static final File scoreFile = new File(FunCombat.instance.getDataFolder() + "\\JumpScore\\JumpScore.txt");
	
	public static ItemStack[] getMenuItems()
	{
		//3 paper et un book
		ItemStack[] items = new ItemStack[27];
		
		ItemStack jump1 = new ItemStack(Material.FEATHER);
		ItemMeta jump1meta = jump1.getItemMeta();
		
		jump1meta.setDisplayName(ChatColor.GREEN + "Jump #1");
		jump1meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour essayer le Jump #1"));
		
		jump1.setItemMeta(jump1meta);
		
		ItemStack jump2 = new ItemStack(Material.FEATHER);
		ItemMeta jump2meta = jump2.getItemMeta();
		
		jump2meta.setDisplayName(ChatColor.GREEN + "Jump #2");
		jump2meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour essayer le Jump #2"));
		
		jump2.setItemMeta(jump2meta);
		
		ItemStack jump3 = new ItemStack(Material.FEATHER);
		ItemMeta jump3meta = jump3.getItemMeta();
		
		jump3meta.setDisplayName(ChatColor.GREEN + "Jump #3");
		jump3meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour essayer le Jump #3"));
		
		jump3.setItemMeta(jump3meta);
		
		ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
		ItemMeta bookmeta = book.getItemMeta();
		
		bookmeta.setDisplayName(ChatColor.GREEN + "Scores des Jumps");
		bookmeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour accéder au Scores"));
		
		book.setItemMeta(bookmeta);
		
		items[3] = jump1;
		items[4] = jump2;
		items[5] = jump3;
		items[22] = book;
		
		return items;
	}
}
