package fr.reaamz.funcombat.player;

import org.bukkit.entity.Player;

public class FCPlayerManager 
{
	public static FCPlayer loadPlayer(Player player)
	{
		//TODO stocker les infos dans le futur
		
		return new FCPlayer(player);
	}
}
