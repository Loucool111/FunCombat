package fr.reaamz.funcombat.hats;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.reaamz.funcombat.Utils;

public class HatsMenuListener implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClick(InventoryClickEvent event)
	{
		if (event.getInventory().getName().contains("Sélécteur de chapeaux"))
		{
			if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().hasItemMeta())
			{
				event.setCancelled(true);
				Player player = (Player) event.getWhoClicked();
				
				for (HatsType type : HatsType.values())
				{
					if (event.getCurrentItem().getType() == type.getType())
					{
						player.getInventory().setHelmet(new ItemStack(type.getType()));
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Votre équipez votre chapeau");
						player.closeInventory();
					}
				}
				
				if (event.getCurrentItem().getType() == Material.THIN_GLASS)
				{
					player.closeInventory();
					
					if (player.getInventory().getHelmet() != null)
					{
						player.getInventory().setHelmet(null);
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Votre chapeau à été retiré !");
					}
					else
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Vous ne portez pas de chapeau en ce moment");
					}
				}
			}
		}
		if (event.getInventory().getName() == "container.inventory")
		{
			if (event.getCurrentItem() != null)
			{
				for (HatsType type : HatsType.values())
				{
					if (event.getWhoClicked().getInventory().getHelmet() != null)
					{
						if (type.getType() == event.getWhoClicked().getInventory().getHelmet().getType())
						{
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}
}
