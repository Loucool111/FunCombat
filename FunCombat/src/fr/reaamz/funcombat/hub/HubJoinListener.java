package fr.reaamz.funcombat.hub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
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
		final Player player = event.getPlayer();
		
		final FCPlayer fcplayer = new FCPlayer(player);		
		
		HubUtils.equipHubStuff(player);
		
		player.getInventory().setArmorContents(null);
		
		player.setGameMode(GameMode.CREATIVE);
		
		Utils.sendCustomMessage(player, ChatColor.AQUA + "Bienvenue sur " + Utils.PLUGIN_NAME + ", " + ChatColor.YELLOW + player.getName());
		
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());	
		
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
