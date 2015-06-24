package fr.reaamz.funcombat;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.reaamz.funcombat.command.HubCommandExecutor;
import fr.reaamz.funcombat.db.MySQLManager;
import fr.reaamz.funcombat.grades.GradesChatListener;
import fr.reaamz.funcombat.grades.GradesJoinListener;
import fr.reaamz.funcombat.hats.HatsMenuListener;
import fr.reaamz.funcombat.hub.HubClickListener;
import fr.reaamz.funcombat.hub.HubJoinListener;
import fr.reaamz.funcombat.hub.HubMotdListener;
import fr.reaamz.funcombat.jump.JumpListener;
import fr.reaamz.funcombat.kitpvp.KitpvpListener;
import fr.reaamz.funcombat.mainmenu.MainMenuListener;
import fr.reaamz.funcombat.metamorphoses.MetamorphListener;
import fr.reaamz.funcombat.otherlisteners.BungeeMessagesListener;
import fr.reaamz.funcombat.otherlisteners.TestListeners;
import fr.reaamz.funcombat.selectioncouleur.SelectionCouleurListener;

public class FunCombat extends JavaPlugin
{
	//TODO fix les message de niveau de kit qui apparaissent trop souvent
	//TODO database
	//TODO finir jumpscore
	//TODO grade dans database //TODO Finir la command /grade qui marche pas, obj = /grade set <playername> <gradename>
	//TODO fix la couleur des banières dans hats
	
	public static Plugin instance;
	
	public static MySQLManager mysql = new MySQLManager();
	
	@Override
	public void onEnable()
	{
		//définition de l'instance du plugin
		instance = this;
		
		//setup de la database
		try
		{
			mysql.setupDatabase();
		}
		catch (ClassNotFoundException | SQLException e)
		{
			Utils.logInfo("=========================DATABASE ERROR==========================");
			Utils.logInfo(e.toString());
			Utils.logInfo("=================================================================");			
		}
		
		//init du command executor
		HubCommandExecutor.init();
		
		//initialisation de listeners du plugin
		initListeners();
		
		//initialisation des commandes
		initCommands();
		
		//petit message dans la console
		Utils.logInfo("Loaded " + Utils.PLUGIN_NAME);
	}

	@Override
	public void onDisable()
	{
		//Sauvegarde de la config
		saveConfig();
		
		//Fermeture de la database
		try
		{
			mysql.closeDatabase();
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
		}
		
		//message de fin
		Utils.logInfo("Savegarde de " + Utils.PLUGIN_NAME);
	}
	
	private void initListeners()
	{
		//Listeners normaux
		Bukkit.getPluginManager().registerEvents(new HubClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new HubJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new MetamorphListener(), this);
		Bukkit.getPluginManager().registerEvents(new MainMenuListener(), this);
		Bukkit.getPluginManager().registerEvents(new SelectionCouleurListener(), this);
		Bukkit.getPluginManager().registerEvents(new KitpvpListener(), this);
		Bukkit.getPluginManager().registerEvents(new HubMotdListener(), this);
		Bukkit.getPluginManager().registerEvents(new HatsMenuListener(), this);
		Bukkit.getPluginManager().registerEvents(new JumpListener(), this);

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
	}
}
