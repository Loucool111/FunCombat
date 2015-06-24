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
		
		FC_MAINMENU(ChatColor.UNDERLINE + "Menu principal"),
		FC_KITPVP(ChatColor.UNDERLINE + "S�l�cteur de kit"),
		FC_METAMORPH(ChatColor.UNDERLINE + "S�l�cteur de M�tamorphoses"),
		FC_COLORS(ChatColor.UNDERLINE + "Choissisez votre couleur"),
		FC_HATS(ChatColor.UNDERLINE + "S�l�cteur de chapeaux"),
		FC_JUMP(ChatColor.UNDERLINE + "S�l�cteur de Jumps"),
		FC_JUMPSCORES(ChatColor.UNDERLINE + "Scores des Jumps"),
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
	
	public static void sendCustomMessage(Player p , String message)
	{
		p.sendMessage(ChatColor.GOLD + "[" + PLUGIN_NAME + "] " + ChatColor.RESET + message);
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
			return FunCombat.mysql.getColor(player);
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
}
