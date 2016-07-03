package fr.reaamz.funcombat.metamorphoses;

import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Sheep;

public enum MetamorphType 
{
	SHEEP(Sheep.class, Material.WOOL, "mouton", true, "sheepMethod"),
	COW(Cow.class, Material.LEATHER, "vache"),
	CREEPER(Creeper.class, Material.SULPHUR, "creeper", true, "creeperMethod"),
	GIANT(Giant.class, Material.ROTTEN_FLESH, "géant"),
	CHICKEN(Chicken.class, Material.FEATHER, "poulet"),
	HORSE(Horse.class, Material.SADDLE, "cheval", true, "horseMethod"),
	PIG(Pig.class, Material.PORK, "cochon"),
	ZOMBIE_PIGMAN(PigZombie.class, Material.GOLD_NUGGET, "zombie pigman", true, "pigzombieMethod"),
	;
	
	private Class<? extends Entity> clazz;
	private Material item;
	private String text;
	private boolean hasCustomCode;
	private String methodName;
	
	private MetamorphType(Class<? extends Entity> clazz, Material item, String text) 
	{
		this(clazz, item, text, false, null);
	}
	
	private MetamorphType(Class<? extends Entity> clazz, Material item, String text, boolean hasCustomCode, String methodName) 
	{
		this.clazz = clazz;
		this.item = item;
		this.text = text;
		this.hasCustomCode = hasCustomCode;
		this.methodName = methodName;
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
	
	public String getMethodName()
	{
		return this.methodName;
	}
}
