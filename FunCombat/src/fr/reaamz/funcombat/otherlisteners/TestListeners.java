package fr.reaamz.funcombat.otherlisteners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.reaamz.funcombat.player.FCPlayer;

public class TestListeners implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)	
	{
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType() == Material.LEVER)
			{
				Block block = event.getPlayer().getTargetBlock(null, 30);
				if (event.getPlayer().getInventory().contains(Material.TNT))
				{
					block.getWorld().spawn(block.getLocation().add(1.5, 1.5, 1.5), TNTPrimed.class);
				}				
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		FCPlayer player = new FCPlayer(event.getPlayer());
		player.setTitleBar(ChatColor.YELLOW + "Bienvenue sur FunCombat !, " + ChatColor.RED + player.getPlayer().getName());
	}
}
