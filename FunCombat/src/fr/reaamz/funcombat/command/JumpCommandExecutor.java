package fr.reaamz.funcombat.command;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.jump.JumpNames;
import fr.reaamz.funcombat.jump.JumpUtils;

public class JumpCommandExecutor implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
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
							}
							if (args[1].equalsIgnoreCase("startBlock"))
							{
								FunCombat.database.updateJumpLoc(jump.getName(), JumpUtils.getLocation(FunCombat.database.getStartZone(jump.getName())), player.getLocation(), JumpUtils.getLocation(FunCombat.database.getEndBlock(jump.getName())));
							}
							if (args[1].equalsIgnoreCase("endBlock"))
							{
								FunCombat.database.updateJumpLoc(jump.getName(), JumpUtils.getLocation(FunCombat.database.getStartZone(jump.getName())), JumpUtils.getLocation(FunCombat.database.getStartBlock(jump.getName())), player.getLocation());
							}
							if (args[1].equalsIgnoreCase("reset"))
							{
								FunCombat.database.resetJump(jump.getName());
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
		return false;
	}
}
