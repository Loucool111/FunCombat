package fr.reaamz.funcombat;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Utils 
{
	public static void sendCustomMessageAllPlayers(String message)
	{
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			p.sendMessage(ChatColor.GOLD + "[FunCombat] " + ChatColor.RESET + message);
		}
	}
	
	public static void sendMessageAllPlayers(String message)
	{
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			p.sendMessage(message);
		}
	}
	
	public static void sendCustomMessage(Player p , String message)
	{
		p.sendMessage(ChatColor.GOLD + "[FunCombat] " + ChatColor.RESET + message);
	}
	
	public static void logInfo(String info)
	{
		Bukkit.getServer().getLogger().info(info);
	}
	
	public static void ClearInventoryAndPotionEffects(Player player)
	{
		player.getInventory().clear();
		
		player.getInventory().setArmorContents(null);
		
		for (PotionEffect e : player.getActivePotionEffects())
		{
			if(player.hasPotionEffect(e.getType()))
			{
				player.removePotionEffect(e.getType());
			}
		}
	}
	
	public static void removeAllPotionEffects(Player player)
	{
		for (PotionEffect e : player.getActivePotionEffects())
		{
			if(player.hasPotionEffect(e.getType()))
			{
				player.removePotionEffect(e.getType());
			}
		}
	}
	
	public static UUID getUUID(String uuid)
    {
        return UUID.fromString( uuid.substring( 0, 8 ) + "-" + uuid.substring( 8, 12 ) + "-" + uuid.substring( 12, 16 ) + "-" + uuid.substring( 16, 20 ) + "-" + uuid.substring( 20, 32 ) );
    }
	
	public static byte getDataFromDyeColor(DyeColor color)
	{
		if (color == DyeColor.BLACK)
			return (byte) 0;
		if (color == DyeColor.RED)
			return (byte) 1;
		if (color == DyeColor.GREEN)
			return (byte) 2;
		if (color == DyeColor.BROWN)
			return (byte) 3;
		if (color == DyeColor.BLUE)
			return (byte) 4;
		if (color == DyeColor.PURPLE)
			return (byte) 5;
		if (color == DyeColor.CYAN)
			return (byte) 6;
		if (color == DyeColor.SILVER)
			return (byte) 7;
		if (color == DyeColor.GRAY)
			return (byte) 8;
		if (color == DyeColor.PINK)
			return (byte) 9;
		if (color == DyeColor.LIME)
			return (byte) 10;
		if (color == DyeColor.YELLOW)
			return (byte) 11;
		if (color == DyeColor.LIGHT_BLUE)
			return (byte) 12;
		if (color == DyeColor.MAGENTA)
			return (byte) 13;
		if (color == DyeColor.ORANGE)
			return (byte) 14;
		if (color == DyeColor.WHITE)
			return (byte) 15;
		
		return (byte) 0;
	}
}
