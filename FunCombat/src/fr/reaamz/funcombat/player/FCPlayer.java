package fr.reaamz.funcombat.player;

import me.confuser.barapi.BarAPI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.grades.GradeType;
import fr.reaamz.funcombat.title.TabTitle;
import fr.reaamz.funcombat.title.Title;
import fr.reaamz.funcombat.title.TitleType;

/**
 * Represents a FunCombatPlayer 
 */
public class FCPlayer 
{
	private Player player;
	private int coins;
	private GradeType grade;
		
	public FCPlayer(Player player) 
	{
		this.player = player;
	}
	
	public void loadPlayer(Player player)
	{
		this.player = player;
		//TODO READ FROM FILES
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
		TabTitle.sendTabTitle(this.player, "$eBienvenue sur FunCombat !", "$4Have Fun !");
		setTitleBar(ChatColor.YELLOW + "Bienvenue sur FunCombat ! , " + ChatColor.RED + this.player.getName());
	}
	
	public void sendRawTitle(int fadeIn, int stayTime, int fadeOut, String title, String subtitle)
	{
		Title.send(this.player, fadeIn, stayTime, fadeOut, title, subtitle);
	}
	
	public void setTitleBar(String message)
	{
		BarAPI.setMessage(this.player, message);
	}
	
	public void sentToServer(String serverName)	
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		
		out.writeUTF("Connect");
		out.writeUTF(serverName);
		
		this.player.sendPluginMessage(FunCombat.instance, "BungeeCord", out.toByteArray());
	}
	
	public void setGrade(GradeType type)
	{
		this.grade = type;
	}
	
	public GradeType getGrade()
	{
		return this.grade;
	}
	
	public int getCoins()
	{
		return this.coins;
	}
	
	public void setCoins(int coins)
	{
		this.coins = coins;
	}
	
	public void addCoins(int coinsToAdd)
	{
		this.coins += coinsToAdd;
	}
}
