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
		ARCHER(FunCombat.localizer.locate("funcombat.kit.kitarcher"), KitArcher.class, Material.BOW, FunCombat.localizer.locate("funcombat.kit.archer1"), FunCombat.localizer.locate("funcombat.kit.archer2")),
		GUERRIER(FunCombat.localizer.locate("funcombat.kit.kitguerrier"), KitGuerrier.class, Material.DIAMOND_SWORD, FunCombat.localizer.locate("funcombat.kit.guerrier1"), FunCombat.localizer.locate("funcombat.kit.guerrier2")),
		SORCIERE(FunCombat.localizer.locate("funcombat.kit.kitsorciere"), KitSorciere.class, Material.STICK, FunCombat.localizer.locate("funcombat.kit.sorciere1"), FunCombat.localizer.locate("funcombat.kit.sorciere2"), FunCombat.localizer.locate("funcombat.kit.sorciere3")),
		;
		
		private String type;
		private Material itemInv;
		private Class<? extends IKit> clazz;
		private String[] lores;
		
		Kits(String type, Class<? extends IKit> clazz, Material itemInv, String ... lores)
		{	
			this.type = type;
			this.clazz = clazz;
			this.itemInv = itemInv;			
			this.lores = lores;
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
		
		public String[] getLores()
		{
			return this.lores;
		}
	}
	
	public static void equipKitArmor(Player player, int level, Kits kit)
	{
		for (Kits kk : Kits.values())
		{
			if (kit.equals(kk))
			{				
				try
				{
					kk.getKitClass().newInstance().equipArmor(player, level);
				}
				catch (InstantiationException | IllegalAccessException e)
				{				
					e.printStackTrace();
				}				
			}
		}
	}
	
	public static void equipKitStuff(Player player, int level, Kits kit)
	{	
		for (Kits kk : Kits.values())
		{
			if (kit.equals(kk))
			{
				try
				{
					kk.getKitClass().newInstance().equipStuff(player, level);
				}
				catch (InstantiationException | IllegalAccessException e)
				{				
					e.printStackTrace();
				}				
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
