package fr.reaamz.funcombat.grades;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.player.FCPlayer;

public class GradesJoinListener implements Listener 
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		
		try
		{
			if (FunCombat.mysql.getGrade(player) == null)
			{
				FunCombat.mysql.updateGrade(player, GradeType.JOUEUR);
			}
			
			if (FunCombat.mysql.getGrade(player).equals(GradeType.ADMIN))
			{
				player.setDisplayName(ChatColor.RED + "[ADMIN] " + player.getName() + ChatColor.RESET);
				player.setCustomName(ChatColor.RED + "[ADMIN] " + player.getName() + ChatColor.RESET);
				player.setPlayerListName(ChatColor.RED + "[ADMIN] " + player.getName());
			}
			if (FunCombat.mysql.getGrade(player).equals(GradeType.DEV))
			{
				player.setDisplayName(ChatColor.GOLD + "[DEV] " + player.getName() + ChatColor.RESET);
				player.setCustomName(ChatColor.GOLD + "[DEV] " + player.getName() + ChatColor.RESET);
				player.setPlayerListName(ChatColor.GOLD + "[DEV] " + player.getName());
			}
			if (FunCombat.mysql.getGrade(player).equals(GradeType.MODO))
			{
				player.setDisplayName(ChatColor.AQUA + "[MODO] " + player.getName() + ChatColor.RESET);
				player.setCustomName(ChatColor.AQUA + "[MODO] " + player.getName() + ChatColor.RESET);
				player.setPlayerListName(ChatColor.AQUA + "[MODO] " + player.getName());
			}
			if (FunCombat.mysql.getGrade(player).equals(GradeType.JOUEUR))
			{
				player.setDisplayName(ChatColor.GRAY + player.getName() + ChatColor.RESET + ChatColor.GRAY);
				player.setCustomName(ChatColor.GRAY  + player.getName() + ChatColor.RESET + ChatColor.GRAY);
				player.setPlayerListName(ChatColor.GRAY + player.getName());
			}
			
			if (FunCombat.mysql.getGrade(player).equals(GradeType.JOUEUR))
			{
				event.setJoinMessage(player.getDisplayName() + ChatColor.GRAY + "" + ChatColor.ITALIC + " joined the game");
			}
			else
			{
				event.setJoinMessage(player.getDisplayName() + ChatColor.WHITE + "" + ChatColor.ITALIC + " joined the game");
			}
		}
		catch (SQLException e1){ e1.printStackTrace(); }		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		FCPlayer player = new FCPlayer(event.getPlayer());

		player.sendWelcomeMessages();
	}
	
	@EventHandler
	public void onPlayerDisconnect(PlayerQuitEvent event)
	{
		event.setQuitMessage(null);
	}
}
