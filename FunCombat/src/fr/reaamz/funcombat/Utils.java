package fr.reaamz.funcombat;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

public class Utils 
{
	public static final String PLUGIN_NAME = "FunCombat";
	
	public enum InventoryNames
	{
		MC_CREATIVE("container.inventory"),
		MC_SURVIVAL("container.crafting"),
		
		FC_MAINMENU(ChatColor.UNDERLINE + FunCombat.localizer.locate("funcombat.inv.mainmenu")),
		FC_KITPVP(ChatColor.UNDERLINE + FunCombat.localizer.locate("funcombat.inv.kitpvp")),
		FC_METAMORPH(ChatColor.UNDERLINE + FunCombat.localizer.locate("funcombat.inv.metamorph")),
		FC_COLORS(ChatColor.UNDERLINE + FunCombat.localizer.locate("funcombat.inv.colors")),
		FC_HATS(ChatColor.UNDERLINE + FunCombat.localizer.locate("funcombat.inv.hats")),
		FC_JUMP(ChatColor.UNDERLINE + FunCombat.localizer.locate("funcombat.inv.jump")),
		FC_JUMPSCORES(ChatColor.UNDERLINE + FunCombat.localizer.locate("funcombat.inv.jumpscores")),
		;
		
		private String name;
		
		private InventoryNames(String name)
		{
			this.name = name;
		}

		public String getName()
		{
			return this.name;
		}
	}
	
	public static void sendCustomMessageAllPlayers(String message)
	{
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			p.sendMessage(ChatColor.GOLD + "[" + PLUGIN_NAME + "] " + ChatColor.RESET + message);
		}
	}
	
	public static void sendMessageAllPlayers(String message)
	{
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			p.sendMessage(message);
		}
	}
	
	public static void sendCustomMessage(Player player, String message)
	{
		player.sendMessage(ChatColor.GOLD + "[" + PLUGIN_NAME + "] " + ChatColor.RESET + message);
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
	
	public static DyeColor refreshColor(Player player)
	{		
		try
		{
			return FunCombat.database.getColor(player.getUniqueId());
		}
		catch (SQLException e)
		{
			return null;
		}
	}
	
	public static Player getTargetPlayer(Player player)
	{
		return getTarget(player, player.getWorld().getPlayers());
	}
	
	//Credits to "Njol" for this code : https://bukkit.org/threads/gettargetentity-something-like-this.59837/
	private static <T extends Entity> T getTarget(final Entity entity, final Iterable<T> entities)
	{
		if (entity == null)
		{
			return null;
	    }
	        
	    T target = null;
	    
	    final double seuil = 1.5;
	        
	    for (final T other : entities) 
	    {
	        final Vector n = other.getLocation().toVector().subtract(entity.getLocation().toVector());
	            
	         if (entity.getLocation().getDirection().normalize().crossProduct(n).lengthSquared() < seuil && n.normalize().dot(entity.getLocation().getDirection().normalize()) >= 0) 
	         {
	             if (target == null || target.getLocation().distanceSquared(entity.getLocation()) > other.getLocation().distanceSquared(entity.getLocation()))
	             {
	                 target = other;
	             }
	         }
	     }
	     return target;
	}
	
	public static byte convertColor(byte b)
	{
		if (b == (byte) 0) return 15;
		if (b == (byte) 1) return 14;
		if (b == (byte) 2) return 13;
		if (b == (byte) 3) return 12;
		if (b == (byte) 4) return 11;
		if (b == (byte) 5) return 10;
		if (b == (byte) 6) return 9;
		if (b == (byte) 7) return 8;
		if (b == (byte) 8) return 7;
		if (b == (byte) 9) return 6;
		if (b == (byte) 10) return 5;
		if (b == (byte) 11) return 4;
		if (b == (byte) 12) return 3;
		if (b == (byte) 13) return 2;
		if (b == (byte) 14) return 1;
		if (b == (byte) 15) return 0;
		else return (byte) 0;
	}
}
