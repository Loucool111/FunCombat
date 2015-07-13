package fr.reaamz.funcombat.jump;

import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.google.common.collect.Maps;

import fr.reaamz.funcombat.FunCombat;

public class JumpManager implements Listener
{
	private HashMap<Player, String> jumpEnCours = Maps.newHashMap();
	
	public void beginJump(Player player, String jumpName)
	{
		jumpEnCours.put(player, jumpName);
	}
	
	public static void tpToStartZone(Player player, String jumpName)
	{
		try
		{
			Location zone = JumpUtils.getLocation(FunCombat.database.getStartZone(jumpName));
			player.teleport(zone);			
		}
		catch (SQLException e)		
		{		
			e.printStackTrace();
		}
	}
}
