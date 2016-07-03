package fr.reaamz.funcombat.completers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import fr.reaamz.funcombat.jump.JumpNames;

public class JumpTabCompleter implements TabCompleter
{
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
	{
		List<String> jumps = new ArrayList<String>();
		
		for (JumpNames jump : JumpNames.values())
		{
			jumps.add(jump.getName());
		}
		
		for (JumpNames jump : JumpNames.values())
		{
			if (args[0].equalsIgnoreCase(jump.getName()))
			{
				return Arrays.asList("startZone","startBlock","endBlock","reset");
			}
		}		
		
		return jumps;
	}
}
