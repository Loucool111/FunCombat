package fr.reaamz.funcombat.hub;

import me.confuser.barapi.BarAPI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
import fr.reaamz.funcombat.title.TabTitle;
import fr.reaamz.funcombat.title.Title;
import fr.reaamz.funcombat.title.TitleType;

public class HubWelcome
{
	public static void sendWelcomeTitle(Player player)
	{
		Title.sendType(player, TitleType.LOGIN);
	}
	
	public static void sendWelcomeMessages(Player player)
	{
		TabTitle.sendTabTitle(player, FunCombat.localizer.locate("funcombat.youareon") + " " + Utils.PLUGIN_NAME + " !", FunCombat.localizer.locate("funcombat.havefun"));
		setTitleBar(player, ChatColor.YELLOW + FunCombat.localizer.locate("funcombat.welcomeon") + " " + Utils.PLUGIN_NAME + " !");
	}
	
	public static void setTitleBar(Player player, String message)
	{
		BarAPI.setMessage(player, message);
	}
	
	public static void sendRawTitle(Player player, int fadeIn, int stayTime, int fadeOut, String title, String subtitle)
	{
		Title.send(player, fadeIn, stayTime, fadeOut, title, subtitle);
	}
}
