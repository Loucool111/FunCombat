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
import fr.reaamz.funcombat.Utils;
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
		List<String> JOUEUR = conf.getStringList("JOUEUR");
		
		if (!(ADMIN.contains(player.getName()) || DEV.contains(player.getName()) || MODO.contains(player.getName()) || JOUEUR.contains(player.getName())))
		{
			JOUEUR.add(player.getName());
			conf.set("JOUEUR", JOUEUR);
			
			Utils.logInfo("added to player");
		}
		
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
			player.setDisplayName(ChatColor.GRAY + player.getName() + ChatColor.RESET + ChatColor.GRAY);
			player.setCustomName(ChatColor.GRAY  + player.getName() + ChatColor.RESET + ChatColor.GRAY);
			player.setPlayerListName(ChatColor.GRAY + player.getName());
		}
		
		if (JOUEUR.contains(player.getName()))
		{
			event.setJoinMessage(player.getDisplayName() + ChatColor.GRAY + "" + ChatColor.ITALIC + " joined the game");
		}
		else
		{
			event.setJoinMessage(player.getDisplayName() + ChatColor.WHITE + "" + ChatColor.ITALIC + " joined the game");
		}
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
