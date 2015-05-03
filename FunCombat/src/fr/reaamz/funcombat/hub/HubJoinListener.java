package fr.reaamz.funcombat.hub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.player.FCPlayer;

public class HubJoinListener implements Listener
{
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event)
	{
		final FCPlayer fcplayer = new FCPlayer(event.getPlayer());
		
		HubUtils.equipHubStuff(fcplayer.getPlayer());
		
		fcplayer.getPlayer().getInventory().setArmorContents(null);
		
		fcplayer.getPlayer().setGameMode(GameMode.CREATIVE);
		
		Utils.sendCustomMessage(fcplayer.getPlayer(), ChatColor.AQUA + "Bienvenue sur " + Utils.PLUGIN_NAME + ", " + ChatColor.YELLOW + fcplayer.getPlayer().getName());
		
		fcplayer.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());	
		
		fcplayer.sendWelcomeMessages();
				
		Bukkit.getScheduler().runTaskLater(FunCombat.instance, new Runnable() 
		{			
			@Override
			public void run()
			{
				fcplayer.sendWelcomeTitle();
			}
		}, 20);
	}
}
