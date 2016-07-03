package fr.reaamz.funcombat.command;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.grades.GradeUtils;

public class GradeCommandExecutor implements IPluginCommand
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = null;
		
		if (sender instanceof Player)
		{
			player = (Player) sender;
		}
			
			if (cmd.getName().equals("grade"))
			{
				if (args.length < 3) return false;
				
				if (args[0].equals("set"))
				{
					try
					{
						UUID uuid;
						try
						{
							uuid = UUID.fromString(args[1]);
						}
						catch (IllegalArgumentException e)
						{
							uuid = Bukkit.getPlayer(args[1]).getUniqueId();
						}
						
						FunCombat.database.updateGrade(uuid, GradeUtils.getGradeFromString(args[2]));
						Utils.sendCustomMessage(player, "Le Grade de " + ChatColor.AQUA + "'" + args[1] + "'" + ChatColor.WHITE + " à été modifié en " + ChatColor.AQUA + args[2]);
					}
					catch (SQLException e) { e.printStackTrace(); }
					
					return true;
				}
				if (args[0].equals("get"))
				{
					try
					{
						Utils.sendCustomMessage(player, "Le Grade de " + ChatColor.AQUA + "'" + args[1] + "'" + ChatColor.WHITE + " est " + ChatColor.AQUA + FunCombat.database.getGrade(UUID.fromString(args[1])));
					}
					catch (SQLException e) { e.printStackTrace(); }
					
					return true;
				}
			}
			
			if (cmd.getName().equals("uuid"))
			{
				Utils.sendCustomMessage(player, player.getUniqueId().toString());
				return true;
			}
		
		return false;
	}

	@Override
	public void displayHelp(Player player)
	{	
		Utils.sendCustomMessage(player, "Permet de modifier le grade d'un joueur.");
		Utils.sendCustomMessage(player, "Utilisation : /grade set;get <UUID || PLAYERNAME>");
	}
}
