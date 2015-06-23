package fr.reaamz.funcombat.title;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R1.PlayerConnection;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title 
{
	public static final int defaultFadeIn = 20;
	public static final int defautStayTime = 100;
	public static final int defaultFadeOut = 20;
	
	/** 
	 * @param player Le player à qui envoyer le title
	 * @param fadeIn Integer représentant la durée d'apparition du texte
	 * @param stayTime Le temps que le message reste à l'écran
	 * @param fadeOut Integer représentant la durée de disparition du texte
	 * @param title Le titre à afficher
	 * @param subtitle Le sous titre à affficher (peut être null)
	 */
	public static void send(Player player, int fadeIn, int stayTime, int fadeOut, String title, String subtitle)
	{
		PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
		
		PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 10, 50, 10);
		connection.sendPacket(packetPlayOutTimes);
		
		if (subtitle != null) {
            subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
            subtitle = ChatColor.translateAlternateColorCodes('$', subtitle);
            IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, titleSub);
            connection.sendPacket(packetPlayOutSubTitle);
        }

        if (title != null) {
            title = title.replaceAll("%player%", player.getDisplayName());
            title = ChatColor.translateAlternateColorCodes('$', title);
            IChatBaseComponent titleMain = ChatSerializer.a("{\"text\": \"" + title + "\"}");
            PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleMain);
            connection.sendPacket(packetPlayOutTitle);            
        }
	}
	
	public static void sendType(Player player, TitleType type)
	{
		if(type == TitleType.LOGIN)
		{
			send(player, defaultFadeIn, defautStayTime, defaultFadeOut, "$eBienvenue", "$4Sur FunCombat !");
		}
	}
}
