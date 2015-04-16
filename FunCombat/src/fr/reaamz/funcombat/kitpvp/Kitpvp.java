package fr.reaamz.funcombat.kitpvp;

import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.google.common.collect.Maps;

import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.event.HubEvent;

public class Kitpvp implements Listener
{
	Map<Player, Integer> ArcherLevels = Maps.newHashMap();
	Map<Player, Integer> GuerrierLevels = Maps.newHashMap();

	Map<Player, String> playerKit = Maps.newHashMap();
	
	Map<Player, Score> ScoreKills = Maps.newHashMap(); 
	Map<Player, Score> ScoreNiv = Maps.newHashMap();
	
	Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
	
	public Kitpvp(Plugin p)
	{
		Bukkit.getPluginManager().registerEvents(this, p);
	}
	
		
	private Objective registerArcher(Player player)
	{
		Objective object = null;
		
		Boolean found = false;
		Set<Objective> objs = sc.getObjectives();
		
		for (Objective a : objs)
		{
			if (a.getName().equalsIgnoreCase(player.getName()))
			{
				object = a;
				found = true;
			}
		}
		
		if(!(found))
		{
			object = sc.registerNewObjective(player.getName(), "dummy");		
			object.setDisplayName(ChatColor.GOLD + "Kit : Archer");
			object.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			
		}
		
		player.setScoreboard(sc);
		
		return object;
	}
	
	private Objective registerGuerrier(Player player)
	{
		Objective object = null;
		
		Boolean found = false;
		Set<Objective> objs = sc.getObjectives();
		
		for (Objective a : objs)
		{
			if (a.getName().equalsIgnoreCase(player.getName()))
			{
				object = a;
				found = true;
			}
		}
		
		if(!(found))
		{
			object = sc.registerNewObjective(player.getName(), "dummy");
			object.setDisplayName(ChatColor.GOLD + "Kit : Guerrier");
			object.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			
		}
		
		player.setScoreboard(sc);
		
		return object;
	}
	
	private void createScores(Objective obj, Player player, Map<Player, Integer> level)
	{
		Score kills = obj.getScore(ChatColor.AQUA + "Kills");
		Score niv = obj.getScore(ChatColor.AQUA + "Niveau");
		
		ScoreKills.put(player, kills);
		ScoreNiv.put(player, niv);
		
		ScoreKills.get(player).setScore(level.get(player));
		ScoreNiv.get(player).setScore(KitpvpUtils.getLevelFromKills(player, level));
	}
	
	public void getKitArcherComplet(Player player)
	{
		playerKit.put(player, "archer");
		
		Utils.ClearInventoryAndPotionEffects(player);

		if (ArcherLevels.containsKey(player))
		{
			KitpvpKits.equipKitArcherStuff(player,ArcherLevels.get(player));
			KitpvpKits.equipKitArcherArmor(player,ArcherLevels.get(player));
		}
		else
		{
			ArcherLevels.put(player, 1);
			KitpvpKits.equipKitArcherStuff(player,ArcherLevels.get(player));
			KitpvpKits.equipKitArcherArmor(player,ArcherLevels.get(player));
		}
		
		Objective archer = registerArcher(player);
		createScores(archer, player, ArcherLevels);
	}
	
	public void getKitGuerrierComplet(Player player)
	{
		playerKit.put(player, "guerrier");
		
		Utils.ClearInventoryAndPotionEffects(player);
		
		if (GuerrierLevels.containsKey(player))
		{
			KitpvpKits.equipKitGuerrierStuff(player,GuerrierLevels.get(player));
			KitpvpKits.equipKitGuerrierArmure(player,GuerrierLevels.get(player));
		}
		else
		{
			GuerrierLevels.put(player, 1);
			KitpvpKits.equipKitGuerrierStuff(player,GuerrierLevels.get(player));
			KitpvpKits.equipKitGuerrierArmure(player,GuerrierLevels.get(player));
		}
		
		Objective guerrier = registerGuerrier(player);
		createScores(guerrier, player, GuerrierLevels);
	}
	
	@EventHandler
	public void onHubEvent(HubEvent event)
	{
		/*
		Player player = event.getPlayer();
		Utils.sendCustomMessageAllPlayers("HubEvent passed, " + player.getName());
		*/
	}
	
