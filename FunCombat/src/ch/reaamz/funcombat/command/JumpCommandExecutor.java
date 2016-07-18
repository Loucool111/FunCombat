package ch.reaamz.funcombat.command;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.reaamz.funcombat.FunCombat;
import ch.reaamz.funcombat.Utils;
import ch.reaamz.funcombat.jump.JumpNames;
import ch.reaamz.funcombat.jump.JumpUtils;
import ch.reaamz.funcombat.player.PlayerInfo;

public class JumpCommandExecutor implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (PlayerInfo.get(player).getPermissionLevel() == 2)
			{	
				if (cmd.getName().equals("setjump"))
				{
					if (args.length < 2) return false;
				
					for (JumpNames jump : JumpNames.values())
					{
						if (args[0].equalsIgnoreCase(jump.getName()))
						{
							try
							{
								if (args[1].equalsIgnoreCase(("startZone")))
								{
									FunCombat.database.updateJumpLoc(jump.getName(), player.getLocation(), JumpUtils.getLocation(FunCombat.database.getStartBlock(jump.getName())), JumpUtils.getLocation(FunCombat.database.getEndBlock(jump.getName())));
									Utils.sendCustomMessage(player, ChatColor.GREEN + "startZone d�fini avec succ�s");
								}
								else if (args[1].equalsIgnoreCase("startBlock"))
								{
									FunCombat.database.updateJumpLoc(jump.getName(), JumpUtils.getLocation(FunCombat.database.getStartZone(jump.getName())), player.getLocation(), JumpUtils.getLocation(FunCombat.database.getEndBlock(jump.getName())));
									Utils.sendCustomMessage(player, ChatColor.GREEN + "startBlock d�fini avec succ�s");
								}
								else if (args[1].equalsIgnoreCase("endBlock"))
								{
									FunCombat.database.updateJumpLoc(jump.getName(), JumpUtils.getLocation(FunCombat.database.getStartZone(jump.getName())), JumpUtils.getLocation(FunCombat.database.getStartBlock(jump.getName())), player.getLocation());
									Utils.sendCustomMessage(player, ChatColor.GREEN + "endBlock d�fini avec succ�s");
								}
								else if (args[1].equalsIgnoreCase("reset"))
								{
									FunCombat.database.resetJump(jump.getName());
									Utils.sendCustomMessage(player, ChatColor.GREEN + "Les coordon�es du " + jump.getItemName() + " ont �t� reset avec succ�s");
								}
								else
								{
									return false;
								}
							}
							catch (SQLException e)
							{
								e.printStackTrace();
							}
						
							return true;
						}
					}
				}
			}
			else
				Utils.sendCustomMessage(player, ChatColor.RED + "Vous n'avez pas la puissance pour effectuer cette commande.");
		}
		return false;
	}
}
