package fr.reaamz.funcombat.hub;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.reaamz.funcombat.FunCombat;

public class TestListeners implements Listener
{
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)	
	{
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType() == Material.LEVER)
			{				
				Bukkit.getMessenger().registerOutgoingPluginChannel(FunCombat.instance, "BungeeCord");
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				
				out.writeUTF("Connect");
				out.writeUTF("Minigame");
				
				event.getPlayer().sendPluginMessage(FunCombat.instance, "BungeeCord", out.toByteArray());
			}
		}
	}
}
