package fr.reaamz.funcombat.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.DyeColor;
import org.bukkit.entity.Player;

import code.husky.mysql.MySQL;
import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.grades.GradeType;
import fr.reaamz.funcombat.grades.GradeUtils;
import fr.reaamz.funcombat.selectioncouleur.SelectionCouleurUtils;

public class MySQLManager
{
	private MySQL db;
		
	public void setupDatabase() throws ClassNotFoundException, SQLException
	{
		this.db = new MySQL(FunCombat.instance, "localhost", "3306", "funcombat", "root", "");
		this.db.openConnection();
		
		Statement state = this.db.getConnection().createStatement();
		
		state.executeUpdate("CREATE TABLE IF NOT EXISTS `tColor` (`UUID` varchar(64), `COLOR` varchar(16))");
		state.executeUpdate("CREATE TABLE IF NOT EXISTS `tGrades` (`UUID` varchar(64), `GRADE` varchar(16))");
		
		state.close();
	}
	
	public void closeDatabase() throws SQLException
	{
		this.db.closeConnection();
	}
	
	public void updateDyeColor(Player player, DyeColor color) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		if (getColor(player) != null)
		{	
			state.executeUpdate("UPDATE `tColor` SET `COLOR`='" + color.toString().toLowerCase() + "' WHERE `UUID`='" + player.getUniqueId() + "';");
		}
		else
		{
			state.executeUpdate("INSERT INTO `tColor` (`UUID`,`COLOR`) VALUES ('" + player.getUniqueId() + "','" + color.toString().toLowerCase() + "');");
		}
	}
	
	public DyeColor getColor(Player player) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		ResultSet res = state.executeQuery("SELECT * FROM `tColor` WHERE `UUID`='" + player.getUniqueId().toString() + "';");
		
		if (!res.next())
			return null;
		
		return SelectionCouleurUtils.getDyeColorFromString(res.getString("COLOR"));
	}
	
	public void updateGrade(Player player, GradeType grade) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		if (getGrade(player) != null)
		{
			state.executeUpdate("UPDATE `tGrades` SET `GRADE`='" + grade.getName() + "' WHERE `UUID`='" + player.getUniqueId() + "';");
		}
		else			
		{
			state.executeUpdate("INSERT INTO `tGrades` (`UUID`,`GRADE`) VALUES ('" + player.getUniqueId() + "','" + grade.getName() + "');");
		}
	}
	
	public GradeType getGrade(Player player) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		ResultSet res = state.executeQuery("SELECT * FROM `tGrades` WHERE `UUID`='" + player.getUniqueId() + "';");
		
		if (!res.next())
			return null;
		
		return GradeUtils.getGradeFromString(res.getString("GRADE"));
	}
}
