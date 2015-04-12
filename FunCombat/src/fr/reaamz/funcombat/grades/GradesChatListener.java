package fr.reaamz.funcombat.grades;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.reaamz.funcombat.Utils;

public class GradesChatListener implements Listener 
{
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		event.setCancelled(true);
		String message = event.getMessage();
		Player player = event.getPlayer();
		
		if (player.getName().equalsIgnoreCase("Reaamz"))
			Utils.sendMessageAllPlayers((player.getDisplayName() + " " +  message));
	}
}
