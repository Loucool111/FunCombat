package fr.reaamz.funcombat.mainmenu;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class MainMenuUtils 
{
	public static ItemStack[] getMainMenuItems() 
	{
		ItemStack kitpvp = new ItemStack(Material.DIAMOND_SWORD,1);
		ItemMeta kitpvpMeta = kitpvp.getItemMeta();
		
		kitpvpMeta.setDisplayName(ChatColor.GOLD + "Choissisez votre kit !");
		
		kitpvpMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		
		kitpvpMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir votre kit !"));
		
		kitpvp.setItemMeta(kitpvpMeta);
		
		//-----------------------------------
		
		ItemStack jump = new ItemStack(Material.FEATHER);
		ItemMeta jumpMeta = jump.getItemMeta();
		
		jumpMeta.setDisplayName(ChatColor.GOLD + "Essayez le jump !");
		jumpMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour tenter le jump !"));
		
		jump.setItemMeta(jumpMeta);
		
		//-----------------------------------
		
		ItemStack morph = new ItemStack(Material.BLAZE_ROD);
		ItemMeta metamorph = morph.getItemMeta();
			
		metamorph.setDisplayName(ChatColor.GOLD + "Changez de Métamorphose !");
		metamorph.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour changer de Métamorphose !"));
			
		morph.setItemMeta(metamorph);
		
		//-----------------------------------
		
		Dye die = new Dye();
		die.setColor(DyeColor.WHITE);
		ItemStack Couleurs = die.toItemStack(1);
		ItemMeta CouleursMeta = Couleurs.getItemMeta();
		
		CouleursMeta.setDisplayName(ChatColor.GOLD + "Choissisez votre couleur préférée !");
		CouleursMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour définir votre couleur préférée !",ChatColor.WHITE + "Nécessaire pour les montures à plusieurs couleurs")); 
		
		Couleurs.setItemMeta(CouleursMeta);
		
		//-----------------------------------
		
		ItemStack[] items = new ItemStack[27];
		
		items[0] = kitpvp;
		
		items[8] = jump;
		
		items[18] = morph;
		
		items[26] = Couleurs;
		
		return items;
	}
}
