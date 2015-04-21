package fr.reaamz.funcombat.mainmenu;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.hats.HatsMenuVirtualInventory;
import fr.reaamz.funcombat.hats.HatsUitls;
import fr.reaamz.funcombat.kitpvp.KitpvpUtils;
import fr.reaamz.funcombat.kitpvp.KitpvpVirtualInventory;
import fr.reaamz.funcombat.metamorphoses.MetamorphUtils;
import fr.reaamz.funcombat.metamorphoses.MetamorphVirtualInventory;
import fr.reaamz.funcombat.player.FCPlayer;
import fr.reaamz.funcombat.selectioncouleur.SelectionCouleurUtils;
import fr.reaamz.funcombat.selectioncouleur.SelectionCouleursVirtualInventory;

public class MainMenuListener implements Listener 
{
	@EventHandler
	public void onInventoryClick (final InventoryClickEvent e)
	{		
		Player player = (Player) e.getWhoClicked();
		
		FCPlayer fcplayer = new FCPlayer(player);

		KitpvpVirtualInventory kitpvpVMInstance = new KitpvpVirtualInventory();
		MetamorphVirtualInventory morphVMInstance  = new MetamorphVirtualInventory();
		SelectionCouleursVirtualInventory couleurInstance = new SelectionCouleursVirtualInventory();
		HatsMenuVirtualInventory hatsInstance = new HatsMenuVirtualInventory();

		if (e.getInventory().getName().equals(Utils.InventoryNames.FC_MAINMENU.getName()))
		{	
			if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta())
			{									
				e.setCancelled(true);
				
				if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD)
				{					
					Inventory KitpvpInv = kitpvpVMInstance.createInventory(KitpvpUtils.getItemsPourMenu());
					
					kitpvpVMInstance.showInventory(player, KitpvpInv);
				}
					
				if(e.getCurrentItem().getType() == Material.BLAZE_ROD)
				{						
					Inventory metamorphInv = morphVMInstance.createInventory(MetamorphUtils.getItemsForMenu());
							
					morphVMInstance.showInventory(player, metamorphInv);
				}
					
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Choissisez votre couleur"))
				{					
					Inventory colorWools = couleurInstance.createInventory(SelectionCouleurUtils.getInventoryContentsForMenu());
							
					couleurInstance.showInventory(player, colorWools);
				}
						
				if (e.getCurrentItem().getType() == Material.HOPPER)
				{
					fcplayer.sentToServer("Minigame");
				}
						
				if (e.getCurrentItem().getType() == Material.FEATHER)
				{
					player.teleport(new Location(player.getWorld(),-265,100,361));	
				}
				
				if (e.getCurrentItem().getType() == Material.CHAINMAIL_HELMET)
				{
					Inventory inv = hatsInstance.createInventory(HatsUitls.getItemForMenu());
					
					hatsInstance.showInventory(player, inv);
				}
			}	
		}
	}
}
