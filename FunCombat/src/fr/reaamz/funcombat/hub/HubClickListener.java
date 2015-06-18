package fr.reaamz.funcombat.hub;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.mainmenu.MainMenuUtils;
import fr.reaamz.funcombat.virtualinventory.GenericVirtualInventory;

public class HubClickListener implements Listener
{	
	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent event)
	{		
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			if(!(event.getItem() == null))
			{
				if(event.getItem().equals(HubUtils.getMenuItem()))
				{					
					event.getPlayer().openInventory(new GenericVirtualInventory(Utils.InventoryNames.FC_MAINMENU.getName(), 27, MainMenuUtils.getMainMenuItems()).getInventory());
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e)
	{
		if (!(e.getCurrentItem() == null))
		{
			if (!(e.getCurrentItem().getType() == Material.AIR))
			{
				if(e.getCurrentItem().equals(HubUtils.getMenuItem()))
				{
					e.getWhoClicked().openInventory(new GenericVirtualInventory(Utils.InventoryNames.FC_MAINMENU.getName(), 27, MainMenuUtils.getMainMenuItems()).getInventory());
						
					e.setCancelled(true);
				}
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerDropItem(final PlayerDropItemEvent e)
	{		
		if (!(e.getItemDrop().getItemStack() == null))
		{
			if (!(e.getItemDrop().getItemStack().getType() == Material.AIR))
			{
				if(e.getItemDrop().getItemStack().getType() == Material.COMPASS)
				{
					e.setCancelled(true);
				}
			}
		}
	}
}
