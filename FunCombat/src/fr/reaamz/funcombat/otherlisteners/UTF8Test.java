package fr.reaamz.funcombat.otherlisteners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.reaamz.funcombat.Utils;

public class UTF8Test implements Listener
{
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)	
	{
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType() == Material.LEVER)
			{
				Utils.sendCustomMessage(event.getPlayer(), "█▓▒░Reaamz░▒▓█");
			}
		}
	}
}
