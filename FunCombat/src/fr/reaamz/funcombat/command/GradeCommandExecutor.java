package fr.reaamz.funcombat.command;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.grades.GradeUtils;

public class GradeCommandExecutor implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (cmd.getName().equals("grade"))
			{
				if (args.length < 3) return false;
				
				if (args[0].equals("set"))
				{
					try
					{
						FunCombat.database.updateGrade(UUID.fromString(args[1]), GradeUtils.getGradeFromString(args[2]));
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
		}
		return false;
	}
}
