package fr.reaamz.funcombat.hub;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Chicken;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.reaamz.funcombat.Utils;

public class TestListeners implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)	
	{
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType() == Material.DIAMOND_PICKAXE)
			{				
				Block block = event.getPlayer().getTargetBlock(null, 20);
				
				if (block.getType() != Material.AIR)
				{				
					block.getWorld().spawn(block.getLocation().add(0.5, 0.5, 0.5), Chicken.class);
					Utils.sendCustomMessage(event.getPlayer(), block.getType().toString().toLowerCase());
					block.setType(Material.AIR);
				}
			}
		}
	}
}
