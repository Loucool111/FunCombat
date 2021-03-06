package ch.reaamz.funcombat.hub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ch.reaamz.funcombat.FunCombat;
import ch.reaamz.funcombat.Utils;

public class HubJoinListener implements Listener
{
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event)
	{
		final Player player = event.getPlayer();
		
		HubUtils.equipHubStuff(player);
		
		player.getInventory().setArmorContents(null);
		
		player.setGameMode(GameMode.CREATIVE);
		
		Utils.sendCustomMessage(player, ChatColor.AQUA + "Bienvenue sur " + Utils.PLUGIN_NAME + ", " + ChatColor.YELLOW + player.getName());
		
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());	
				
		HubWelcome.sendWelcomeMessages(player);
				
		Bukkit.getScheduler().runTaskLater(FunCombat.instance, new Runnable() 
		{			
			@Override
			public void run()
			{				
				HubWelcome.sendWelcomeTitle(player);
			}
		}, 20);
	}
}
