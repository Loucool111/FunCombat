package fr.reaamz.funcombat.hats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Banner;
import org.bukkit.material.Wool;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.selectioncouleur.SelectionCouleurUtils;

public class HatsMenuListener implements Listener
{
	private DyeColor dColor;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		if (event.getInventory().getName().equals(Utils.InventoryNames.FC_HATS.getName()))
		{
			if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().hasItemMeta())
			{
				event.setCancelled(true);
				Player player = (Player) event.getWhoClicked();
				
				for (HatsType type : HatsType.values())
				{
					if (event.getCurrentItem().getType() == type.getType())
					{
						if (type.getType() == Material.BANNER)
						{
							refreshColor(player);
							Banner banner = new Banner(Material.BANNER);
							banner.setData(Utils.getDataFromDyeColor(dColor));
							player.getInventory().setHelmet(banner.toItemStack(1));
						}
						else if (type.getType() == Material.WOOL)
						{
							refreshColor(player);
							Wool wool = new Wool(dColor);
							player.getInventory().setHelmet(wool.toItemStack(1));
						}
						else
						{
							player.getInventory().setHelmet(new ItemStack(type.getType()));
						}
						
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
		if (event.getInventory().getName().equals(Utils.InventoryNames.MC_CREATIVE.getName()) || event.getInventory().getName().equals(Utils.InventoryNames.MC_SURVIVAL.getName()))
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
							Player player = (Player)event.getWhoClicked();
							
							if (type.getType() == Material.BANNER)
							{
								refreshColor(player);
								Banner banner = new Banner(Material.BANNER);
								banner.setData(Utils.getDataFromDyeColor(dColor));
								player.getInventory().setHelmet(banner.toItemStack(1));
							}
							else if (type.getType() == Material.WOOL)
							{
								refreshColor(player);
								if (dColor != null)
								{
									Wool wool = new Wool(dColor);
									player.getInventory().setHelmet(wool.toItemStack(1));
								}
								else
								{
									Utils.sendCustomMessage(player, ChatColor.RED + "Votre couleur préférée n'est pas définie ! Veuillez choisir votre couleur préférée, via le menu prévu à cet effet, dans le menu principal.");
								}
							}
							else
							{
								event.getWhoClicked().getInventory().setHelmet(new ItemStack(type.getType()));
							}
						}
					}
				}
			}
		}
	}
	
	private void refreshColor(Player player)
	{
		File file = new File(FunCombat.instance.getDataFolder() + "\\Couleurs\\" + player.getUniqueId().toString() + ".txt");
		
		BufferedReader br = null;
		
		if (file.exists())
		{
			try
			{
				String currentLine;
			
				br = new BufferedReader(new FileReader(file));
			
				currentLine = br.readLine();
				
				if(!(currentLine == null))
					dColor = SelectionCouleurUtils.getDyeColorFromString(currentLine);
			
				br.close();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}
}
