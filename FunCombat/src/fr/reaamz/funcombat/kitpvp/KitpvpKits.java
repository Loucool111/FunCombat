package fr.reaamz.funcombat.kitpvp;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.kitpvp.kits.IKit;
import fr.reaamz.funcombat.kitpvp.kits.KitArcher;
import fr.reaamz.funcombat.kitpvp.kits.KitGuerrier;
import fr.reaamz.funcombat.kitpvp.kits.KitSorciere;

public class KitpvpKits 
{
	public enum Kits
	{
		ARCHER(FunCombat.localizer.locate("funcombat.kit.kitarcher"), Material.BOW, KitArcher.class),
		GUERRIER(FunCombat.localizer.locate("funcombat.kit.kitguerrier"), Material.DIAMOND_SWORD, KitGuerrier.class),
		SORCIERE(FunCombat.localizer.locate("funcombat.kit.kitsorciere"), Material.STICK, KitSorciere.class),
		;
		
		private String type;
		private Material itemInv;
		private Class<? extends IKit> clazz;
		
		Kits(String type, Material itemInv, Class<? extends IKit> clazz)
		{
			this.type = type;
			this.itemInv = itemInv;
			this.clazz = clazz;
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
		
		public Class<? extends IKit> getKitClass()
		{
			return this.clazz;
		}
	}
	
	public static void equipKitArmor(Player player, int level, Kits kit)
	{
//		if (kit.equals(Kits.ARCHER))
//		{
//			new KitArcher().equipArmor(player, level);
//		}
//		if (kit.equals(Kits.GUERRIER))
//		{
//			new KitGuerrier().equipArmor(player, level);
//		}
//		if (kit.equals(Kits.SORCIERE))
//		{
//			new KitSorciere().equipArmor(player, level);
//		}
		
		for (Kits kk : Kits.values())
		{
			if (kit.equals(kk))
			{
				IKit kt = null;
				try
				{
					kt = kk.getKitClass().newInstance();
				}
				catch (InstantiationException | IllegalAccessException e)
				{				
					e.printStackTrace();
				}
				kt.equipArmor(player, level);
			}
		}
	}
	
	public static void equipKitStuff(Player player, int level, Kits kit)
	{
//		if (kit.equals(Kits.ARCHER))
//		{
//			new KitArcher().equipStuff(player, level);
//		}
//		if (kit.equals(Kits.GUERRIER))
//		{
//			new KitGuerrier().equipStuff(player, level);
//		}
//		if (kit.equals(Kits.SORCIERE))
//		{
//			new KitSorciere().equipStuff(player, level);
//		}
		
		for (Kits kk : Kits.values())
		{
			if (kit.equals(kk))
			{
				IKit kt = null;
				try
				{
					kt = kk.getKitClass().newInstance();
				}
				catch (InstantiationException | IllegalAccessException e)
				{				
					e.printStackTrace();
				}
				kt.equipStuff(player, level);
			}
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
