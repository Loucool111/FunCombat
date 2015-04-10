package fr.reaamz.funcombat.kitpvp;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.reaamz.funcombat.Utils;

public class KitpvpKits 
{
	public static void equipKitArcherArmor(Player player, int level)
	{
		ItemStack[] armor = new ItemStack[]{
				new ItemStack(Material.CHAINMAIL_BOOTS),
				new ItemStack(Material.CHAINMAIL_LEGGINGS),
				new ItemStack(Material.CHAINMAIL_CHESTPLATE),
				new ItemStack(Material.CHAINMAIL_HELMET)
		};
		
		if(level >= 1)
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 127);				
			}
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 5)
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,1);
						
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 10)
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,2);
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 20)
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,3);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,3);
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 50)
		{
			for(ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4);				
			}
			
			armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL,4);
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		
		player.getInventory().setArmorContents(armor);
	}
	
	public static void equipKitArcherStuff(Player player, int level)
	{
		ItemStack bow = new ItemStack(Material.BOW, 1);
		ItemMeta bowMeta = bow.getItemMeta();
		
		bowMeta.setDisplayName("Arc de " + player.getName());
		
		ArrayList<String> Bowlore = new ArrayList<String>();
		Bowlore.add(ChatColor.RESET + "Stuff du kit Archer");
		
		if (level >= 1 && level <= 4)
		{	
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			
			Bowlore.add(ChatColor.RESET + "Niv.1");
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + "Vous passez niveau 1");
		}
		if (level >= 5 && level <= 9)
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,1, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			
			Bowlore.add(ChatColor.RESET + "Niv.2");
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + "Vous passez niveau 2");
		}
		if (level >= 10 && level <= 19)
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,2, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		
			Bowlore.add(ChatColor.RESET + "Niv.3");
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + "Vous passez niveau 3");
		}
		if (level >= 20 && level <= 49)
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,3, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			
			Bowlore.add(ChatColor.RESET + "Niv.4");
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + "Vous passez niveau 4");
		}
		if (level >= 50)
		{
			bowMeta.addEnchant(Enchantment.ARROW_DAMAGE,4, true);
			bowMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			
			Bowlore.add(ChatColor.RESET + "Niv.5");
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + "Vous passez niveau 5");
		}
		
		
		bowMeta.setLore(Bowlore);
		
		bow.setItemMeta(bowMeta);
	
		player.getInventory().addItem(bow);
		player.getInventory().addItem(new ItemStack(Material.ARROW,1));
		player.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT,64));
	}
	
	public static void equipKitGuerrierArmure(Player player, int level)
	{
		ItemStack[] armor = new ItemStack[]{
				new ItemStack(Material.IRON_BOOTS),
				new ItemStack(Material.IRON_LEGGINGS),
				new ItemStack(Material.IRON_CHESTPLATE),
				new ItemStack(Material.IRON_HELMET)
		};
		
		if (level >= 1)
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
			}
			
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 5)
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 1);
			
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 10)
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 2);
			
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 20)
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 3);
			
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		if (level >= 50)
		{
			for (ItemStack arm : armor)
			{
				arm.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
				arm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			}
			
			armor[0].addEnchantment(Enchantment.PROTECTION_FALL, 4);
			
			
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 1);
		}
		
		player.getInventory().setArmorContents(armor);
	}
	
	public static void equipKitGuerrierStuff(Player player, int level)
	{
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD,1);
		ItemMeta swordMeta = sword.getItemMeta();
		
		swordMeta.setDisplayName("Ep�e de " + player.getName());
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RESET + "Stuff du kit guerrier");
		
		if (level >= 1 && level <= 4)
		{
			lore.add(ChatColor.RESET + "Niv.1");
			
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + "Vous passez niveau 1");
		}
		if (level >= 5 && level <= 9)
		{
			lore.add(ChatColor.RESET + "Niv.2");
			
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + "Vous passez niveau 2");
		}
		if (level >= 10 && level <= 19)
		{
			lore.add(ChatColor.RESET + "Niv.3");
			
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + "Vous passez niveau 3");
		}
		if (level >= 20 && level <= 49)
		{
			lore.add(ChatColor.RESET + "Niv.4");
			
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + "Vous passez niveau 4");
		}
		if (level >= 50)
		{
			lore.add(ChatColor.RESET + "Niv.5");
			
			swordMeta.addEnchant(Enchantment.DURABILITY, 127, true);
			swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
			swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
			
			Utils.sendCustomMessage(player,ChatColor.GREEN + "Vous passez niveau 5");
		}
		
		swordMeta.setLore(lore);
		
		sword.setItemMeta(swordMeta);
		player.getInventory().addItem(sword);
		player.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT,64));
	}
	
	public static void equipKitAdmArmure(Player player)
	{
		ItemStack[] armor = new ItemStack[]{
				new ItemStack(Material.DIAMOND_BOOTS),
				new ItemStack(Material.DIAMOND_LEGGINGS),
				new ItemStack(Material.DIAMOND_CHESTPLATE),
				new ItemStack(Material.DIAMOND_HELMET)
		};
			
		for(ItemStack armure: armor)
		{
			armure.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 32767);
			armure.addUnsafeEnchantment(Enchantment.DURABILITY, 32767);
		}
		
		armor[0].addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 32767);
	
		player.getInventory().setArmorContents(armor);
	}
	
	public static void equipKitAdmStuff(Player player)
	{
		PotionEffect SpeedEffect = new PotionEffect(PotionEffectType.SPEED,10000000,1);
		
		player.addPotionEffect(SpeedEffect);
		
		ItemStack sword = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta swordMeta = sword.getItemMeta();
	
		swordMeta.setDisplayName("Destructeur de cheateurs");
	
		swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 32767,true);
		swordMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 32767,true);
		swordMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 32767,true);
		swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 10,true);
		swordMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 127,true);
	
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Morts au cheaters!");					
		swordMeta.setLore(lore);
	
		sword.setItemMeta(swordMeta);
		player.getInventory().addItem(sword);
	}
}