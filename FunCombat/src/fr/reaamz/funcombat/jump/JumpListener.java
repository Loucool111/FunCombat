package fr.reaamz.funcombat.jump;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.jump.score.JumpScore;
import fr.reaamz.funcombat.virtualinventory.GenericVirtualInventory;

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
					player.openInventory(new GenericVirtualInventory(Utils.InventoryNames.FC_JUMPSCORES.getName(), 18, JumpScore.getScore()).getInventory());
				}
				else if (event.getCurrentItem().getType().equals(Material.FEATHER))
				{
					for (JumpNames jump : JumpNames.values())
					{
						if (jump.getItemName().equals(event.getCurrentItem().getItemMeta().getDisplayName()))
						{
							//TP player to start zone
							JumpManager.tpToStartZone(player, jump.getName());
						}
					}
				}
			}			
		}
	}
}
