package fr.reaamz.funcombat.hub;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.mainmenu.MainMenuUtils;
import fr.reaamz.funcombat.mainmenu.MainMenuVirtualInventory;

public class HubClickListener implements Listener
{	
	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent event)
	{
		MainMenuVirtualInventory MainMenuInstance = new MainMenuVirtualInventory();
		
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			if(!(event.getItem() == null))
			{
				if(event.getMaterial() == Material.COMPASS)
				{
					Inventory inv = MainMenuInstance.createInventory(MainMenuUtils.getMainMenuItems());
					
					MainMenuInstance.showInventory(event.getPlayer(),inv);
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e)
	{
		MainMenuVirtualInventory MainMenuInstance = new MainMenuVirtualInventory();
		
		if(e.getInventory().getName().equals(Utils.InventoryNames.MC_CREATIVE.getName()) || e.getInventory().getName().equals(Utils.InventoryNames.MC_SURVIVAL.getName()))
		{
			if (!(e.getCurrentItem() == null))
			{
				if (!(e.getCurrentItem().getType() == Material.AIR))
				{
					if(e.getCurrentItem().getType() == Material.COMPASS)
					{
						Inventory inv = MainMenuInstance.createInventory(MainMenuUtils.getMainMenuItems());
						
						MainMenuInstance.showInventory((Player)e.getWhoClicked(),inv);
						
						e.setCancelled(true);
					}
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
