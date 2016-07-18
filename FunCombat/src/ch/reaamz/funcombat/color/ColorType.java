package ch.reaamz.funcombat.color;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public enum ColorType 
{
	BLACK(DyeColor.BLACK, ChatColor.BLACK + "Noir"),
	RED(DyeColor.RED, ChatColor.RED + "Rouge"),
	GREEN(DyeColor.GREEN, ChatColor.DARK_GREEN + "Vert"),
	BROWN(DyeColor.BROWN, ChatColor.RED + "Marron"),
	BLUE(DyeColor.BLUE, ChatColor.DARK_BLUE + "Bleu"),
	PURPLE(DyeColor.PURPLE, ChatColor.LIGHT_PURPLE + "Mauve"),
	CYAN(DyeColor.CYAN, ChatColor.DARK_AQUA + "Cyan"),
	LIGHT_GRAY(DyeColor.SILVER, ChatColor.GRAY + "Gris claire"),
	GRAY(DyeColor.GRAY, ChatColor.GRAY + "Gris"),
	PINK(DyeColor.PINK, ChatColor.LIGHT_PURPLE + "Rose"),
	LIME(DyeColor.LIME, ChatColor.GREEN + "Vert claire"),
	YELLOW(DyeColor.YELLOW, ChatColor.YELLOW + "Jaune"),
	LIGHT_BLUE(DyeColor.LIGHT_BLUE, ChatColor.BLUE + "Bleu claire"),
	MAGENTA(DyeColor.MAGENTA, ChatColor.DARK_PURPLE + "Magenta"),
	ORANGE(DyeColor.ORANGE, ChatColor.GOLD + "Orange"),
	WHITE(DyeColor.WHITE, ChatColor.WHITE + "Blanc");
	
	private DyeColor color;
	private String caption;
	
	private ColorType(DyeColor color, String caption)
	{
		this.color = color;
		this.caption = caption;
	}
	
	public DyeColor getColor()
	{
		return this.color;
	}
	
	public String getCaption()
	{
		return this.caption;
	}
}
