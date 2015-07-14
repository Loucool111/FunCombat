package fr.reaamz.funcombat.jump;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.event.JumpDoneEvent;

public class JumpDoneListener implements Listener
{
	@EventHandler
	public void onJumpDone(JumpDoneEvent event)
	{
		Player player = event.getPlayer();
		
		Utils.sendMessageAllPlayers("Player " + player.getName() + " just finished " + event.getJumpName() + " in " + event.getTime() + " secondes(s)");
	}
}
