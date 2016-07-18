package ch.reaamz.funcombat.grades;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ch.reaamz.funcombat.FunCombat;
import ch.reaamz.funcombat.Utils;

public class GradesChatListener implements Listener 
{
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		event.setCancelled(true);
		
		String message = event.getMessage();
		Player player = event.getPlayer();
		
		try
		{
			if (FunCombat.database.getGrade(player.getUniqueId()) != GradeType.JOUEUR)
				message = ChatColor.translateAlternateColorCodes('$', message);
		}
		catch (SQLException e){}

		Utils.broadcastMessage((player.getDisplayName() + " " +  message));
	}
}
