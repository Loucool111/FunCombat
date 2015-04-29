package fr.reaamz.funcombat.kitpvp;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.google.common.collect.Maps;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.kitpvp.KitpvpKits.Kits;

public class Kitpvp implements Listener
{
	private HashMap<Player, HashMap<Kits, Integer>> KitLevels = Maps.newHashMap();
	
	private HashMap<Player, String> playerKit = Maps.newHashMap();
	
	private HashMap<Player, Score> ScoreKills = Maps.newHashMap(); 
	private HashMap<Player, Score> ScoreNiv = Maps.newHashMap();
	
	private HashMap<Player, Scoreboard> scs = Maps.newHashMap();
	
	public Kitpvp()
	{
		Bukkit.getPluginManager().registerEvents(this, FunCombat.instance);
	}
	
	private void registerPass(Player player, Kits type, int level)
	{
		Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
				
		Objective object = sc.registerNewObjective(type.toStringKit(), "dummy");
		
		object.setDisplayName(ChatColor.GREEN + "Kit : " + type.toStringKit());
		object.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Score kills = object.getScore(ChatColor.AQUA + "Kills");
		Score niv = object.getScore(ChatColor.AQUA + "Niveau");
		
		ScoreKills.put(player, kills);
		ScoreNiv.put(player, niv);
		
		ScoreKills.get(player).setScore(level);
		ScoreNiv.get(player).setScore(KitpvpUtils.getLevelFromKills(player, level));
		
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
				name = scs.get(player).getObjective(type.toStringKit()).getName(); // on essaye de recup le nom de l'objective
			}
			catch (IllegalArgumentException | NullPointerException ex) {}
			
			if (name == type.toStringKit())
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
		playerKit.put(player, type.toStringKit());
		
		Utils.ClearInventoryAndPotionEffects(player);
		
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
				if (kit.toStringKit().equals(playerKit.get(player)))
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
					if (playerKit.get(killer).equals(kit.toStringKit()))
					{
						if (KitLevels.get(killer).containsKey(kit))
						{
							KitLevels.get(killer).put(kit, KitLevels.get(killer).get(kit) + 1);
							
							ScoreKills.get(killer).setScore(KitLevels.get(killer).get(kit));
							
							ScoreNiv.get(killer).setScore(KitpvpUtils.getLevelFromKills(killer, KitLevels.get(killer).get(kit)));
							
							if (KitLevels.get(killer).get(kit) == 5||KitLevels.get(killer).get(kit) == 10||KitLevels.get(killer).get(kit) == 20||KitLevels.get(killer).get(kit) == 50)
							{
								Utils.ClearInventoryAndPotionEffects(killer);
								KitpvpKits.equipKitArmor(killer, KitLevels.get(killer).get(kit), kit);
								KitpvpKits.equipKitStuff(killer, KitLevels.get(killer).get(kit), kit);
							}
						}
					}
				}
				
				if (playerKit.containsKey(victim))
				{
					if (playerKit.get(victim).equals(kit.toStringKit()))
					{
						if (KitLevels.get(victim).containsKey(kit))
						{
							if (KitLevels.get(victim).get(kit) > 1)
							{
								KitLevels.get(victim).put(kit, KitLevels.get(victim).get(kit) - 1);
								
								ScoreKills.get(victim).setScore(KitLevels.get(victim).get(kit));
								
								ScoreNiv.get(victim).setScore(KitpvpUtils.getLevelFromKills(victim, KitLevels.get(victim).get(kit)));
							
								if (KitLevels.get(victim).get(kit) == 5||KitLevels.get(victim).get(kit) == 10||KitLevels.get(victim).get(kit) == 20||KitLevels.get(victim).get(kit) == 50)
								{
									Utils.ClearInventoryAndPotionEffects(victim);
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
}
