package ch.reaamz.funcombat.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.reaamz.funcombat.FunCombat;
import ch.reaamz.funcombat.Utils;
import ch.reaamz.funcombat.player.PlayerInfo;

public class SlowCommandExecutor implements IPluginCommand
{
	public static long slow = 1000;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (PlayerInfo.get(player).getPermissionLevel() >= 1)
			{
				Utils.sendCustomMessage(player, "adwawd");
				enableAutoReseter();
			}
		}
		return false;
	}

	@Override
	public void displayHelp(Player player)
	{

	}
	
	private void enableAutoReseter()
	{
		Bukkit.getScheduler().runTaskLater(FunCombat.instance, new Runnable()
		{
			@Override
			public void run()
			{			
				
			}
			
		}, 20 * 60 * 10);
	}
}
