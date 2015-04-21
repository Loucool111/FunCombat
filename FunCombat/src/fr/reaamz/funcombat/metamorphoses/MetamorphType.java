package fr.reaamz.funcombat.metamorphoses;

import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Sheep;

public enum MetamorphType 
{
	SHEEP(Sheep.class, Material.WOOL, "mouton", true),
	COW(Cow.class, Material.LEATHER, "vache", false),
	CREEPER(Creeper.class, Material.SULPHUR, "creeper", false),
	GIANT(Giant.class, Material.ROTTEN_FLESH, "géant", false),
	CHICKEN(Chicken.class, Material.FEATHER, "poulet", false),
	HORSE(Horse.class, Material.SADDLE, "cheval", true),
	PIG(Pig.class, Material.PORK, "cochon", false),
	;
	
	private Class<? extends Entity> clazz;
	private Material item;
	private String text;
	private boolean hasCustomCode;
	
	private MetamorphType(Class<? extends Entity> clazz, Material item, String text, boolean hasCustomCode) 
	{
		this.clazz = clazz;
		this.item = item;
		this.text = text;
		this.hasCustomCode = hasCustomCode;
	}
	
	public Class<? extends Entity> getClassEntity()
	{
		return this.clazz;
	}
	
	public Material getItemMaterial()
	{
		return this.item;
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public boolean hasCustomCode()
	{
		return this.hasCustomCode;
	}
}
