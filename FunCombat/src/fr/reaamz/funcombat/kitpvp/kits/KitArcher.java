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

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.kitpvp.KitpvpUtils;

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
		
		if (KitpvpUtils.estEntre1et4(level))
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);				
			}
		}
		if (KitpvpUtils.estEntre4et9(level))
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,1);
		}
		if (KitpvpUtils.estEntre9et19(level))
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,2);
		}
		if (KitpvpUtils.estEntre19et49(level))
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,3);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,3);
		}
		if (KitpvpUtils.estPlusDe50(level))
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,4);
		}
		
		player.getInventory().setArmorContents(armor);
	}
	
	@Override
	public void equipStuff(Player player, int level)
	{
		ItemStack bow = new ItemStack(Material.BOW, 1);
		ItemMeta bowMeta = bow.getItemMeta();
		
		bowMeta.setDisplayName(FunCombat.localizer.locate("funcombat.kit.bowof") + " " + player.getName());
		
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.stuffarcher"));
		
		if (KitpvpUtils.estEntre1et4(level))
		{	
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		}
		if (KitpvpUtils.estEntre4et9(level))
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,1, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		}
		if (KitpvpUtils.estEntre9et19(level))
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,2, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		}
		if (KitpvpUtils.estEntre19et49(level))
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,3, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		}
		if (KitpvpUtils.estPlusDe50(level))
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,4, true);
			bowMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		}
		
		lore.add(KitpvpUtils.addLevelLore(level));
		Utils.sendCustomMessage(player, KitpvpUtils.getMessageLevel(level));
		player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 50, 1);
		
		bowMeta.setLore(lore);
		
		bow.setItemMeta(bowMeta);
	
		player.getInventory().addItem(bow);
		player.getInventory().addItem(new ItemStack(Material.ARROW,1));
		player.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT,64));
	}
}
