package fr.reaamz.funcombat.player;

import org.bukkit.entity.Player;

import fr.reaamz.funcombat.title.TabTitle;
import fr.reaamz.funcombat.title.Title;
import fr.reaamz.funcombat.title.TitleType;

public class FCPlayer 
{
	private Player player;
	private int coins;
		
	public FCPlayer(Player player) 
	{
		this.player = player;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public void sendWelcomeTitleMessage()
	{
		Title.sendType(this.player, TitleType.LOGIN);
		TabTitle.sendTabTitle(this.player, "$eBienvenue sur FunCombat !", "$4Have Fun !");
	}
	
	public void sendRawTitle(int fadeIn, int stayTime, int fadeOut, String title, String subtitle)
	{
		Title.send(this.player, fadeIn, stayTime, fadeOut, title, subtitle);
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
