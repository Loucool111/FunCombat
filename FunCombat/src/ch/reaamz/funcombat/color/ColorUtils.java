package ch.reaamz.funcombat.color;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

public class ColorUtils 
{	
	public static ItemStack[] getInventoryContentsForMenu()
	{
		ItemStack[] items = new ItemStack[18];
		
		int i = 0;
		
		for (ColorType type : ColorType.values())
		{
			Wool wool = new Wool(type.getColor());
			ItemStack stack = wool.toItemStack(1);
			ItemMeta meta = stack.getItemMeta();
			
			meta.setDisplayName(type.getCaption());
			meta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
			
			stack.setItemMeta(meta);
			
			items[i] = stack;
			
			++i;
		}
		
		return items;
	}
	
	public static DyeColor getDyeColorFromString(String color)
	{
		if (color.equals("black"))
			return DyeColor.BLACK;
		if (color.equals("red"))
			return DyeColor.RED;
		if (color.equals("green"))
			return DyeColor.GREEN;
		if (color.equals("brown"))
			return DyeColor.BROWN;
		if (color.equals("blue"))
			return DyeColor.BLUE;
		if (color.equals("purple"))
			return DyeColor.PURPLE;
		if (color.equals("cyan"))
			return DyeColor.CYAN;
		if (color.equals("silver"))
			return DyeColor.SILVER;
		if (color.equals("gray"))
			return DyeColor.GRAY;
		if (color.equals("pink"))
			return DyeColor.PINK;
		if (color.equals("lime"))
			return DyeColor.LIME;
		if (color.equals("yellow"))
			return DyeColor.YELLOW;
		if (color.equals("light_blue"))
			return DyeColor.LIGHT_BLUE;
		if (color.equals("magenta"))
			return DyeColor.MAGENTA;
		if (color.equals("orange"))
			return DyeColor.ORANGE;
		if (color.equals("white"))
			return DyeColor.WHITE;
		
		return null;
	}
}
