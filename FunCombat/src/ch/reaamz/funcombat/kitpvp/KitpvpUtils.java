package ch.reaamz.funcombat.kitpvp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.reaamz.funcombat.FunCombat;
import ch.reaamz.funcombat.kitpvp.KitpvpKits.Kits;

public class KitpvpUtils 
{	
	public static ItemStack[] getItemsPourMenu()
	{
		ItemStack[] items = new ItemStack[9];
		
		int id = 0;
		for (Kits kit : Kits.values())
		{
			ItemStack item = new ItemStack(kit.getTypeInv());
			ItemMeta meta = item.getItemMeta();
			
			meta.setDisplayName(ChatColor.GOLD + kit.toString());
			
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "" + ChatColor.UNDERLINE + FunCombat.localizer.locate("funcombat.kit.contains"));
			lore.add("");
			for (String l : kit.getLores())
			{
				lore.add(ChatColor.WHITE + l);
			}
			
			meta.setLore(lore);
			
			meta.addEnchant(Enchantment.DURABILITY, 1, true);
			
			item.setItemMeta(meta);
			
			items[id] = item;
			
			id++;		
		}
		
		return items;		
	}
	
	public static int getLevelFromKills(int kitLevel)
	{	
		if (kitLevel < 4) return 1;
		else if (kitLevel > 4 && kitLevel <= 9) return 2;
		else if (kitLevel > 9 && kitLevel <= 19) return 3;
		else if (kitLevel > 19 && kitLevel <= 49) return 4;
		else return 5;
	}
	
	public static String getMaxKillsForNextLevel(int level)
	{
		if (level == 1)
			return "5";
		if (level == 2)
			return "10";
		if (level == 3)
			return "20";
		if (level == 4)
			return "50";
		
		return "Max level";
	}
	
	public static boolean estEntre1et4(int level)
	{
		return level >= 1 && level <= 4;
	}
	
	public static boolean estEntre4et9(int level)
	{
		return level >= 5 && level <= 9;
	}
	
	public static boolean estEntre9et19(int level)
	{
		return level >= 10 && level <= 19;
	}
	
	public static boolean estEntre19et49(int level)
	{
		return level >= 20 && level <= 49;
	}
	
	public static boolean estPlusDe50(int level)
	{
		return level >= 50;
	}
	
	public static String addLevelLore(int level)
	{
		return ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.level") + KitpvpUtils.getLevelFromKills(level);
	}
	
	public static String getMessageLevel(int level)
	{
		return ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.ylevel") + " " + FunCombat.localizer.locate("funcombat.kit.level").toLowerCase() + " " + KitpvpUtils.getLevelFromKills(level);
	}
}
