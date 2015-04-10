package fr.reaamz.funcombat.selectioncouleur;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

public class SelectionCouleurUtils 
{
	public static ItemStack[] getItemsForMenu()
	{
		Wool black = new Wool(DyeColor.BLACK);
		ItemStack blackWool = black.toItemStack(1);
		ItemMeta blackWoolMeta = blackWool.getItemMeta();
		blackWoolMeta.setDisplayName(ChatColor.BLACK + "Noir");
		blackWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		blackWool.setItemMeta(blackWoolMeta);
		//----------------------------
		Wool red = new Wool(DyeColor.RED);
		ItemStack redWool = red.toItemStack(1);
		ItemMeta redWoolMeta = redWool.getItemMeta();
		redWoolMeta.setDisplayName(ChatColor.RED + "Rouge");
		redWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		redWool.setItemMeta(redWoolMeta);
		//----------------------------
		Wool green = new Wool(DyeColor.GREEN);
		ItemStack greenWool = green.toItemStack(1);
		ItemMeta greenWoolMeta = greenWool.getItemMeta();
		greenWoolMeta.setDisplayName(ChatColor.DARK_GREEN + "Vert");
		greenWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		greenWool.setItemMeta(greenWoolMeta);
		//----------------------------
		Wool brown = new Wool(DyeColor.BROWN);
		ItemStack brownWool = brown.toItemStack(1);
		ItemMeta brownWoolMeta = brownWool.getItemMeta();
		brownWoolMeta.setDisplayName(ChatColor.DARK_RED + "Marron");
		brownWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		brownWool.setItemMeta(brownWoolMeta);
		//----------------------------
		Wool blue = new Wool(DyeColor.BLUE);
		ItemStack blueWool = blue.toItemStack(1);
		ItemMeta blueWoolMeta = blueWool.getItemMeta();
		blueWoolMeta.setDisplayName(ChatColor.DARK_BLUE + "Bleu");
		blueWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		blueWool.setItemMeta(blueWoolMeta);
		//----------------------------
		Wool purple = new Wool(DyeColor.PURPLE);
		ItemStack purpleWool = purple.toItemStack(1);
		ItemMeta purpleWoolMeta = purpleWool.getItemMeta();
		purpleWoolMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Mauve");
		purpleWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		purpleWool.setItemMeta(purpleWoolMeta);
		//----------------------------
		Wool cyan = new Wool(DyeColor.CYAN);
		ItemStack cyaneWool = cyan.toItemStack(1);
		ItemMeta cyanWoolMeta = cyaneWool.getItemMeta();
		cyanWoolMeta.setDisplayName(ChatColor.DARK_AQUA + "Cyan");
		cyanWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		cyaneWool.setItemMeta(cyanWoolMeta);
		//----------------------------
		Wool lgray = new Wool(DyeColor.SILVER);
		ItemStack lgrayWool = lgray.toItemStack(1);
		ItemMeta lgrayWoolMeta = lgrayWool.getItemMeta();
		lgrayWoolMeta.setDisplayName(ChatColor.GRAY + "Gris Clair");
		lgrayWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		lgrayWool.setItemMeta(lgrayWoolMeta);
		//----------------------------
		Wool gray = new Wool(DyeColor.GRAY);
		ItemStack grayWool = gray.toItemStack(1);
		ItemMeta grayWoolMeta = grayWool.getItemMeta();
		grayWoolMeta.setDisplayName(ChatColor.GRAY + "Gris");
		grayWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		grayWool.setItemMeta(grayWoolMeta);
		//----------------------------
		Wool pink = new Wool(DyeColor.PINK);
		ItemStack pinkWool = pink.toItemStack(1);
		ItemMeta pinkWoolMeta = pinkWool.getItemMeta();
		pinkWoolMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Rose");
		pinkWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		pinkWool.setItemMeta(pinkWoolMeta);
		//----------------------------
		Wool lime = new Wool(DyeColor.LIME);
		ItemStack limeWool = lime.toItemStack(1);
		ItemMeta limeWoolMeta = limeWool.getItemMeta();
		limeWoolMeta.setDisplayName(ChatColor.GREEN + "Vert claire");
		limeWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		limeWool.setItemMeta(limeWoolMeta);
		//----------------------------
		Wool yellow = new Wool(DyeColor.YELLOW);
		ItemStack yellowWool = yellow.toItemStack(1);
		ItemMeta yellowWoolMeta = yellowWool.getItemMeta();
		yellowWoolMeta.setDisplayName(ChatColor.YELLOW + "Jaune");
		yellowWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		yellowWool.setItemMeta(yellowWoolMeta);
		//----------------------------
		Wool lblue = new Wool(DyeColor.LIGHT_BLUE);
		ItemStack lblueWool = lblue.toItemStack(1);
		ItemMeta lblueWoolMeta = lblueWool.getItemMeta();
		lblueWoolMeta.setDisplayName(ChatColor.BLUE + "Bleu claire");
		lblueWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		lblueWool.setItemMeta(lblueWoolMeta);
		//----------------------------
		Wool magenta = new Wool(DyeColor.MAGENTA);
		ItemStack magentaWool = magenta.toItemStack(1);
		ItemMeta magentaWoolMeta = magentaWool.getItemMeta();
		magentaWoolMeta.setDisplayName(ChatColor.DARK_PURPLE + "Magenta");
		magentaWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		magentaWool.setItemMeta(magentaWoolMeta);
		//----------------------------
		Wool orange = new Wool(DyeColor.ORANGE);
		ItemStack orangeWool = orange.toItemStack(1);
		ItemMeta orangeWoolMeta = orangeWool.getItemMeta();
		orangeWoolMeta.setDisplayName(ChatColor.GOLD + "Orange");
		orangeWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		orangeWool.setItemMeta(orangeWoolMeta);
		//----------------------------
		Wool white = new Wool(DyeColor.WHITE);
		ItemStack whiteWool = white.toItemStack(1);
		ItemMeta whiteWoolMeta = whiteWool.getItemMeta();
		whiteWoolMeta.setDisplayName(ChatColor.WHITE + "Blanc");
		whiteWoolMeta.setLore(Arrays.asList(ChatColor.WHITE + "Clic pour choisir la couleur !"));
		whiteWool.setItemMeta(whiteWoolMeta);
		//----------------------------
		
		ItemStack[] items = new ItemStack[18];
		
		items[0] = blackWool;
		items[1] = redWool;
		items[2] = greenWool;
		items[3] = brownWool;
		items[4] = blueWool;
		items[5] = purpleWool;
		items[6] = cyaneWool;
		items[7] = lgrayWool;
		items[8] = grayWool;
		items[9] = pinkWool;
		items[10] = limeWool;
		items[11] = yellowWool;
		items[12] = lblueWool;
		items[13] = magentaWool;
		items[14] = orangeWool;
		items[15] = whiteWool;
		
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
