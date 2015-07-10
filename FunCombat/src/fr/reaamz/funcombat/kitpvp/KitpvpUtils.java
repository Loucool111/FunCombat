package fr.reaamz.funcombat.kitpvp;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.reaamz.funcombat.FunCombat;

public class KitpvpUtils 
{	
	public static ItemStack[] getItemsPourMenu()
	{
		//-------------ARCHER-------------
		
		ItemStack kitArcher = new ItemStack(Material.BOW,1);
		ItemMeta arcMeta = kitArcher.getItemMeta();
	
		arcMeta.setDisplayName(ChatColor.GOLD + FunCombat.localizer.locate("funcombat.kit.kitarcher"));
	
		arcMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		arcMeta.setLore(Arrays.asList(ChatColor.WHITE +""+ChatColor.UNDERLINE + FunCombat.localizer.locate("funcombat.kit.contains"),"",ChatColor.WHITE + FunCombat.localizer.locate("funcombat.kit.archer1"),ChatColor.WHITE + FunCombat.localizer.locate("funcombat.kit.archer2")));
	
		kitArcher.setItemMeta(arcMeta);
		
		//--------------GUERRIER-----------
	
		ItemStack kitGuerrier = new ItemStack(Material.DIAMOND_SWORD,1);
		ItemMeta swordMeta = kitGuerrier.getItemMeta();
	
		swordMeta.setDisplayName(ChatColor.GOLD + FunCombat.localizer.locate("funcombat.kit.kitguerrier"));
		
		swordMeta.addEnchant(Enchantment.DURABILITY, 1, true);
	
		swordMeta.setLore(Arrays.asList(ChatColor.WHITE +""+ ChatColor.UNDERLINE + FunCombat.localizer.locate("funcombat.kit.contains"),"",ChatColor.WHITE + FunCombat.localizer.locate("funcombat.kit.guerrier1"),ChatColor.WHITE + FunCombat.localizer.locate("funcombat.kit.guerrier2")));
	
		kitGuerrier.setItemMeta(swordMeta);
		//-------------SORCIERE--------------
		
		ItemStack kitSorciere = new ItemStack(Material.STICK);
		ItemMeta stickMeta = kitSorciere.getItemMeta();
		
		stickMeta.setDisplayName(ChatColor.GOLD + FunCombat.localizer.locate("funcombat.kit.kitsorciere"));
		
		stickMeta.setLore(Arrays.asList(ChatColor.WHITE + "" + ChatColor.UNDERLINE + FunCombat.localizer.locate("funcombat.kit.contains"),"",ChatColor.WHITE + FunCombat.localizer.locate("funcombat.kit.sorciere1"),ChatColor.WHITE + FunCombat.localizer.locate("funcombat.kit.sorciere2"), ChatColor.WHITE + FunCombat.localizer.locate("funcombat.kit.sorciere3")));
		
		stickMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		kitSorciere.setItemMeta(stickMeta);
		
		//-----------------------------------
		return new ItemStack[]{
				kitArcher,
				kitGuerrier,
				kitSorciere
		};
	}
	
	public static int getLevelFromKills(Player player, int kitLevel)
	{
		switch (kitLevel) {
		case 0:
		case 1: 
		case 2:
		case 3:
		case 4: return 1;
		case 5:
		case 6:
		case 7:
		case 8:
		case 9: return 2;
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19: return 3;
		case 20:
		case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
		case 29:
		case 30:
		case 31:
		case 32:
		case 33:
		case 34:
		case 35:
		case 36:
		case 37:
		case 38:
		case 39:
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
		case 45:
		case 46:
		case 47:
		case 48:
		case 49: return 4;
		case 50: return 5;

		default:
			return 5;
		}
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
}
