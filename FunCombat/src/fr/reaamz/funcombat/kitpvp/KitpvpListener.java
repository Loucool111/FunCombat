package fr.reaamz.funcombat.kitpvp;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.reaamz.funcombat.Utils.InventoryNames;
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
		
		if (e.getInventory().getName().equals(InventoryNames.FC_KITPVP.getName()))
		{
			e.setCancelled(true);
			
			if(!(e.getCurrentItem() == null))
			{
				for (Kits kit : Kits.values())
				{
					if (e.getCurrentItem().getType().equals(kit.getTypeInv()))
					{
						kits.newKitpvpPlayer(player, kit);
						e.setCancelled(true);
						player.closeInventory();
						player.setGameMode(GameMode.SURVIVAL);
					}
				}
			}
		}
	}
}
