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

public class KitArcher implements IKit
{
	@Override
	public void equipArmor(Player player, int level)
	{
		ItemStack[] armor = new ItemStack[]{
				new ItemStack(Material.CHAINMAIL_BOOTS),
				new ItemStack(Material.CHAINMAIL_LEGGINGS),
				new ItemStack(Material.CHAINMAIL_CHESTPLATE),
				new ItemStack(Material.CHAINMAIL_HELMET)
		};
		
		if (level >= 1 && level <= 4)
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);				
			}
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 5 && level <= 9)
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,1);
						
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 10 && level <= 19)
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,2);
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 20 && level <= 49)
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,3);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,3);
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 50)
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,4);
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		
		player.getInventory().setArmorContents(armor);
	}
	
	@Override
	public void equipStuff(Player player, int level)
	{
		ItemStack bow = new ItemStack(Material.BOW, 1);
		ItemMeta bowMeta = bow.getItemMeta();
		
		bowMeta.setDisplayName(FunCombat.localizer.locate("funcombat.kit.bowof") + " " + player.getName());
		
		ArrayList<String> Bowlore = new ArrayList<String>();
		Bowlore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.stuffarcher"));
		
		if (level >= 1 && level <= 4)
		{	
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			
			Bowlore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.level1"));
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.ylevel1"));
		}
		if (level >= 5 && level <= 9)
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,1, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			
			Bowlore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.level2"));
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.ylevel2"));
		}
		if (level >= 10 && level <= 19)
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,2, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		
			Bowlore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.level3"));
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.ylevel3"));
		}
		if (level >= 20 && level <= 49)
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,3, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			
			Bowlore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.level4"));
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.ylevel4"));
		}
		if (level >= 50)
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,4, true);
			bowMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			
			Bowlore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.level5"));
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.ylevel5"));
		}
		
		
		bowMeta.setLore(Bowlore);
		
		bow.setItemMeta(bowMeta);
	
		player.getInventory().addItem(bow);
		player.getInventory().addItem(new ItemStack(Material.ARROW,1));
		player.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT,64));
	}
}
