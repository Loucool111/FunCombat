package fr.reaamz.funcombat.kitpvp;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.reaamz.funcombat.kitpvp.KitpvpKits.Kits;

public class KitpvpListener implements Listener 
{
	Kitpvp kits;
	
	public KitpvpListener() 
	{		
		kits = new Kitpvp();
	}
	
	@EventHandler
	public void onInventoryClick (final InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		
		if (e.getInventory().getTitle().contains("Sélécteur de kit"))
		{
			e.setCancelled(true);
			if(!(e.getCurrentItem() == null))
			{
				if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD)
				{
					kits.newKitpvpPlayer(player, Kits.GUERRIER);
					e.setCancelled(true);
					player.closeInventory();
					player.setGameMode(GameMode.SURVIVAL);
				}
				
				if(e.getCurrentItem().getType() == Material.BOW)
				{
					kits.newKitpvpPlayer(player, Kits.ARCHER);
					e.setCancelled(true);
					player.closeInventory();
					player.setGameMode(GameMode.SURVIVAL);
				}
			}
		}
	}

}
