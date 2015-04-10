package fr.reaamz.funcombat.mainmenu;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import fr.reaamz.funcombat.kitpvp.KitpvpUtils;
import fr.reaamz.funcombat.kitpvp.KitpvpVirtualInventory;
import fr.reaamz.funcombat.metamorphoses.MetamorphUtils;
import fr.reaamz.funcombat.metamorphoses.MetamorphVirtualInventory;
import fr.reaamz.funcombat.selectioncouleur.SelectionCouleurUtils;
import fr.reaamz.funcombat.selectioncouleur.SelectionCouleursVirtualMenu;

public class MainMenuListener implements Listener 
{
	/*
	Plugin pl;
	public MainMenuListener(Plugin p) 
	{
		Bukkit.getPluginManager().registerEvents(this, p);
		pl = p;
	}
	*/
	
	@EventHandler
	public void onInventoryClick (final InventoryClickEvent e)
	{		
		Player player = (Player) e.getWhoClicked();

		KitpvpVirtualInventory kitpvpVMInstance = new KitpvpVirtualInventory();
		
		MetamorphVirtualInventory morphVMInstance  = new MetamorphVirtualInventory();
		
		SelectionCouleursVirtualMenu couleurInstance = new SelectionCouleursVirtualMenu();

		if (e.getInventory().getName().contains("Menu principal"))
		{	
			if(!(e.getCurrentItem() == null))
			{				
				if(!(e.getCurrentItem().getType() == Material.AIR))
				{					
					e.setCancelled(true);
					
					try
					{
						if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD)
						{					
							Inventory KitpvpInv = kitpvpVMInstance.createInventory(KitpvpUtils.getItemsPourMenu());
					
							kitpvpVMInstance.showInventory(player, KitpvpInv);
						}
					
						if(e.getCurrentItem().getType() == Material.BLAZE_ROD)
						{						
							Inventory metamorphInv = morphVMInstance.createInventory(MetamorphUtils.getMetaMorphMenuItems());
							
							morphVMInstance.showInventory(player, metamorphInv);
						}
					
						if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Choissisez votre couleur"))
						{					
							Inventory colorWools = couleurInstance.createInventory(SelectionCouleurUtils.getItemsForMenu());
							
							couleurInstance.showInventory(player, colorWools);
						}
						
						if (e.getCurrentItem().getType() == Material.FEATHER)
						{
							player.teleport(new Location(player.getWorld(),1200,75,640));	
						}
					}
					catch(Exception e1){}
				}
			}	
		}
	}
}
