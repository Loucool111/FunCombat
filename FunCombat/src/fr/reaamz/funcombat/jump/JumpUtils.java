package fr.reaamz.funcombat.jump;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JumpUtils
{	
	public static ItemStack[] getMenuItems()
	{
		ItemStack[] items = new ItemStack[27];
		
		ItemStack jump1 = new ItemStack(Material.FEATHER);
		ItemMeta jump1meta = jump1.getItemMeta();
		
		jump1meta.setDisplayName(JumpNames.JUMP1.getItemName());
		jump1meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour essayer le " + JumpNames.JUMP1.getItemName()));
		
		jump1.setItemMeta(jump1meta);
		
		ItemStack jump2 = new ItemStack(Material.FEATHER);
		ItemMeta jump2meta = jump2.getItemMeta();
		
		jump2meta.setDisplayName(JumpNames.JUMP2.getItemName());
		jump2meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour essayer le " + JumpNames.JUMP2.getItemName()));
		
		jump2.setItemMeta(jump2meta);
		
		ItemStack jump3 = new ItemStack(Material.FEATHER);
		ItemMeta jump3meta = jump3.getItemMeta();
		
		jump3meta.setDisplayName(JumpNames.JUMP3.getItemName());
		jump3meta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour essayer le " + JumpNames.JUMP2.getItemName()));
		
		jump3.setItemMeta(jump3meta);
		
		ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
		ItemMeta bookmeta = book.getItemMeta();
		
		bookmeta.setDisplayName(ChatColor.GREEN + "Scores des Jumps");
		bookmeta.setLore(Arrays.asList(ChatColor.WHITE + "Cliquez pour accéder au Scores"));
		
		book.setItemMeta(bookmeta);
		
		items[3] = jump1;
		items[4] = jump2;
		items[5] = jump3;
		items[22] = book;
		
		return items;
	}
	
	public static Location getLocation(String rawLoc)
	{
		if (rawLoc == null) return null;
		
		rawLoc = rawLoc.substring(1, rawLoc.length() - 1);
		
		String[] items = rawLoc.split(", ");
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		Map<String, String> str = new HashMap<String, String>();
		
		for (String dItem : items)
		{
			String[] stra = dItem.split("=");
			str.put(stra[0], stra[1]);
		}
		
		map.put("world", "world");
		
		map.put("x", Double.valueOf(str.get("x")));
		map.put("y", Double.valueOf(str.get("y")));
		map.put("z", Double.valueOf(str.get("z")));
		
		map.put("pitch", Float.valueOf(str.get("pitch")));
		map.put("yaw", Float.valueOf(str.get("yaw")));
		
		return Location.deserialize(map);
	}
}
