package ch.reaamz.funcombat.mainmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import ch.reaamz.funcombat.Utils;
import ch.reaamz.funcombat.color.ColorUtils;
import ch.reaamz.funcombat.hats.HatsUitls;
import ch.reaamz.funcombat.inventory.GenericContainer;
import ch.reaamz.funcombat.jump.JumpUtils;
import ch.reaamz.funcombat.kitpvp.KitpvpUtils;
import ch.reaamz.funcombat.metamorphoses.MetamorphUtils;
import ch.reaamz.funcombat.player.PlayerInfo;

public class MainMenuListener implements Listener 
{
	@EventHandler
	public void onInventoryClick (final InventoryClickEvent e)
	{		
		Player player = (Player) e.getWhoClicked();
		
		if (e.getInventory().getName().equals(Utils.InventoryNames.FC_MAINMENU.getName()))
		{	
			if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta())
			{									
				e.setCancelled(true);
				
				if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD)
				{					
					e.getWhoClicked().openInventory(new GenericContainer(Utils.InventoryNames.FC_KITPVP.getName(), 9, KitpvpUtils.getItemsPourMenu()).getInventory());
				}
					
				if(e.getCurrentItem().getType() == Material.BLAZE_ROD)
				{						
					e.getWhoClicked().openInventory(new GenericContainer(Utils.InventoryNames.FC_METAMORPH.getName(), 18, MetamorphUtils.getItemsForMenu()).getInventory());
				}
					
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Choissisez votre couleur"))
				{					
					e.getWhoClicked().openInventory(new GenericContainer(Utils.InventoryNames.FC_COLORS.getName(), 18, ColorUtils.getInventoryContentsForMenu()).getInventory());
				}
						
				if (e.getCurrentItem().getType() == Material.HOPPER)
				{
					PlayerInfo.get(player).sendToServer("Minigame");
				}
						
				if (e.getCurrentItem().getType() == Material.FEATHER)
				{
					e.getWhoClicked().openInventory(new GenericContainer(Utils.InventoryNames.FC_JUMP.getName(), 27, JumpUtils.getMenuItems()).getInventory());
				}
				
				if (e.getCurrentItem().getType() == Material.CHAINMAIL_HELMET)
				{
					e.getWhoClicked().openInventory(new GenericContainer(Utils.InventoryNames.FC_HATS.getName(), 27, HatsUitls.getItemForMenu()).getInventory());
				}
			}	
		}
	}
}
