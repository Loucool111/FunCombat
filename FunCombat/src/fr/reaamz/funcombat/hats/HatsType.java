package fr.reaamz.funcombat.hats;

import org.bukkit.Material;

public enum HatsType 
{
	GLASS(Material.GLASS, "Bloc de verre", "un bloc de verre"),
	ENCHANT(Material.ENCHANTMENT_TABLE, "Table d'enchantement","une table d'enchantement");
	
	private Material type;
	private String text;
	private String caption;
	
	HatsType(Material type, String caption, String text) {
		this.type = type;
		this.text = text;
		this.caption = caption;
	}
	
	public Material getType() {
		return this.type;
	}
	
	public String getText() {
		return this.text;
	}
	
	public String getCaption() {
		return this.caption;
	}
}
