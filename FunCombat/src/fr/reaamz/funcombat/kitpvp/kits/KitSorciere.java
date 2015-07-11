package fr.reaamz.funcombat.kitpvp.kits;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import fr.reaamz.funcombat.FunCombat;

public class KitSorciere implements IKit
{
	@Override
	public void equipArmor(Player player, int level)
	{
		ItemStack[] armor = new ItemStack[]{
				new ItemStack(Material.DIAMOND_BOOTS),
				new ItemStack(Material.LEATHER_LEGGINGS),
				new ItemStack(Material.LEATHER_CHESTPLATE),
				new ItemStack(Material.CHAINMAIL_HELMET)
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
		ItemStack rod = new ItemStack(Material.STICK);
		ItemMeta rodMeta = rod.getItemMeta();
		rodMeta.setDisplayName(ChatColor.DARK_PURPLE + FunCombat.localizer.locate("funcombat.kit.witchstick"));
		rodMeta.setLore(Arrays.asList(ChatColor.WHITE + FunCombat.localizer.locate("funcombat.kit.rightclick")));
		rod.setItemMeta(rodMeta);
		
		Potion popop = new Potion(PotionType.INSTANT_HEAL, 1).splash();
		ItemStack popo = popop.toItemStack(1);
		
		if (level >= 1 && level <= 4)
		{
			rod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		}
		if (level >= 5 && level <= 9)
		{
			rod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);	
		}
		if (level >= 10 && level <= 19)
		{
			rod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		}
		if (level >= 20 && level <= 49)
		{
			rod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
		}
		if (level >= 50)
		{
			rod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		}
		
		player.getInventory().addItem(rod);
		player.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT, 64));
		
		for (int i = 0; i<= 5; i++)
		{
			player.getInventory().addItem(popo);
		}
		player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL,16));
	}
}
