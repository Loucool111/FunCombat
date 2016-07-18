package ch.reaamz.funcombat.kitpvp;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.google.common.collect.Maps;

import ch.reaamz.funcombat.FunCombat;
import ch.reaamz.funcombat.Utils;
import ch.reaamz.funcombat.kitpvp.KitpvpKits.Kits;

public class Kitpvp implements Listener
{
	private HashMap<Player, HashMap<Kits, Integer>> KitLevels = Maps.newHashMap();
	
	private HashMap<Player, String> playerKit = Maps.newHashMap();
	
	private HashMap<Player, Score> ScoreKills = Maps.newHashMap(); 
	private HashMap<Player, Score> ScoreNiv = Maps.newHashMap();
	
	private HashMap<Player, Scoreboard> scs = Maps.newHashMap();
	
	//---------------Kits Stuff----------------
	private HashMap<Player, Integer> kitSorciereTaskIds = Maps.newHashMap();
	//-----------------------------------------
	
	public Kitpvp()
	{
		Bukkit.getPluginManager().registerEvents(this, FunCombat.instance);
	}
	
	private void registerPass(Player player, Kits type, int level)
	{
		Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
				
		Objective object = sc.registerNewObjective(type.toString(), "dummy");
		
		object.setDisplayName(ChatColor.GREEN + FunCombat.localizer.locate("funcombat.kit.kit") + " " + type.toString());
		object.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Score kills = object.getScore(ChatColor.AQUA + FunCombat.localizer.locate("funcombat.kit.kills"));
		Score niv = object.getScore(ChatColor.AQUA + FunCombat.localizer.locate("funcombat.kit.level"));
		
		ScoreKills.put(player, kills);
		ScoreNiv.put(player, niv);
		
		ScoreKills.get(player).setScore(level);
		ScoreNiv.get(player).setScore(KitpvpUtils.getLevelFromKills(level));
		
		scs.put(player, sc);
		
		player.setScoreboard(sc);
	}
	
	private void registerPlayer(Player player, Kits type, int level)
	{
		if (scs.containsKey(player))
		{
			String name = null;
			
			try 
			{
				name = scs.get(player).getObjective(type.toString()).getName(); // on essaye de recup le nom de l'objective
			}
			catch (IllegalArgumentException | NullPointerException ex) {}
			
			if (name == type.toString())
			{
				player.setScoreboard(scs.get(player));
			}
			else
			{
				registerPass(player, type, level);
			}
		}
		else
		{
			registerPass(player, type, level);
		}
	}
	
	public void newKitpvpPlayer(Player player, Kits type)
	{
		playerKit.put(player, type.toString());
		
		Utils.clearInventoryAndPotionEffects(player);
		
		for (Kits kit : Kits.values())
		{
			if (kit.equals(type))
			{
				if (KitLevels.containsKey(player))
				{
					if (KitLevels.get(player).containsKey(kit))
					{
						KitpvpKits.equipKitArmor(player, KitLevels.get(player).get(kit), kit);
						KitpvpKits.equipKitStuff(player, KitLevels.get(player).get(kit), kit);
						
						registerPlayer(player, kit, KitLevels.get(player).get(kit));
					}
					else
					{
						HashMap<Kits, Integer> map = KitLevels.get(player);
						map.put(kit, 1);
						KitLevels.put(player, map);
						
						KitpvpKits.equipKitArmor(player, KitLevels.get(player).get(kit), kit);
						KitpvpKits.equipKitStuff(player, KitLevels.get(player).get(kit), kit);
						
						registerPlayer(player, kit, KitLevels.get(player).get(kit));
					}
				}
				else
				{
					HashMap<Kits, Integer> temp = Maps.newHashMap();
					temp.put(type, 1);
					
					KitLevels.put(player, temp);
					
					KitpvpKits.equipKitArmor(player, KitLevels.get(player).get(kit), kit);
					KitpvpKits.equipKitStuff(player, KitLevels.get(player).get(kit), kit);
					
					registerPlayer(player, kit, KitLevels.get(player).get(kit));
				}
			}
		}
	}
	
