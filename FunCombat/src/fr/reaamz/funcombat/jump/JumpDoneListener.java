package fr.reaamz.funcombat.jump;

import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.event.JumpDoneEvent;

public class JumpDoneListener implements Listener
{
	@EventHandler
	public void onJumpDone(JumpDoneEvent event)
	{
		Player player = event.getPlayer();
		
		Utils.sendMessageAllPlayers("Player " + player.getName() + " just finished " + event.getJumpName() + " in " + event.getTime() + " secondes(s)");
		
		try
		{
			int tent = Integer.parseInt(FunCombat.database.getTent(event.getJumpName(), player.getUniqueId()));
			int last = Integer.parseInt(FunCombat.database.getLastTime(event.getJumpName(), player.getUniqueId()));
			int best = Integer.parseInt(FunCombat.database.getBestTime(event.getJumpName(), player.getUniqueId()));
			
			FunCombat.database.updateTime(player.getUniqueId(), event.getJumpName(), tent, last, best);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
