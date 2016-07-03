package fr.reaamz.funcombat.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public interface IPluginCommand extends CommandExecutor
{
	void displayHelp(Player player);
}
