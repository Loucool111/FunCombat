package fr.reaamz.funcombat.selectioncouleur;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;

public class SelectionCouleurListener implements Listener
{
	DyeColor dColor = null;
	
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		
		if (e.getInventory().getName().equals(Utils.InventoryNames.FC_COLORS.getName()))
		{
			e.setCancelled(true);
			
			if (e.getCurrentItem() != null)
			{
				if(e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta())
				{
					try
					{
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Noir"))
						{
							dColor = DyeColor.BLACK;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Rouge"))
						{
							dColor = DyeColor.RED;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_GREEN + "Vert"))
						{
							dColor = DyeColor.GREEN;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Marron"))
						{
							dColor = DyeColor.BROWN;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_BLUE + "Bleu"))
						{
							dColor = DyeColor.BLUE;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Mauve"))
						{
							dColor = DyeColor.PURPLE;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Cyan"))
						{
							dColor = DyeColor.CYAN;
						}
					
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GRAY + "Gris Clair"))
						{
							dColor = DyeColor.SILVER;
						}
					
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GRAY + "Gris"))
						{
							dColor = DyeColor.GRAY;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Rose"))
						{
							dColor = DyeColor.PINK;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Vert claire"))
						{
							dColor = DyeColor.LIME;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Jaune"))
						{
							dColor = DyeColor.YELLOW;
						}
					
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Bleu claire"))
						{
							dColor = DyeColor.LIGHT_BLUE;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Magenta"))
						{
							dColor = DyeColor.MAGENTA;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Orange"))
						{
							dColor = DyeColor.ORANGE;
						}
				
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Blanc"))
						{
							dColor = DyeColor.WHITE;
						}
						
						if(!e.getCurrentItem().getItemMeta().getDisplayName().contains("Menu principal"))
						{							
							//insertion des données dans la database
							if (dColor != null)
							{													
								FunCombat.mysql.updateDyeColor(player, dColor);
								Utils.sendCustomMessage(player, ChatColor.GREEN + "Votre couleur a bien été modifiée : " + dColor.toString().toLowerCase());
								player.closeInventory();
							}							
						}
					}
					catch (Exception e1){}					
				}
			}
		}
	}
}
