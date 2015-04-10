package fr.reaamz.funcombat.metamorphoses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
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
	
	private void spawnNewSheep(DyeColor color,Player player)
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
	
	public void spawnNewEntity(Player player, Class<? extends Entity> clazz)
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
				Method setPowered = c.getMethod("setPowered", boolean.class);
				setPowered.setAccessible(true);
				setPowered.invoke(ent, true);
			}
			
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10000000,1));
			
			entities.put(player, ent);
		} 
		catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
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
				Utils.sendCustomMessage(e.getPlayer(), ChatColor.RED + "Il y a eu un probl�me, s.v.p report au staff et essayer de deco-reco");
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
		
		if (e.getInventory().getTitle().contains("S�l�cteur de M�tamorphoses"))
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
						Utils.sendCustomMessage(player, ChatColor.RED + "Votre couleur pr�f�r�e n'est pas d�finie ! Veuillez choisir votre couleur pr�f�r�e, via le menu pr�vu � cet effet, dans le menu principal.");
					}
					else
					{
						if(!(entities.containsKey(player)))
						{
							spawnNewSheep(dColor, player);
							Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voil� transform� en mouton !");
						}
						else
						{
							Utils.sendCustomMessage(player, ChatColor.RED + "Vous �tes d�j� M�tamorphos� !");
						}
						player.closeInventory();
					}					
				}
				
				if(e.getCurrentItem().getType().equals(Material.SULPHUR))
				{
					if(!(entities.containsKey(player)))
					{
						spawnNewEntity(player, Creeper.class);
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voil� transform� en creeper !");
					}
					else
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Vous �tes d�j� M�tamorphos� !");
					}
					player.closeInventory();				
				}
				
				if(e.getCurrentItem().getType().equals(Material.LEATHER))
				{
					if(!(entities.containsKey(player)))
					{
						spawnNewEntity(player, Cow.class);
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voil� transform� en vache !");
					}
					else
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Vous �tes d�j� M�tamorphos� !");
					}
					player.closeInventory();				
				}
				
				if(e.getCurrentItem().getType().equals(Material.ROTTEN_FLESH))
				{
					if(!(entities.containsKey(player)))
					{
						spawnNewEntity(player, Giant.class);
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voil� transform� en g�ant !");
					}
					else
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Vous �tes d�j� M�tamorphos� !");
					}
					player.closeInventory();
				}
				
				if(e.getCurrentItem().getType().equals(Material.FEATHER))
				{
					if(!(entities.containsKey(player)))
					{
						spawnNewEntity(player, Chicken.class);
						Utils.sendCustomMessage(player, ChatColor.GREEN + "Vous voil� transform� en poulet !");
					}
					else
					{
						Utils.sendCustomMessage(player, ChatColor.RED + "Vous �tes d�j� M�tamorphos� !");
					}
					player.closeInventory();
				}
				
				if(e.getCurrentItem().getType().equals(Material.THIN_GLASS))
				{
					if (entities.containsKey(player))
					{
						Entity ent = entities.get(player);
						
						ent.remove();
						Utils.sendCustomMessage(player,ChatColor.GREEN + "Votre M�tamorphose � �t� supprim�e");
						
						entities.remove(player);
						Utils.removeAllPotionEffects(player);
						
						player.closeInventory();
					}
					else
					{
						Utils.sendCustomMessage(player,ChatColor.RED + "Aucune M�tamorphose � supprimer");
						player.closeInventory();
					}
				}
			}	
		}
	}
}
