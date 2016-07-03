package fr.reaamz.funcombat.hats;

import org.bukkit.Material;

public enum HatsType 
{
	GLASS(Material.GLASS, "Bloc de verre", "un bloc de verre"),
	ENCHANT(Material.ENCHANTMENT_TABLE, "Table d'enchantement","une table d'enchantement"),
	DIAMOND_ORE(Material.DIAMOND_ORE,"Minerai de diamant","un minerai de diamant"),
	BANNER(Material.BANNER,"Bannière","une bannière"),
	CHEST(Material.CHEST, "Coffre","un coffre"),
	CRAFT_TABLE(Material.WORKBENCH,"Table de craft","une table de craft"),
	FURNACE(Material.FURNACE,"Four","un four"),
	JUKEBOX(Material.JUKEBOX,"Jukebox","un jukebox"),
	SPONGE(Material.SPONGE,"Eponge","une éponge"),
	SEA_LANTERN(Material.SEA_LANTERN,"Sea Lantern","une sea lantern"),
	HAY_BALE(Material.HAY_BLOCK,"Bloc de foin", "une bloc de foin"),
	END_STONE(Material.ENDER_STONE,"Pierre de l'end","de la pierre de l'end"),
	SOUL_SAND(Material.SOUL_SAND,"Soul sand","de la soul sand"),
	BOOKSHELF(Material.BOOKSHELF,"Bibliothèque", "une bibliothèque"),
	JACK_OLANTERN(Material.JACK_O_LANTERN,"Jack o'Lantern","une Jack o'Lantern"),
	PACKED_ICE(Material.PACKED_ICE,"Packet ice", "de la packed ice"),
	NETHER_BRICK(Material.NETHER_BRICK,"Brique du nether","de la brique du nether"),
	WOOD(Material.WOOD,"Bois", "du bois"),
	MOSSY(Material.MOSSY_COBBLESTONE,"Mossy cobblestone","de la mossy cobblestone"),
	IRON_BLOCK(Material.IRON_BLOCK,"Bloc de fer", "un bloc de fer"),
	WOOL(Material.WOOL,"Laine","un bloc de laine");
	
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