	@EventHandler
	public void onRespawnEvent(final PlayerRespawnEvent e)
	{
		
		Player player = e.getPlayer();
		
		if (playerKit.containsKey(player))
		{
			if (playerKit.get(player) == "archer")
			{
				getKitArcherComplet(player);
			}
			if (playerKit.get(player) == "guerrier")
			{
				getKitGuerrierComplet(player);
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerQuit(final PlayerQuitEvent e)
	{
		if (playerKit.containsKey(e.getPlayer()))
		{
			playerKit.remove(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onEntityDeath(final EntityDeathEvent ede)
	{
		//if(ede.getEntity().getKiller() instanceof Player)
		if(ede.getEntity().getKiller() instanceof Player && ede.getEntity() instanceof Player)
		{
			Player killer = (Player)ede.getEntity().getKiller();
			Player victim = (Player)ede.getEntity();
			
			if (playerKit.containsKey(killer))
			{
				if(playerKit.get(killer).equalsIgnoreCase("archer"))			
				{
					ArcherLevels.put(killer, ArcherLevels.get(killer) + 1);

					ScoreKills.get(killer).setScore(ArcherLevels.get(killer));
										
					if (ArcherLevels.get(killer) == 5||ArcherLevels.get(killer) == 10||ArcherLevels.get(killer) == 20||ArcherLevels.get(killer) == 50)
					{
						
						
						Utils.ClearInventoryAndPotionEffects(killer);
						KitpvpKits.equipKitArcherStuff(killer,ArcherLevels.get(killer));
						KitpvpKits.equipKitArcherArmor(killer,ArcherLevels.get(killer));
					}
					
					ScoreNiv.get(killer).setScore(KitpvpUtils.getLevelFromKills(killer, ArcherLevels));
				}
				
				if(playerKit.get(killer).equalsIgnoreCase("guerrier"))
				{
					GuerrierLevels.put(killer, GuerrierLevels.get(killer) + 1);

					ScoreKills.get(killer).setScore(GuerrierLevels.get(killer));
					
					if(GuerrierLevels.get(killer) == 5||GuerrierLevels.get(killer) == 10||GuerrierLevels.get(killer) == 20||GuerrierLevels.get(killer) == 50)
					{												
						Utils.ClearInventoryAndPotionEffects(killer);
						KitpvpKits.equipKitGuerrierStuff(killer, GuerrierLevels.get(killer));
						KitpvpKits.equipKitGuerrierArmure(killer, GuerrierLevels.get(killer));
					}
					
					ScoreNiv.get(killer).setScore(KitpvpUtils.getLevelFromKills(killer, GuerrierLevels));
				}
			}
			
			//------------------------------------------------------------------------------------------------------------------------------
			
			if (playerKit.containsKey(victim))
			{
				if (playerKit.get(victim).equalsIgnoreCase("archer"))
				{
					if (ArcherLevels.get(victim) > 1)
					{
						ArcherLevels.put(victim, ArcherLevels.get(victim) - 1);

						ScoreKills.get(victim).setScore(ArcherLevels.get(victim));
						
						if (ArcherLevels.get(victim) == 1||ArcherLevels.get(victim) == 5||ArcherLevels.get(victim) == 10||ArcherLevels.get(victim) == 20||ArcherLevels.get(victim) == 50)
						{							
							Utils.ClearInventoryAndPotionEffects(victim);
							KitpvpKits.equipKitArcherStuff(victim,ArcherLevels.get(victim));
							KitpvpKits.equipKitArcherArmor(victim,ArcherLevels.get(victim));
						}
						
						ScoreNiv.get(victim).setScore(KitpvpUtils.getLevelFromKills(victim, ArcherLevels));
					}
				}
			
				if (playerKit.get(victim).equalsIgnoreCase("guerrier"))
				{
					if (GuerrierLevels.get(victim) > 1)
					{
						GuerrierLevels.put(victim, GuerrierLevels.get(victim) - 1);

						ScoreKills.get(victim).setScore(GuerrierLevels.get(victim));
						
						if(GuerrierLevels.get(victim) == 1||GuerrierLevels.get(victim) == 5||GuerrierLevels.get(victim) == 10||GuerrierLevels.get(victim) == 20||GuerrierLevels.get(victim) == 50)
						{							
							Utils.ClearInventoryAndPotionEffects(victim);
							KitpvpKits.equipKitGuerrierStuff(victim, GuerrierLevels.get(victim));
							KitpvpKits.equipKitGuerrierArmure(victim, GuerrierLevels.get(victim));
						}
						
						ScoreNiv.get(victim).setScore(KitpvpUtils.getLevelFromKills(victim, GuerrierLevels));
					}
				}
			}
		}
	}
}
