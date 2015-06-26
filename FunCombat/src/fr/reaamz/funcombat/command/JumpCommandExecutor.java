package fr.reaamz.funcombat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
				if (args.length < 1) return false;
				
				if (args[0].equals("jump1"))
				{
					player.sendRawMessage("a");
				}
				else if (args[0].equals("jump2"))
				{
					
				}
				else
				{
					
				}
			}
		}
		return false;
	}

}
