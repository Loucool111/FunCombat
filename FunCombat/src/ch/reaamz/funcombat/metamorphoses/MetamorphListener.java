package ch.reaamz.funcombat.metamorphoses;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.Maps;

import ch.reaamz.funcombat.FunCombat;
import ch.reaamz.funcombat.Utils;
import ch.reaamz.funcombat.event.HubEvent;

public class MetamorphListener implements Listener 
{
	private DyeColor dColor = DyeColor.WHITE;
	
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
	
	@EventHandler
	public void onHubEvent(HubEvent e)
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
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event)
	{
		if (event.getRightClicked() instanceof Creeper)
		{
			for (Entity ent : entities.values())
			{
				if (ent instanceof Creeper && ent.getCustomName() == event.getRightClicked().getCustomName())
				{
					if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.FLINT_AND_STEEL))
					{
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event)
	{
		if (event.getCause().equals(DamageCause.SUFFOCATION))
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
		
		if (e.getInventory().getName().equals(Utils.InventoryNames.FC_METAMORPH.getName()))
		{
			e.setCancelled(true);
						
			if(!(e.getCurrentItem() == null))
			{
				boolean sent = false;
				
				for (MetamorphType type : MetamorphType.values())
				{
					if (type.getItemMaterial() == e.getCurrentItem().getType())
					{
						if (!(entities.containsKey(player)))
						{
							dColor = Utils.refreshColor(player);
							Entity ent = spawnNewEntity(player, type.getClassEntity());
							if (type.hasCustomCode())
							{
								try 
								{
									Method m = getClass().getMethod(type.getMethodName(),Entity.class);
									m.setAccessible(true);
									ent = (Entity) m.invoke(this, ent);
								} 
								catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) 
								{
									e1.printStackTrace();
								}
							}
							setTeleporter(player, ent);
							Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voilà transformé en " + type.getText() + " !");
						}
						else
						{
							if (!sent)
							{
								Utils.sendCustomMessage(player, ChatColor.RED + "Vous êtes déjà Métamorphosé !");
								sent = true;
							}
						}
						player.closeInventory();
					}	
				}
				
				sent = false;
				
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
						if (!sent)
						{
							Utils.sendCustomMessage(player,ChatColor.RED + "Aucune Métamorphose à supprimer");
						}
						player.closeInventory();
					}
				}				
			}
		}
	}
	
	public Entity creeperMethod(Entity ent)
	{
		Creeper creeper = (Creeper) ent;
		creeper.setPowered(true);
		return (Entity) creeper;
	}
	
	public Entity horseMethod(Entity ent)
	{
		Horse horse = (Horse) ent;
		horse.setVariant(Variant.SKELETON_HORSE);
		horse.setAdult();
		return (Entity) horse;
	}
	
	public Entity sheepMethod(Entity ent)
	{
		Sheep sheep = (Sheep) ent;
		sheep.setColor(dColor);
		return (Entity) sheep;
	}
	
	public Entity pigzombieMethod(Entity ent)
	{
		PigZombie pz = (PigZombie) ent;
		pz.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
		pz.setBaby(false);
		return (Entity) pz;
	}
}
