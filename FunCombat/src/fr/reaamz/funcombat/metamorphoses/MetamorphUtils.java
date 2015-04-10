package fr.reaamz.funcombat.metamorphoses;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MetamorphUtils 
{
	public static ItemStack[] getMetaMorphMenuItems()
	{
		ItemStack mouton = new ItemStack(Material.WOOL);
		ItemMeta moutonMeta = mouton.getItemMeta();
		
		moutonMeta.setDisplayName(ChatColor.GOLD + "Métamorphosez-vous en mouton !");
		moutonMeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour vous transformer en mouton !",ChatColor.WHITE + "Couleur selon couleur préférée"));
		
		mouton.setItemMeta(moutonMeta);
		
		//-----------------------------
		
		ItemStack enlever = new ItemStack(Material.THIN_GLASS);
		ItemMeta enleverMeta = enlever.getItemMeta();
		
		enleverMeta.setDisplayName(ChatColor.RED + "Enlever votre Métamorphose");
		enleverMeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour enlever votre Métamorphose !"));
		
		enlever.setItemMeta(enleverMeta);
		
		//-----------------------------
		
		ItemStack cow = new ItemStack(Material.LEATHER);
		ItemMeta cowMeta = cow.getItemMeta();
				
		cowMeta.setDisplayName(ChatColor.GOLD + "Métamorphosez-vous en vache !");
		cowMeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour vous transformer en vache !"));
		
		cow.setItemMeta(cowMeta);
				
		//-----------------------------
		ItemStack creeper = new ItemStack(Material.getMaterial("SULPHUR"));
		ItemMeta creeperMeta = creeper.getItemMeta();
		
		creeperMeta.setDisplayName(ChatColor.GOLD + "Métamorphosez-vous en creeper !");
		creeperMeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour vous transformer en creeper!"));
		
		creeper.setItemMeta(creeperMeta);
		
		
		//-----------------------------
		
		ItemStack giant = new ItemStack(Material.ROTTEN_FLESH);
		ItemMeta giantMeta = giant.getItemMeta();
		
		giantMeta.setDisplayName(ChatColor.GOLD + "Métamorphosez-vous en géant !");
		giantMeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour vous transformer en géant !"));
		
		giant.setItemMeta(giantMeta);
		
		//-----------------------------
		
		ItemStack poulet = new ItemStack(Material.FEATHER);
		ItemMeta pouletMeta = poulet.getItemMeta();
		
		pouletMeta.setDisplayName(ChatColor.GOLD + "Métamorphosez-vous en poulet !");
		pouletMeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour vous transformer en poulet !"));		
		poulet.setItemMeta(pouletMeta);
		
		//-----------------------------
		ItemStack horse = new ItemStack(Material.SADDLE);
		ItemMeta horseMeta = horse.getItemMeta();
		
		horseMeta.setDisplayName(ChatColor.GOLD + "Métamorphosez-vous en cheval-squelette !");
		horseMeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour vous transformer en cheval-squelette !"));		
		horse.setItemMeta(horseMeta);
		//-----------------------------
		
		ItemStack[] items = new ItemStack[18];
		
		items[0] = mouton;
		items[1] = cow;
		items[2] = creeper;
		items[3] = giant;
		items[4] = poulet;
		items[5] = horse;
		
		items[17] = enlever;
		
		return items;
	}
}
