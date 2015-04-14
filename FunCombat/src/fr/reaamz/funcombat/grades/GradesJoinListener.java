package fr.reaamz.funcombat.grades;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
		
		File file = new File(FunCombat.instance.getDataFolder()+"\\Grades\\Grades.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		
		List<String> ADMIN = conf.getStringList("ADMIN");
		List<String> DEV = conf.getStringList("DEV");
		List<String> MODO = conf.getStringList("MODO");
		
		if (!(ADMIN.contains(player.getName()) || DEV.contains(player.getName()) || MODO.contains(player.getName())))
		{
			conf.getStringList("JOUEUR").add(player.getName());
		}
		
		List<String> JOUEUR = conf.getStringList("JOUEUR");
		
		try
		{
			conf.save(file);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		if (ADMIN.contains(player.getName()))
		{
			player.setDisplayName(ChatColor.RED + "[ADMIN] " + player.getName() + ChatColor.RESET);
			player.setCustomName(ChatColor.RED + "[ADMIN] " + player.getName() + ChatColor.RESET);
			player.setPlayerListName(ChatColor.RED + "[ADMIN] " + player.getName());
		}
		if (DEV.contains(player.getName()))
		{
			player.setDisplayName(ChatColor.GOLD + "[DEV] " + player.getName() + ChatColor.RESET);
			player.setCustomName(ChatColor.GOLD + "[DEV] " + player.getName() + ChatColor.RESET);
			player.setPlayerListName(ChatColor.GOLD + "[DEV] " + player.getName());
		}
		if (MODO.contains(player.getName()))
		{
			player.setDisplayName(ChatColor.AQUA + "[MODO] " + player.getName() + ChatColor.RESET);
			player.setCustomName(ChatColor.AQUA + "[MODO] " + player.getName() + ChatColor.RESET);
			player.setPlayerListName(ChatColor.AQUA + "[MODO] " + player.getName());
		}
		if (JOUEUR.contains(player.getName()))
		{
			player.setDisplayName(ChatColor.GRAY + "" + ChatColor.ITALIC + player.getName() + ChatColor.RESET + ChatColor.GRAY);
			player.setCustomName(ChatColor.GRAY + "" + ChatColor.ITALIC + player.getName() + ChatColor.RESET + ChatColor.GRAY);
			player.setPlayerListName(ChatColor.ITALIC + "" + ChatColor.GRAY + player.getName());
		}
		
		
		event.setJoinMessage(player.getDisplayName() + ChatColor.ITALIC + ChatColor.GRAY + " joined the game");
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
