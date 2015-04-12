package fr.reaamz.funcombat.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.event.HubEvent;
import fr.reaamz.funcombat.hub.HubUtils;
import fr.reaamz.funcombat.kitpvp.KitpvpKits;

public class HubCommandExecutor implements CommandExecutor
{
	static Location loc;
	
	public static void init()
	{
		if(FunCombat.instance.getConfig().getBoolean("isLocationSet"))
		{			
			loc = new Location(Bukkit.getWorld("world"),FunCombat.instance.getConfig().getDouble("xLoc"), FunCombat.instance.getConfig().getDouble("yLoc"), FunCombat.instance.getConfig().getDouble("zLoc"));		
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{	
		if(sender instanceof Player)
		{
			Player player = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("sethub"))
			{
				loc = player.getLocation();
				
				FunCombat.instance.getConfig().set("isLocationSet", true);
				FunCombat.instance.getConfig().set("xLoc",loc.getBlockX());
				FunCombat.instance.getConfig().set("yLoc",loc.getBlockY());
				FunCombat.instance.getConfig().set("zLoc",loc.getBlockZ());
				
				FunCombat.instance.saveConfig();
				
				Utils.sendCustomMessage(player,ChatColor.AQUA + "Location du hub définie : " + FunCombat.instance.getConfig().getInt("xLoc") + " " + FunCombat.instance.getConfig().getInt("yLoc") + " " + FunCombat.instance.getConfig().getInt("zLoc"));
				System.out.println("Loc set");
				
				return true;
			}
			
			if(cmd.getName().equalsIgnoreCase("hub"))
			{
				Utils.ClearInventoryAndPotionEffects(player);
				
				if(FunCombat.instance.getConfig().getBoolean("isLocationSet"))
				{			
					Utils.sendCustomMessage(player,ChatColor.AQUA + "Vous retournez au hub!");
					player.teleport(loc);
					player.playSound(player.getLocation(), Sound.EXPLODE, 50, 1);
				}
				else
				{
					Utils.sendCustomMessage(player,ChatColor.RED + "La location du hub n'est pas définie.");	
				}
				
				HubUtils.equipHubStuff(player);
				
				player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
				
				//envoi du HubEvent
				Bukkit.getServer().getPluginManager().callEvent(new HubEvent(Bukkit.getServerName(),player));
				
				player.setGameMode(GameMode.CREATIVE);
				
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("gethub"))
			{
				if(FunCombat.instance.getConfig().getBoolean("isLocationSet"))
				{
					Utils.sendCustomMessage(player,ChatColor.AQUA + "X: " + FunCombat.instance.getConfig().getInt("xLoc") + " Y: " + FunCombat.instance.getConfig().getInt("yLoc") + " Z: " + FunCombat.instance.getConfig().getInt("zLoc") + " " + loc.getWorld().getName());
				}
				else
				{
					Utils.sendCustomMessage(player,ChatColor.RED + "La location du hub n'est pas définie.");
				}
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("resethubdata"))
			{
				FunCombat.instance.getConfig().set("isLocationSet", false);
				FunCombat.instance.getConfig().set("xLoc", 0);
				FunCombat.instance.getConfig().set("yLoc", 0);
				FunCombat.instance.getConfig().set("zLoc", 0);
				
				FunCombat.instance.saveConfig();
				
				Utils.sendCustomMessage(player,"All data cleared.");
				
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("getMenu"))
			{	
				KitpvpKits.equipKitAdmArmure(player);
				KitpvpKits.equipKitAdmStuff(player);
				
				Utils.sendCustomMessage(player,ChatColor.DARK_PURPLE + "Vous etes desormais invincible.");
				
				return true;
			}
		}
		return false;
	}	
}
