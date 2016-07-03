package fr.reaamz.funcombat.jump;

import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.google.common.collect.Maps;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.event.JumpDoneEvent;

public class JumpManager implements Listener
{
	private HashMap<Player, String> jumpEnCours = Maps.newHashMap();
	private HashMap<Player, Integer> jumpTasksIds = Maps.newHashMap();
	private HashMap<Player, Integer> jumpTimes = Maps.newHashMap();
	
	public void beginJump(final Player player, final String jumpName)
	{
		jumpEnCours.put(player, jumpName);
		
		jumpTimes.put(player, 0);
		
		int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(FunCombat.instance, new Runnable()
		{
			@Override
			public void run()
			{
				jumpTimes.put(player, jumpTimes.get(player) + 1);
			}
			
		}, 20L, 20L);
		
		jumpTasksIds.put(player, id);
	}
	
	public void endJump(Player player, String jumpName)
	{
		Bukkit.getScheduler().cancelTask(jumpTasksIds.get(player));
		
		Bukkit.getPluginManager().callEvent(new JumpDoneEvent(jumpName, player, jumpTimes.get(player)));
		
		jumpEnCours.remove(player);
		jumpTasksIds.remove(player);
		jumpTimes.remove(player);
	}
	
	public static void tpToStartZone(Player player, String jumpName)
	{
		try
		{
			Location zone = JumpUtils.getLocation(FunCombat.database.getStartZone(jumpName));
			player.teleport(zone);
		}
		catch (SQLException | NullPointerException e)
		{		
			Utils.sendCustomMessage(player, ChatColor.RED + "La zone du jump n'est pas définie");
		}
	}
	
	//TODO empecher le npe si la loc est null
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();
		
		for (JumpNames jump : JumpNames.values())
		{
			try
			{
				Location jumpLocStart = JumpUtils.getLocation(FunCombat.database.getStartBlock(jump.getName()));
				
				Location jumpLocEnd = JumpUtils.getLocation(FunCombat.database.getEndBlock(jump.getName()));
				
				if (!(jumpEnCours.containsKey(player)))
				{
					if (jumpLocStart.getBlockX() == player.getLocation().getBlockX() && jumpLocStart.getBlockZ() == player.getLocation().getBlockZ())
					{
						beginJump(player, jump.getName());
						Utils.sendCustomMessage(player, "Vous commencez le " + jump.getItemName());
					}
				}
				
				if (jumpEnCours.containsKey(player))
				{
					if (jumpLocEnd.getBlockX() == player.getLocation().getBlockX() && jumpLocEnd.getBlockZ() == player.getLocation().getBlockZ())
					{
						endJump(player, jump.getName());
					}
				}
			}
			catch (Exception e){}
		}
	}	
}
