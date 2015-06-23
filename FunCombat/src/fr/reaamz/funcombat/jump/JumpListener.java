package fr.reaamz.funcombat.jump;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.reaamz.funcombat.Utils;

public class JumpListener implements Listener
{
	@EventHandler
	public void onInventoryClic(InventoryClickEvent event)
	{
		if (event.getInventory().getName().equals(Utils.InventoryNames.FC_JUMP.getName()))
		{
			event.setCancelled(true);
			
			Player player = (Player) event.getWhoClicked();
			
			if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR)
			{
				if (event.getCurrentItem().getType().equals(Material.BOOK_AND_QUILL))
				{
					//new score inv
				}
				else if (event.getCurrentItem().getType().equals(Material.FEATHER))
				{
					if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Jump #1"))
					{
						Utils.sendCustomMessage(player, "Jump #1");
					}
					else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Jump #2"))
					{
						Utils.sendCustomMessage(player, "Jump #2");
					}
					else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Jump #3"))
					{
						Utils.sendCustomMessage(player, "Jump #3");
					}
				}
			}			
		}
	}
}
