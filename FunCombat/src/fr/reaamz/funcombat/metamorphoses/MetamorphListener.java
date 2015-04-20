package fr.reaamz.funcombat.metamorphoses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.Maps;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.selectioncouleur.SelectionCouleurUtils;

public class MetamorphListener implements Listener 
{
	private DyeColor dColor;
	
	private HashMap<Player, Entity> entities = Maps.newHashMap();
	
	private HashMap<Player, Integer> tasksIds = Maps.newHashMap();
	
	public Entity spawnNewEntity(Player player, Class<? extends Entity> clazz)
	{
		Entity ent = player.getWorld().spawn(player.getLocation(), clazz);
		
		Class<? extends Entity> c = ent.getClass();
		
		try 
		{
			Method setCustomName = c.getMethod("setCustomName", String.class);
			setCustomName.setAccessible(true);
			setCustomName.invoke(ent, player.getDisplayName());	
			
			Method addPotionEffect = c.getMethod("addPotionEffect", PotionEffect.class);
			addPotionEffect.setAccessible(true);
			addPotionEffect.invoke(ent, new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,1000000000,127));
			addPotionEffect.invoke(ent, (new PotionEffect(PotionEffectType.SLOW,1000000000,127)));
			
			if (ent instanceof Creeper)
			{
				Creeper creeper = (Creeper) ent;
				creeper.setPowered(true);
			}
			
			if (ent instanceof Horse)
			{
				Horse horse = (Horse) ent;
				horse.setVariant(Variant.SKELETON_HORSE);
			}
			
			if (ent instanceof Sheep)
			{
				Sheep sheep = (Sheep) ent;
				sheep.setColor(dColor);
			}
			
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10000000,1));
			
			entities.put(player, ent);
		} 
		catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			e.printStackTrace();
		}
		
		return ent;
	}
	
	private void setTeleporter(final Player player, final Entity ent)
	{
		int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(FunCombat.instance, new Runnable() 
		{
			@Override
			public void run() 
			{
				if(entities.containsKey(player))
				{
					if(!(ent == null))
					{
						ent.teleport(player.getLocation());
						ent.setFallDistance(0);
					}
					else
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Il y a eu un problème, s.v.p report au staff et essayer de deco-reco");
					}
				}
			}
		}, 0L, Long.MAX_VALUE);
		
		tasksIds.put(player, id);
	}
	
	private void refreshColor(Player player)
	{
		File file = new File(Bukkit.getPluginManager().getPlugin(Utils.PLUGIN_NAME).getDataFolder() + "\\Couleurs\\" + player.getUniqueId().toString() + ".txt");
		
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
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event)
	{
		if (event.getRightClicked() instanceof Creeper)
		{
			for (Entity ent : entities.values())
			{
				if (ent instanceof Creeper && ent.getCustomName() == event.getRightClicked().getCustomName())
				{
					if (event.getPlayer().getItemInHand().getType().equals(Material.FLINT_AND_STEEL))
					{
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onDamageByBlock(EntityDamageByBlockEvent event)
	{
		for (Entity ent : entities.values())
		{
			if (ent.equals(event.getEntity()))
			{
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onDamageByEntity(EntityDamageByEntityEvent event)
	{
		if (event.getDamager() instanceof Player)
		{
			for (Entity ent : entities.values())
			{
				if (ent.equals(event.getEntity()))
				{
					event.setCancelled(true);
				}
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

					Bukkit.getScheduler().cancelTask(tasksIds.get(e.getEntity()));
					tasksIds.remove(e.getEntity());
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

			Bukkit.getScheduler().cancelTask(tasksIds.get(e.getPlayer()));
			tasksIds.remove(e.getPlayer());
			
			ent.remove();
			
			Utils.removeAllPotionEffects(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onPlayerKick(final PlayerKickEvent e)
	{
		if (entities.containsKey(e.getPlayer()))
		{
			Entity ent = entities.get(e.getPlayer());
			
			entities.remove(e.getPlayer());

			Bukkit.getScheduler().cancelTask(tasksIds.get(e.getPlayer()));
			tasksIds.remove(e.getPlayer());
			
			ent.remove();
			
			Utils.removeAllPotionEffects(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		
		if (e.getInventory().getName().equals(Utils.SlotNames.FC_METAMORPH.getName()))
		{
			e.setCancelled(true);
						
			if(!(e.getCurrentItem() == null))
			{				
				if(e.getCurrentItem().getType().equals(Material.WOOL))
				{
					refreshColor(player);
					
					if (dColor == null)
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Votre couleur préférée n'est pas définie ! Veuillez choisir votre couleur préférée, via le menu prévu à cet effet, dans le menu principal.");
					}
					else
					{
						if(!(entities.containsKey(player)))
						{				
							Entity sheep = spawnNewEntity(player, Sheep.class);
							setTeleporter(player, sheep);
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
						Entity creep = spawnNewEntity(player, Creeper.class);
						setTeleporter(player, creep);
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
						Entity cow = spawnNewEntity(player, Cow.class);
						setTeleporter(player, cow);
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
						Entity giant = spawnNewEntity(player, Giant.class);
						setTeleporter(player, giant);
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voilà transformé en géant !");
					}
					else
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Vous êtes déjà Métamorphosé !");
					}
					player.closeInventory();
				}
				
				if(e.getCurrentItem().getType().equals(Material.FEATHER))
				{
					if(!(entities.containsKey(player)))
					{
						Entity chicken = spawnNewEntity(player, Chicken.class);
						setTeleporter(player, chicken);
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voilà transformé en poulet !");
					}
					else
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Vous êtes déjà Métamorphosé !");
					}
					player.closeInventory();
				}
				
				if(e.getCurrentItem().getType().equals(Material.SADDLE))
				{
					if(!(entities.containsKey(player)))
					{
						Entity horse = spawnNewEntity(player, Horse.class);
						setTeleporter(player, horse);
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voilà transformé en cheval-squelette !");
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
						
						Bukkit.getScheduler().cancelTask(tasksIds.get(player));
						tasksIds.remove(player);
						
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
