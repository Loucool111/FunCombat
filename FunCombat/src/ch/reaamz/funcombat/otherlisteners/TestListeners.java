package ch.reaamz.funcombat.otherlisteners;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import ch.reaamz.funcombat.FunCombat;
import ch.reaamz.funcombat.event.JumpDoneEvent;
import ch.reaamz.funcombat.jump.JumpNames;

public class TestListeners implements Listener
{
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)	
	{
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.LEVER)
			{
				Block block = event.getPlayer().getTargetBlock(new HashSet<Material>(), 30);
				
				if (event.getPlayer().getInventory().contains(Material.TNT))
				{
					block.getWorld().spawn(block.getLocation().add(1.5, 1.5, 1.5), TNTPrimed.class);
					block.getWorld().strikeLightning(block.getLocation());
				}
				Bukkit.getPluginManager().callEvent(new JumpDoneEvent(JumpNames.JUMP2.getName(), event.getPlayer(), 6969));
				/*
				JSONChat.sendClicMessage(event.getPlayer(), "Destinations: ", "HUB", ChatUtils.ClicActions.RUN_COMMAND , "/hub");
				JSONChat.sendHoverMessage(event.getPlayer(), "----------------> ", "HOVER !", ChatUtils.HoverActions.SHOW_TEXT , "YEAH le HoverEvent marche !");
				
				JSONChat.sendClicHoverEvent(event.getPlayer(), "Clic ", ChatColor.GREEN + "ici", ChatUtils.ClicActions.RUN_COMMAND, "/hub", ChatUtils.HoverActions.SHOW_TEXT, "clic pour retourner au hub");
				*/
			}
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		File file = new File(FunCombat.instance.getDataFolder() + "\\" + event.getPlayer().getName() + "_UUID.txt");
		FileWriter out = null;
		
		try
		{
			if (!(file.exists()))
			{
				file.createNewFile();
			}
			
			out = new FileWriter(file);
			
			out.write(event.getPlayer().getName() + " : " + event.getPlayer().getUniqueId().toString());
			
			out.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
