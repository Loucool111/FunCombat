package fr.reaamz.funcombat.player;

import me.confuser.barapi.BarAPI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.title.TabTitle;
import fr.reaamz.funcombat.title.Title;
import fr.reaamz.funcombat.title.TitleType;

/**
 * Represents a FunCombatPlayer 
 */
public class FCPlayer 
{
	private Player player;
		
	public FCPlayer(Player player) 
	{
		this.player = player;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public void sendWelcomeTitle()
	{
		Title.sendType(this.player, TitleType.LOGIN);		
	}
	
	public void sendWelcomeMessages()
	{
		TabTitle.sendTabTitle(this.player, "$eVous êtes sur " + Utils.PLUGIN_NAME + " !", "$4Have Fun !");
		setTitleBar(ChatColor.YELLOW + "Bienvenue sur " + Utils.PLUGIN_NAME + " !");
	}
	
	public void sendRawTitle(int fadeIn, int stayTime, int fadeOut, String title, String subtitle)
	{
		Title.send(this.player, fadeIn, stayTime, fadeOut, title, subtitle);
	}
	
	public void setTitleBar(String message)
	{
		BarAPI.setMessage(this.player, message);
	}
	
	public void sendToServer(String serverName)	
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		
		out.writeUTF("Connect");
		out.writeUTF(serverName);
		
		this.player.sendPluginMessage(FunCombat.instance, "BungeeCord", out.toByteArray());
	}
}
