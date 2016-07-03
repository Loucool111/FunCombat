package fr.reaamz.funcombat.otherlisteners;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class BungeeMessagesListener implements PluginMessageListener
{
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) 
	{
		if (!channel.equals("BungeeCord")) 
		{
		      return;
		}
		
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		
		String recieve = in.readUTF();
		if (recieve.equals(""))
		{
			//do stuff
		}
	}
}