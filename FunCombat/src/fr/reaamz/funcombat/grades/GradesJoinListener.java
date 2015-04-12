package fr.reaamz.funcombat.grades;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GradesJoinListener implements Listener 
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		
		if (player.getName().equalsIgnoreCase("Reaamz"))
			player.setDisplayName(ChatColor.RED + "[DEV] " + player.getName() + ChatColor.WHITE);
	}
}
