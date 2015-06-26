package fr.reaamz.funcombat.jump.score;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JumpScore
{
	public static ItemStack[] getScore()
	{
		ItemStack[] items = new ItemStack[18];
		//-----------------------------------
		
		ItemStack score1time = new ItemStack(Material.WRITTEN_BOOK);
		ItemMeta score1timemeta = score1time.getItemMeta();
		
		score1timemeta.setDisplayName(ChatColor.YELLOW + "Temps du Jump #1");
		score1timemeta.setLore(getTime("jump1"));
		
		score1time.setItemMeta(score1timemeta);
		
		ItemStack score1tent = new ItemStack(Material.WRITTEN_BOOK);
		ItemMeta score1tentmeta = score1tent.getItemMeta();
		
		score1tentmeta.setDisplayName(ChatColor.YELLOW + "Tentatives du Jump #1");
		score1tentmeta.setLore(getTent("jump1"));
		
		score1tent.setItemMeta(score1tentmeta);
		
		//-----------------------------------
		
		ItemStack score2time = new ItemStack(Material.WRITTEN_BOOK);
		ItemMeta score2timemeta = score2time.getItemMeta();
		
		score2timemeta.setDisplayName(ChatColor.YELLOW + "Temps du Jump #2");
		score2timemeta.setLore(Arrays.asList(ChatColor.WHITE + ""));
		
		score2time.setItemMeta(score2timemeta);
		
		ItemStack score2tent = new ItemStack(Material.WRITTEN_BOOK);
		ItemMeta score2tentmeta = score2time.getItemMeta();
		
		score2tentmeta.setDisplayName(ChatColor.YELLOW + "Tentatives du Jump #2");
		score2tentmeta.setLore(Arrays.asList(ChatColor.WHITE + ""));
		
		score2tent.setItemMeta(score2tentmeta);
		
		//-----------------------------------
		
		ItemStack score3time = new ItemStack(Material.WRITTEN_BOOK);
		ItemMeta score3timemeta = score3time.getItemMeta();
		
		score3timemeta.setDisplayName(ChatColor.YELLOW + "Temps du Jump #3");
		score3timemeta.setLore(Arrays.asList(ChatColor.WHITE + ""));
		
		score3time.setItemMeta(score3timemeta);
		
		ItemStack score3tent = new ItemStack(Material.WRITTEN_BOOK);
		ItemMeta score3tentmeta = score3time.getItemMeta();
		
		score3tentmeta.setDisplayName(ChatColor.YELLOW + "Tentatives du Jump #3");
		score3tentmeta.setLore(Arrays.asList(ChatColor.WHITE + ""));
		
		score3tent.setItemMeta(score3tentmeta);
		
		//-----------------------------------
		
		items[2] = score1time;
		items[11] = score1tent;
		items[4] = score2time;
		items[13] = score2tent;
		items[6] = score3time;
		items[15] = score3tent;
		
		return items;
	}
	
	private static List<String> getTime(String jumpname)
	{
		//get info from database
				
		return Arrays.asList(ChatColor.WHITE + jumpname);
		
	}
	
	private static List<String> getTent(String jumpname)
	{
		//get info from database
		
		return Arrays.asList(ChatColor.WHITE + jumpname);
	}
}