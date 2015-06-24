package fr.reaamz.funcombat.db;

import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.DyeColor;
import org.bukkit.entity.Player;

import code.husky.mysql.MySQL;
import fr.reaamz.funcombat.FunCombat;

public class MySQLManager
{
	private MySQL db;
		
	public void setupDatabase() throws ClassNotFoundException, SQLException
	{
		this.db = new MySQL(FunCombat.instance, "localhost", "3306", "funcombat", "root", "pass");
		this.db.openConnection();
		
		Statement state = this.db.getConnection().createStatement();
		
		state.executeUpdate("CREATE TABLE IF NOT EXISTS `tColor` (`UUID` varchar(64), `COLOR` varchar(16)");
		
		state.close();
	}
	
	public void closeDatabase() throws SQLException
	{
		this.db.closeConnection();
	}
	
	public DyeColor getColor(Player player)
	{
		return null; //TODO SELECT * FROM tColor WHERE UUID = player.getUniqueId()// ou quelque chose comme ça
	}
}
