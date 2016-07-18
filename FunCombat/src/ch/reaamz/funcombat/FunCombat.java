package ch.reaamz.funcombat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ch.reaamz.funcombat.color.ColorListener;
import ch.reaamz.funcombat.command.GradeCommandExecutor;
import ch.reaamz.funcombat.command.HubCommandExecutor;
import ch.reaamz.funcombat.command.JumpCommandExecutor;
import ch.reaamz.funcombat.completers.GradeTabCompleter;
import ch.reaamz.funcombat.completers.JumpTabCompleter;
import ch.reaamz.funcombat.database.MySQLManager;
import ch.reaamz.funcombat.grades.GradesChatListener;
import ch.reaamz.funcombat.grades.GradesJoinListener;
import ch.reaamz.funcombat.hats.HatsMenuListener;
import ch.reaamz.funcombat.hub.HubClickListener;
import ch.reaamz.funcombat.hub.HubJoinListener;
import ch.reaamz.funcombat.hub.HubMotdListener;
import ch.reaamz.funcombat.jump.JumpDoneListener;
import ch.reaamz.funcombat.jump.JumpListener;
import ch.reaamz.funcombat.jump.JumpManager;
import ch.reaamz.funcombat.kitpvp.KitpvpListener;
import ch.reaamz.funcombat.localization.LocalizationManager;
import ch.reaamz.funcombat.mainmenu.MainMenuListener;
import ch.reaamz.funcombat.metamorphoses.MetamorphListener;
import ch.reaamz.funcombat.otherlisteners.BungeeMessagesListener;
import ch.reaamz.funcombat.otherlisteners.TestListeners;

/**
 * 
 * @author Reaamz / Loucool111
 * <br><br>
 * Cette classe est la classe principale du plugin FunCombat<br>
 * https://github.com/Loucool111/FunCombat
 */
public class FunCombat extends JavaPlugin
{
	//TODO finir jumpscore et jump
	//TODO database table avec des truc random (hub loc etc..)
	//TODO stocker les infos de temps dans la db
	//TODO Permissions ......
	//TODO la grade depuis la console et le perms
	
	public static Plugin instance;
	
	public static LocalizationManager localizer;
	
	public static MySQLManager database = new MySQLManager();
	
	@Override
	public void onEnable()
	{
		//définition de l'instance du plugin
		instance = this;
		
		//setup du localizer
		try
		{
			localizer = new LocalizationManager(this.getConfig().getString("locFileName"));
		}
		catch (URISyntaxException | IOException | IllegalArgumentException e)
		{		
			Utils.logInfo("====================LOCALIZATION FILE NOT FOUND=====================");
			Utils.logInfo(e.toString());
			Utils.logInfo("====================================================================");
		}
		
		//setup de la database
		try
		{
			database.connectDatabase();
			database.setupTables();
		}
		catch (ClassNotFoundException | SQLException e)
		{
			Utils.logInfo("====================DATABASE CONNECTION ERROR=====================");
			Utils.logInfo(e.toString());
			Utils.logInfo("==================================================================");
		}
		
		//init du command executor
		HubCommandExecutor.init();
		
		//initialisation de listeners du plugin
		initListeners();
		
		//initialisation des commandes
		initCommands();
		
		//petit message dans la console
		Utils.logInfo(FunCombat.localizer.locate("funcombat.loaded") + " " + Utils.PLUGIN_NAME);
	}

	@Override
	public void onDisable()
	{
		//Sauvegarde de la config
		saveConfig();
		
		//Fermeture de la database
		try
		{
			database.closeDatabase();
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
		}
		
		//message de fin
		Utils.logInfo(FunCombat.localizer.locate("funcombat.saved") + " " + Utils.PLUGIN_NAME);
	}
	
	private void initListeners()
	{
		//Listeners normaux
		Bukkit.getPluginManager().registerEvents(new HubClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new HubJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new MetamorphListener(), this);
		Bukkit.getPluginManager().registerEvents(new MainMenuListener(), this);
		Bukkit.getPluginManager().registerEvents(new ColorListener(), this);
		Bukkit.getPluginManager().registerEvents(new KitpvpListener(), this);
		Bukkit.getPluginManager().registerEvents(new HubMotdListener(), this);
		Bukkit.getPluginManager().registerEvents(new HatsMenuListener(), this);
		Bukkit.getPluginManager().registerEvents(new JumpListener(), this);
		Bukkit.getPluginManager().registerEvents(new JumpDoneListener(), this);
		Bukkit.getPluginManager().registerEvents(new JumpManager(), this);

		//Grades listeners
		Bukkit.getPluginManager().registerEvents(new GradesJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new GradesChatListener(), this);
		
		//Outgoing messages
		Bukkit.getMessenger().registerOutgoingPluginChannel(FunCombat.instance, "BungeeCord");
		
		//Incoming messages
		Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeMessagesListener());
		
		//test
		Bukkit.getPluginManager().registerEvents(new TestListeners(), this);
	}
	
	private void initCommands()
	{
		//instance de l'executor
		CommandExecutor hubExecutor = new HubCommandExecutor();
		
		//définition des commandes
		getCommand("hub").setExecutor(hubExecutor);
		getCommand("sethub").setExecutor(hubExecutor);
		getCommand("gethub").setExecutor(hubExecutor);
		getCommand("resethubdata").setExecutor(hubExecutor);
		getCommand("getmenu").setExecutor(hubExecutor);
		
		//instance du grade exector
		CommandExecutor gradeExecutor = new GradeCommandExecutor();
		TabCompleter gradeComplter = new GradeTabCompleter();
		
		//définition des commandes
		getCommand("grade").setExecutor(gradeExecutor);
		getCommand("grade").setTabCompleter(gradeComplter);
		getCommand("uuid").setExecutor(gradeExecutor);
		getCommand("uuid").setTabCompleter(gradeComplter);
		
		CommandExecutor jumpExecutor = new JumpCommandExecutor();
		TabCompleter jumpCompleter = new JumpTabCompleter();
		
		getCommand("setjump").setExecutor(jumpExecutor);
		getCommand("setjump").setTabCompleter(jumpCompleter);
	}
}
