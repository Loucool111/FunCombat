package fr.reaamz.funcombat.command;

import java.sql.SQLException;

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
				if (args.length < 1) return false;
				
				if (args[0].equals("set"))
				{
					if (args.length < 2) return false;
					
					try
					{
						FunCombat.mysql.updateGrade(player, GradeUtils.getGradeFromString(args[1]));
						Utils.sendCustomMessage(player, "Le Grade de " + ChatColor.AQUA + player.getName() + " à été modifié en " + args[1]);
					}
					catch (SQLException e) { e.printStackTrace(); }
					
					return true;
				}
				if (args[0].equals("get"))
				{
					if (args.length < 2) return false;
					
					try
					{
						Utils.sendCustomMessage(player, "Le Grade de " + ChatColor.AQUA + player.getName() + " est " + FunCombat.mysql.getGrade(player));
					}
					catch (SQLException e) { e.printStackTrace(); }
					
					return true;
				}
			}
		}
		return false;
	}
}
