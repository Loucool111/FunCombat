package fr.reaamz.funcombat.kitpvp.kits;

import java.util.ArrayList;
import java.util.List;

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
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.kitpvp.KitpvpUtils;

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
		
		if (KitpvpUtils.estEntre1et4(level))
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
			}
		}
		if (KitpvpUtils.estEntre4et9(level))
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 1);
		}
		if (KitpvpUtils.estEntre9et19(level))
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 2);
		}
		if (KitpvpUtils.estEntre19et49(level))
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 3);
		}
		if (KitpvpUtils.estPlusDe50(level))
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 4);
		}
		
		player.getInventory().setArmorContents(armor);
	}

	@Override
	public void equipStuff(Player player, int level)
	{
		ItemStack rod = new ItemStack(Material.STICK);
		ItemMeta rodMeta = rod.getItemMeta();
		rodMeta.setDisplayName(ChatColor.DARK_PURPLE + FunCombat.localizer.locate("funcombat.kit.witchstick"));
		List<String> lore = new ArrayList<String>();
		
		lore.add(ChatColor.WHITE + FunCombat.localizer.locate("funcombat.kit.rightclick"));
		lore.add(ChatColor.WHITE + FunCombat.localizer.locate("funcombat.kit.stuffsorciere"));
		
		Potion popop = new Potion(PotionType.INSTANT_HEAL, 1).splash();
		ItemStack popo = popop.toItemStack(1);
		
		if (KitpvpUtils.estEntre1et4(level))
		{
			rod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		}
		if (KitpvpUtils.estEntre4et9(level))
		{
			rod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);	
		}
		if (KitpvpUtils.estEntre9et19(level))
		{
			rod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		}
		if (KitpvpUtils.estEntre19et49(level))
		{
			rod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
		}
		if (KitpvpUtils.estPlusDe50(level))
		{
			rod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		}
		
		lore.add(KitpvpUtils.addLevelLore(level));
		Utils.sendCustomMessage(player, KitpvpUtils.getMessageLevel(level));
		player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		
		rodMeta.setLore(lore);
		rod.setItemMeta(rodMeta);
		
		player.getInventory().addItem(rod);
		player.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT, 64));
		
		for (int i = 0; i<= 5; i++)
		{
			player.getInventory().addItem(popo);
		}
		
		player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL,16));
	}
}
