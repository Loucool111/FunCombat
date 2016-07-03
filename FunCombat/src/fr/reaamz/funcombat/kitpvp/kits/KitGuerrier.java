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
		
		if (KitpvpUtils.estEntre1et4(level))
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
			}
			
			player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 50, 1);
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
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD,1);
		ItemMeta swordMeta = sword.getItemMeta();
		
		swordMeta.setDisplayName(FunCombat.localizer.locate("funcombat.kit.swordof") + " " + player.getName());
		
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RESET + FunCombat.localizer.locate("funcombat.kit.stuffguerrier"));
		
		if (KitpvpUtils.estEntre1et4(level))
		{
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
		}
		if (KitpvpUtils.estEntre4et9(level))
		{
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);	
		}
		if (KitpvpUtils.estEntre9et19(level))
		{
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		}
		if (KitpvpUtils.estEntre19et49(level))
		{
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		}
		if (KitpvpUtils.estPlusDe50(level))
		{
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
			swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
		}
		
		lore.add(KitpvpUtils.addLevelLore(level));
		Utils.sendCustomMessage(player, KitpvpUtils.getMessageLevel(level));
		player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 50, 1);
		
		swordMeta.setLore(lore);
		
		sword.setItemMeta(swordMeta);
		player.getInventory().addItem(sword);
		player.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT,64));
	}
}
