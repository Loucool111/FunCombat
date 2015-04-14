package fr.reaamz.funcombat.chat;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class JSONChat 
{
	/**
	 * CELUI CI EST BASIQUE
	 * 
	 * @param player le player
	 * @param debutMessage le message
	 */
	public static void sendMessage(Player player, String debutMessage)
	{
		player.sendMessage(debutMessage);
	}
	
	/**
	 * CELUI CI EST POUR LE CLIC UNIQUEMENT : {@link ChatUtils}
	 * 
	 * @param player le player
	 * @param debutMessage le début du message
	 * @param partieEvent la partie qui as les events
	 * @param clicAction Peut etre : {@link ChatUtils.ClicActions}
	 * @param valueAction la value de l'action
	 */
	public static void sendClicMessage(Player player, String debutMessage, String partieEvent,ChatUtils.ClicActions clicAction, String valueAction)
	{
		IChatBaseComponent comp  = ChatSerializer.a(ChatUtils.COMP_BASE + debutMessage + ChatUtils.COMP_EXTRA + partieEvent + ChatUtils.COMP_CLIC + clicAction.getTypeString() + ChatUtils.COMP_CLIC2 + valueAction + ChatUtils.COMP_CLICEND);
		
		PacketPlayOutChat packet = new PacketPlayOutChat(comp);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
	
	/**
	 * CELUI CI EST POUR LE HOVER UNIQUEMENT : {@link ChatUtils}
	 * 
	 * @param player la player
	 * @param debutMessage le message
	 * @param partieEvent la partie avec hover
	 * @param hoverAction Peut etre :  {@link ChatUtils.HoverActions}
	 * @param valueAction la valeur de l'action
	 */
	public static void sendHoverMessage(Player player, String debutMessage, String partieEvent, ChatUtils.HoverActions hoverAction, String valueAction)
	{
		IChatBaseComponent comp  = ChatSerializer.a(ChatUtils.COMP_BASE + debutMessage + ChatUtils.COMP_EXTRA + partieEvent + ChatUtils.COMP_HOVER + hoverAction.getTypeString() + ChatUtils.COMP_HOVER2 + valueAction + ChatUtils.COMP_HOVEREND);
		
		PacketPlayOutChat packet = new PacketPlayOutChat(comp);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
	
	/**
	 * CELUI CI EST POUR LE HOVER AVEC LE CLIC : {@link ChatUtils}
	 * 
	 * @param player la player
	 * @param debutMessage le message
	 * @param partieEvent la partie avec event
	 * @param clicAction Peut etre :  {@link ChatUtils.ClicActions}
	 * @param valueClic la valeur du clic
	 * @param hoverAction Peut etre :  {@link ChatUtils.HoverActions}
	 * @param valueHover la valeur du hover
	 */
	public static void sendClicHoverEvent(Player player, String debutMessage, String partieEvent, ChatUtils.ClicActions clicAction, String valueClic, ChatUtils.HoverActions hoverAction, String valueHover)
	{
		IChatBaseComponent comp  = ChatSerializer.a(ChatUtils.COMP_BASE + debutMessage + ChatUtils.COMP_EXTRA + partieEvent + ChatUtils.COMP_BOTH + clicAction.getTypeString() + ChatUtils.COMP_BOTH2 + valueClic + ChatUtils.COMP_BOTHEND + ChatUtils.COMP_BOTH3 + hoverAction.getTypeString() + ChatUtils.COMP_BOTH4 + valueHover + ChatUtils.COMP_BOTHEND2);
		
		PacketPlayOutChat packet = new PacketPlayOutChat(comp);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
}
