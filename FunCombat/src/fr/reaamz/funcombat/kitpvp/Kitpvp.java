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
import fr.reaamz.funcombat.event.HubEvent;
import fr.reaamz.funcombat.kitpvp.KitpvpKits.Kits;

public class Kitpvp implements Listener
{
	//----------------------------------------------------------
	HashMap<Player, Integer> ArcherLevels = Maps.newHashMap();
	HashMap<Player, Integer> GuerrierLevels = Maps.newHashMap();

	//----------------------------------------------------------
	
	HashMap<Player, String> playerKit = Maps.newHashMap();
	
	HashMap<Player, Score> ScoreKills = Maps.newHashMap(); 
	HashMap<Player, Score> ScoreNiv = Maps.newHashMap();
	
	HashMap<Player, Scoreboard> scs = Maps.newHashMap();
	
	public Kitpvp()
	{
		Bukkit.getPluginManager().registerEvents(this, FunCombat.instance);
	}
	
	private void registerPass(Player player, KitpvpKits.Kits type, HashMap<Player, Integer> level)
	{
		Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
				
		Objective object = sc.registerNewObjective(type.toStringKit(), "dummy");
		
		object.setDisplayName(ChatColor.GREEN + "Kit : " + type.toStringKit());
		object.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Score kills = object.getScore(ChatColor.AQUA + "Kills");
		Score niv = object.getScore(ChatColor.AQUA + "Niveau");
		
		ScoreKills.put(player, kills);
		ScoreNiv.put(player, niv);
		
		ScoreKills.get(player).setScore(level.get(player));
		ScoreNiv.get(player).setScore(KitpvpUtils.getLevelFromKills(player, level));
		
		scs.put(player, sc);
		
		player.setScoreboard(sc);
	}
	
	private void registerPlayer(Player player, KitpvpKits.Kits type, HashMap<Player, Integer> level)
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
	
	public void newKitpvpPlayer(Player player, KitpvpKits.Kits type)
	{
		playerKit.put(player, type.toStringKit());
		
		Utils.ClearInventoryAndPotionEffects(player);
		
		if (type.toStringKit().equals(KitpvpKits.Kits.ARCHER.toStringKit()))
		{
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
			
			registerPlayer(player, type, ArcherLevels);
		}
		
		if (type.toStringKit().equals(KitpvpKits.Kits.GUERRIER.toStringKit()))
		{
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
			
			registerPlayer(player, type, GuerrierLevels);
		}
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
			if (playerKit.get(player) == Kits.ARCHER.toStringKit())
			{
				newKitpvpPlayer(player, Kits.ARCHER);
				//ON NE DOIT PAS RECREER LE PLAYER MAIS JUSTE LUI DIMINUER SON SCORE ET REEQUIPPER SON STUFF
			}
			if (playerKit.get(player) == Kits.GUERRIER.toStringKit())
			{
				newKitpvpPlayer(player, Kits.GUERRIER);
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
			
			ede.getDrops().clear();
			
			if (playerKit.containsKey(killer))
			{				
				if(playerKit.get(killer).equals(KitpvpKits.Kits.ARCHER))			
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
				
				if(playerKit.get(killer).equals(KitpvpKits.Kits.GUERRIER))
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
				if (playerKit.get(victim).equals(KitpvpKits.Kits.ARCHER))
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
			
				if (playerKit.get(victim).equals(KitpvpKits.Kits.GUERRIER))
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
