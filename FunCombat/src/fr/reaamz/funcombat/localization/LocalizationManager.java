package fr.reaamz.funcombat.localization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.bukkit.ChatColor;

import com.google.common.collect.Maps;

public class LocalizationManager
{
	private HashMap<String, String> texts = Maps.newHashMap();
	
	private BufferedReader br;
	
	public LocalizationManager(String localizationFileName) throws URISyntaxException, IOException
	{	
		br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/" + localizationFileName)));
		
		setup();
		
		br.close();
	}
	
	public String locate(String key)
	{
		if (key.equals(null) || key.equals(""))
			return ChatColor.RED + "KEY NOT FOUND";
		
		String text = texts.get(key.toLowerCase());
		
		if (text == null)
			return ChatColor.RED + "VALUE NOT FOUND";
					
		text = ChatColor.translateAlternateColorCodes('$', text);
		
		return text;
	}
	
	private void setup() throws IOException
	{
		String line;
		while ((line = br.readLine()) != null)
		{
			if (!(line.startsWith("#")))
			{
				String[] temp = line.split("=");
				try
				{
					texts.put(temp[0].toLowerCase(), temp[1]);
				}
				catch (ArrayIndexOutOfBoundsException e){}
			}
		}
	}
}
