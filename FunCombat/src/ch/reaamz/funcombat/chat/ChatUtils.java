package ch.reaamz.funcombat.chat;

/**
 * 
 * Random Stuff
 * <p>
 * Utils pour le JSONChat
 *
 */
public class ChatUtils 
{
	//ALL PART
	public static final String COMP_BASE = "{\"text\":\"";
	
	public static final String COMP_EXTRA = "\",\"extra\":[{\"text\":\"";
	
	//CLIC PART
	public static final String COMP_CLIC = "\",\"clickEvent\":{\"action\":\"";
	
	public static final String COMP_CLIC2 = "\",\"value\":\"";
	
	public static final String COMP_CLICEND = "\"}}]}";
	
	//HOVER PART
	public static final String COMP_HOVER = "\",\"hoverEvent\":{\"action\":\"";
	
	public static final String COMP_HOVER2 = "\",\"value\":\"";
	
	public static final String COMP_HOVEREND = "\"}}]}";
	
	//LES 2 PART
	public static final String COMP_BOTH = "\",\"clickEvent\":{\"action\":\"";
	
	public static final String COMP_BOTH2 = "\",\"value\":\"";
	
	public static final String COMP_BOTHEND = "\"}";
	
	public static final String COMP_BOTH3 = ",\"hoverEvent\":{\"action\":\"";
	
	public static final String COMP_BOTH4 = "\",\"value\":\"";
	
	public static final String COMP_BOTHEND2 =  "\"}}]}";
	
	/**
	 * 
	 * Enum des actions disponibles pour un HoverEvent.<br><br>
	 * 
	 * SHOW_TEXT("show_text")<br>
	 * SHOW_ITEM("show_item")<br>
 	 * SHOW_ACHIEVEMENT("show_achievement")
	 *
	 */
	public enum HoverActions
	{
		SHOW_TEXT("show_text"),
		SHOW_ITEM("show_item"),
		SHOW_ACHIEVEMENT("show_achievement");
		
		private final String type;
		
		HoverActions(String type) {
			this.type = type;
		}
		
		public String getTypeString(){
			return type;
		}
	}
	
	/**
	 * 
	 * Enum des actions disponibles pour un ClicEvent.<br><br>
	 *
	 * RUN_COMMAND("run_command")<br>
	 * SUGGEST_COMMAND("suggest_command")<br>
	 * OPEN_URL("open_url")
	 *
	 */
	public enum ClicActions
	{
		RUN_COMMAND("run_command"),
	    SUGGEST_COMMAND("suggest_command"),
	    OPEN_URL("open_url");
		
	    private final String type;
	    
	    ClicActions(String type) {
			this.type = type;
		}
	    
	    public String getTypeString(){
	    	return type;
	    }
	}
}
