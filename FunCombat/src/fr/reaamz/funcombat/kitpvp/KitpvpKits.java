package fr.reaamz.funcombat.kitpvp;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import fr.reaamz.funcombat.Utils;

public class KitpvpKits 
{
	public enum Kits
	{
		ARCHER("Archer", Material.BOW),
		GUERRIER("Guerrier", Material.DIAMOND_SWORD),
		SORCIERE("Sorcière", Material.STICK),
		;
		
		private String type;
		private Material itemInv;
		
		Kits(String type, Material itemInv)
		{
			this.type = type;
			this.itemInv = itemInv;
		}
		
		public String toStringKit()
		{
			return this.type;
		}
		
		@Override
		public String toString()
		{
			return this.type;
		}
		
		public Material getTypeInv()
		{
			return this.itemInv;
		}
	}
	
	public static void equipKitArmor(Player player, int level, Kits kit)
	{
		if (kit.equals(Kits.ARCHER))
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
		if (kit.equals(Kits.GUERRIER))
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
		if (kit.equals(Kits.SORCIERE))
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
	}
	
	public static void equipKitStuff(Player player, int level, Kits kit)
	{
		if (kit.equals(Kits.ARCHER))
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
		if (kit.equals(Kits.GUERRIER))
		{
			ItemStack sword = new ItemStack(Material.DIAMOND_SWORD,1);
			ItemMeta swordMeta = sword.getItemMeta();
			
			swordMeta.setDisplayName("Epée de " + player.getName());
			
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
		if (kit.equals(Kits.SORCIERE))
		{
			ItemStack rod = new ItemStack(Material.STICK);
			ItemMeta rodMeta = rod.getItemMeta();
			rodMeta.setDisplayName(ChatColor.DARK_PURPLE + "Bâton de sorcière");
			rodMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic droit pour activer"));
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
