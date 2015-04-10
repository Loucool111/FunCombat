package fr.reaamz.funcombat.metamorphoses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.Maps;

import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.selectioncouleur.SelectionCouleurUtils;

public class MetamorphListener implements Listener 
{
	DyeColor dColor;
	
	Map<Player, Entity> entities = Maps.newHashMap();
	
	private void createNewSheep(DyeColor color,Player player)
	{
		Entity ent = player.getWorld().spawnEntity(player.getLocation(), EntityType.SHEEP);
		
		Sheep sheep = (Sheep) ent;
		sheep.setColor(color);
		
		sheep.setCustomName(player.getDisplayName());
		sheep.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,1000000000,127));
		sheep.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,1000000000,127));
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10000000,1));
		
		entities.put(player, ent);
	}
	
	private void createNewCreeper(Player player)
	{
		Entity ent = player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
		
		Creeper creeper = (Creeper) ent;
		
		creeper.setCustomName(player.getDisplayName());
		creeper.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,1000000000,127));
		creeper.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,1000000000,127));
		creeper.setPowered(true);
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10000000,1));
		
		entities.put(player, ent);
	}
	
	private void createNewCow(Player player)
	{
		Entity ent = player.getWorld().spawnEntity(player.getLocation(), EntityType.COW);
		
		Cow cow = (Cow) ent;
		
		cow.setCustomName(player.getDisplayName());
		cow.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,1000000000,127));
		cow.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,1000000000,127));
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10000000,1));
		
		entities.put(player, ent);
	}
	
	private void createNewGiant(Player player)
	{
		Entity ent = player.getWorld().spawnEntity(player.getLocation(), EntityType.GIANT);
		
		Giant giant = (Giant) ent;
		
		giant.setCustomName(player.getDisplayName());
		giant.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,1000000000,127));
		giant.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,1000000000,127));
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10000000,1));
		
		entities.put(player, ent);
	}
	
	//private void createNewEntity(Player player, EntityType type, Class<? extends EntityType> entityClass)
	//{
		//Entity ent = player.getWorld().spawnEntity(player.getLocation(), type);
		
		//entityClass. a = (entityClass) ent;
		
		//preciseEnt.setCustomName(player.getDisplayName());
		//preciseEnt.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,1000000000,127));
		
		//player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10000000,1));
		
		//entities.put(player, ent);
	//}
	
	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent e)
	{
		if(entities.containsKey(e.getPlayer()))
		{
			Entity ent = entities.get(e.getPlayer());
			
			if(!(ent == null))
			{
				ent.teleport(e.getPlayer().getLocation());
			}
			else
			{
				Utils.sendCustomMessage(e.getPlayer(), ChatColor.RED + "Il y a eu un problème, s.v.p report au staff et essayer de deco-reco");
			}
		}
	}
	
	@EventHandler
	public void onEntityDeath(final EntityDeathEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			String name = e.getEntity().getName();
			for (Map.Entry<Player, Entity> ent : entities.entrySet())
			{
				if (ent.getValue().getName().equals(name))
				{
					ent.getValue().remove();
					entities.remove(e.getEntity());
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerQuit(final PlayerQuitEvent e)
	{	
		if (entities.containsKey(e.getPlayer()))
		{
			Entity ent = entities.get(e.getPlayer());
			
			entities.remove(e.getPlayer());
			
			ent.remove();
			
			Utils.removeAllPotionEffects(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		
		if (e.getInventory().getTitle().contains("Sélécteur de Métamorphoses"))
		{
			e.setCancelled(true);
			
			//----------------------------------------------------------------------------------------------------------------------
			File file = new File(Bukkit.getPluginManager().getPlugin("FunCombat").getDataFolder() + "\\" + player.getName() + ".txt");
			
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
			//-----------------------------------------------------------------------------------------------------------------------
			
			if(!(e.getCurrentItem() == null))
			{				
				if(e.getCurrentItem().getType().equals(Material.WOOL))
				{
					if (dColor == null)
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Votre couleur préférée n'est pas définie ! Veuillez choisir votre couleur préférée, via le menu prévu à cet effet, dans le menu principal.");
					}
					else
					{
						if(!(entities.containsKey(player)))
						{
							createNewSheep(dColor, player);
							Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voilà transformé en mouton !");
						}
						else
						{
							Utils.sendCustomMessage(player, ChatColor.RED + "Vous êtes déjà Métamorphosé !");
						}
						player.closeInventory();
					}					
				}
				
				if(e.getCurrentItem().getType().equals(Material.SULPHUR))
				{
					if(!(entities.containsKey(player)))
					{
						createNewCreeper(player);
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voilà transformé en creeper !");
					}
					else
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Vous êtes déjà Métamorphosé !");
					}
					player.closeInventory();				
				}
				
				if(e.getCurrentItem().getType().equals(Material.LEATHER))
				{
					if(!(entities.containsKey(player)))
					{
						createNewCow(player);
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voilà transformé en vache !");
					}
					else
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Vous êtes déjà Métamorphosé !");
					}
					player.closeInventory();				
				}
				
				if(e.getCurrentItem().getType().equals(Material.ROTTEN_FLESH))
				{
					if(!(entities.containsKey(player)))
					{
						createNewGiant(player);
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voilà transformé en géant !");
					}
					else
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Vous êtes déjà Métamorphosé !");
					}
					player.closeInventory();
				}
				
				if(e.getCurrentItem().getType().equals(Material.THIN_GLASS))
				{
					if (entities.containsKey(player))
					{
						Entity ent = entities.get(player);
						
						ent.remove();
						Utils.sendCustomMessage(player,ChatColor.GREEN + "Votre Métamorphose à été supprimée");
						
						entities.remove(player);
						Utils.removeAllPotionEffects(player);
						
						player.closeInventory();
					}
					else
					{
						Utils.sendCustomMessage(player,ChatColor.RED + "Aucune Métamorphose à supprimer");
						player.closeInventory();
					}
				}
			}	
		}
	}
}