	@EventHandler
	public void onRespawnEvent(final PlayerRespawnEvent e)
	{
		Player player = e.getPlayer();
		
		if (playerKit.containsKey(player))
		{
			for (Kits kit : Kits.values())
			{
				if (kit.toString().equals(playerKit.get(player)))
				{
					newKitpvpPlayer(player, kit);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerQuit(final PlayerQuitEvent e)
	{
		if (playerKit.containsKey(e.getPlayer()))
		{
			playerKit.remove(e.getPlayer());
			e.getPlayer().getInventory().clear();
			e.getPlayer().getInventory().setArmorContents(null);
		}
	}
	
	private boolean checkScores(Player player, KitpvpKits.Kits kit)
	{
		return KitLevels.get(player).get(kit) == 5||KitLevels.get(player).get(kit) == 10||KitLevels.get(player).get(kit) == 20||KitLevels.get(player).get(kit) == 50;
	}
	
	@EventHandler
	public void onEntityDeath(final EntityDeathEvent ede)
	{
		if(ede.getEntity().getKiller() instanceof Player && ede.getEntity() instanceof Player)
		{
			Player killer = (Player)ede.getEntity().getKiller();
			Player victim = (Player)ede.getEntity();
			
			ede.getDrops().clear();
			
			for (Kits kit : Kits.values())
			{
				if (playerKit.containsKey(killer))
				{
					if (playerKit.get(killer).equals(kit.toString()))
					{
						if (KitLevels.get(killer).containsKey(kit))
						{
							KitLevels.get(killer).put(kit, KitLevels.get(killer).get(kit) + 1);
							
							ScoreKills.get(killer).setScore(KitLevels.get(killer).get(kit));
							
							ScoreNiv.get(killer).setScore(KitpvpUtils.getLevelFromKills(KitLevels.get(killer).get(kit)));
							
							if (checkScores(killer, kit))
							{
								Utils.clearInventoryAndPotionEffects(killer);
								KitpvpKits.equipKitArmor(killer, KitLevels.get(killer).get(kit), kit);
								KitpvpKits.equipKitStuff(killer, KitLevels.get(killer).get(kit), kit);
							}
						}
					}
				}
				
				if (playerKit.containsKey(victim))
				{
					if (playerKit.get(victim).equals(kit.toString()))
					{
						if (KitLevels.get(victim).containsKey(kit))
						{
							if (KitLevels.get(victim).get(kit) > 1)
							{
								KitLevels.get(victim).put(kit, KitLevels.get(victim).get(kit) - 1);
								
								ScoreKills.get(victim).setScore(KitLevels.get(victim).get(kit));
								
								ScoreNiv.get(victim).setScore(KitpvpUtils.getLevelFromKills(KitLevels.get(victim).get(kit)));
							
								if (checkScores(victim, kit))
								{
									Utils.clearInventoryAndPotionEffects(victim);
									KitpvpKits.equipKitArmor(victim, KitLevels.get(victim).get(kit), kit);
									KitpvpKits.equipKitStuff(victim, KitLevels.get(victim).get(kit), kit);
								}
							}
						}
					}
				}
			}
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	//Stick du kit sorcière
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		final Player player = event.getPlayer();
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) && playerKit.containsKey(player))
		{
			if (playerKit.get(player).equals(Kits.SORCIERE.toString()))
			{
				List<Entity> entList = player.getNearbyEntities(7, 7, 7);
				
				for (Entity ent : entList)
				{
					if (ent instanceof Player)
					{
						if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().getType().equals(Material.STICK))
						{
							if (Utils.getTargetPlayer(player) != null)
							{
								Player target = Utils.getTargetPlayer(player);
								
								if (playerKit.containsKey(target))
								{
									if (!(kitSorciereTaskIds.containsKey(player)))
									{
										target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0));
										target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,200,0));
										target.damage(2, player);
										target.playSound(target.getLocation(), Sound.ENTITY_BLAZE_HURT, 50F, 1F);
										Utils.sendCustomMessage(target, ChatColor.RED + FunCombat.localizer.locate("funcombat.kit.sorcierehit"));
									
										final String baseName = ChatColor.DARK_PURPLE + FunCombat.localizer.locate("funcombat.kit.witchstick");
									
										int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(FunCombat.instance, new Runnable() 
										{
											int counter = 20;
										
											@Override
											public void run()
											{
												if (counter > 0)
												{
													for (ItemStack item : player.getInventory().getContents())
													{
														if (item != null)
														{
															if (item.getType().equals(Material.STICK))
															{
																ItemMeta meta = item.getItemMeta();
																meta.setDisplayName(baseName + ChatColor.AQUA + " (" + counter + ")");
																item.setItemMeta(meta);
															}
														}
													}
												}
												else
												{
													Bukkit.getScheduler().cancelTask(kitSorciereTaskIds.get(player));
													
													kitSorciereTaskIds.remove(player);
													
													for (ItemStack item : player.getInventory().getContents())
													{
														if (item != null)
														{
															if (item.getType().equals(Material.STICK))
															{
																ItemMeta meta = item.getItemMeta();
																meta.setDisplayName(baseName);
																item.setItemMeta(meta);
															}
														}
													}
												}
												counter--;
											}
										}, 0L , 20L);
									
										kitSorciereTaskIds.put(player, id);									
									}
									else
									{
										Utils.sendCustomMessage(player, ChatColor.RED + FunCombat.localizer.locate("funcombat.kit.sorcierecooldown"));
									}
								}
							}
						}
					}
				}
			}		
		}		
	}
}