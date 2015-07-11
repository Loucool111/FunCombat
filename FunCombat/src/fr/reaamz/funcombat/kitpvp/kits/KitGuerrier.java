package fr.reaamz.funcombat.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;

public class KitGuerrier implements IKit
{
	@Override
	public void equipArmor(Player player, int level)
	{
		ItemStack[] armor = new ItemStack[]{
				new ItemStack(Material.IRON_BOOTS),
				new ItemStack(Material.IRON_LEGGINGS),
				new ItemStack(Material.IRON_CHESTPLATE),
				new ItemStack(Material.IRON_HELMET)
		};
		
		if (level >= 1 && level <= 4)
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
			}
			
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 5 && level <= 9)
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 1);
			
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 10 && level <= 19)
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 2);
			
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 20 && level <= 49)
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 3);
			
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 50)
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 4);
			
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		
		player.getInventory().setArmorContents(armor);
	}

	@Override
	public void equipStuff(Player player, int level)
	{
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD,1);
		ItemMeta swordMeta = sword.getItemMeta();
		
		swordMeta.setDisplayName(FunCombat.localizer.locate("funcombat.kit.swordof") + " " + player.getName());
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.stuffguerrier"));
		
		if (level >= 1 && level <= 4)
		{
			lore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.level1"));
			
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.ylevel1"));
		}
		if (level >= 5 && level <= 9)
		{
			lore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.level2"));
			
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.ylevel2"));
		}
		if (level >= 10 && level <= 19)
		{
			lore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.level3"));
			
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.ylevel3"));
		}
		if (level >= 20 && level <= 49)
		{
			lore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.level4"));
			
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.ylevel4"));
		}
		if (level >= 50)
		{
			lore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.level5"));
			
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
			swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.ylevel5"));
		}
		
		swordMeta.setLore(lore);
		
		sword.setItemMeta(swordMeta);
		player.getInventory().addItem(sword);
		player.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT,64));
	}
}
