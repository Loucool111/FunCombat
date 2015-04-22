package fr.reaamz.funcombat.grades;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;

public class GradesChatListener implements Listener 
{
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		event.setCancelled(true);
		String message = event.getMessage();
		Player player = event.getPlayer();
		
		File file = new File(FunCombat.instance.getDataFolder()+"\\Grades\\Grades.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		
		List<String> JOUEUR = conf.getStringList("JOUEUR");
		
		if (!(JOUEUR.contains(player.getUniqueId())))
			message = ChatColor.translateAlternateColorCodes('$', message);

		Utils.sendMessageAllPlayers((player.getDisplayName() + " " +  message));
	}
}
