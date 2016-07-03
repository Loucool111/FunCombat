package fr.reaamz.funcombat.jump.score;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.reaamz.funcombat.Utils;

public class JumpScoreListener implements Listener
{
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		if (event.getInventory().getName().equals(Utils.InventoryNames.FC_JUMPSCORES.getName()))
		{
			event.setCancelled(true);
		}
	}
}
