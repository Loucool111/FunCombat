package fr.reaamz.funcombat.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.bukkit.DyeColor;

import code.husky.mysql.MySQL;
import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.grades.GradeType;
import fr.reaamz.funcombat.grades.GradeUtils;
import fr.reaamz.funcombat.selectioncouleur.SelectionCouleurUtils;

public class MySQLManager
{
	private MySQL db;
		
	public void connectDatabase() throws ClassNotFoundException, SQLException
	{
		this.db = new MySQL(FunCombat.instance, "localhost", "3306", "funcombat", "root", "");
		this.db.openConnection();
	}
	
	public void setupTables() throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		state.executeUpdate("CREATE TABLE IF NOT EXISTS `tColor` (`UUID` varchar(64), `COLOR` varchar(16))");
		state.executeUpdate("CREATE TABLE IF NOT EXISTS `tGrades` (`UUID` varchar(64), `GRADE` varchar(16))");
		
		state.executeUpdate("CREATE TABLE IF NOT EXISTS `tJump` (`UUID` varchar(64),`JUMPNAME` varchar(16), `TENT` varchar(6), `BESTTIME` varchar(10), `LASTTIME` varchar(10))");
		
		state.close();
	}
	
	public void closeDatabase() throws SQLException
	{
		this.db.closeConnection();
	}
	
	public void updateDyeColor(UUID uuid, DyeColor color) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		if (getColor(uuid) != null)
		{	
			state.executeUpdate("UPDATE `tColor` SET `COLOR`='" + color.toString().toLowerCase() + "' WHERE `UUID`='" + uuid + "';");
		}
		else
		{
			state.executeUpdate("INSERT INTO `tColor` (`UUID`,`COLOR`) VALUES ('" + uuid + "','" + color.toString().toLowerCase() + "');");
		}
		
		state.close();
	}
	
	public DyeColor getColor(UUID uuid) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		ResultSet res = state.executeQuery("SELECT * FROM `tColor` WHERE `UUID`='" + uuid + "';");
				
		if (!res.next())
		{
			state.close();
			return null;
		}
		
		String color = res.getString("COLOR");
		state.close();
		return SelectionCouleurUtils.getDyeColorFromString(color);
	}
	
	public void updateGrade(UUID uuid, GradeType grade) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		if (getGrade(uuid) != null)
		{
			state.executeUpdate("UPDATE `tGrades` SET `GRADE`='" + grade.getName() + "' WHERE `UUID`='" + uuid + "';");
		}
		else			
		{
			state.executeUpdate("INSERT INTO `tGrades` (`UUID`,`GRADE`) VALUES ('" + uuid + "','" + grade.getName() + "');");
		}
		
		state.close();
	}
	
	public GradeType getGrade(UUID uuid) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		ResultSet res = state.executeQuery("SELECT * FROM `tGrades` WHERE `UUID`='" + uuid + "';");
		
		if (!res.next())
		{
			state.close();
			return null;			
		}
		
		String grade = res.getString("GRADE");		
		state.close();
		return GradeUtils.getGradeFromString(grade);
	}
	
	public void updateTime(UUID uuid, String jumpname, int tent, int besttime, int lasttime) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		if (getBestTime(jumpname, uuid) != null && getLastTime(jumpname, uuid) != null && getTent(jumpname, uuid) != null)
		{
			state.executeUpdate("UPDATE `tJump` SET `BESTTIME`='" + besttime + "' WHERE `UUID`='" + uuid + "';");
			state.executeUpdate("UPDATE `tJump` SET `LASTTIME`='" + lasttime + "' WHERE `UUID`='" + uuid + "';");
			state.executeUpdate("UPDATE `tJump` SET `TENT`='" + tent + "' WHERE `UUID`='" + uuid + "';");
		}
		else
		{
			state.executeUpdate("INSERT INTO `tJump` (`UUID`,`JUMPNAME`,`TENT`,`BESTTIME`,`LASTTIME`) VALUES ('" + uuid + "','" + jumpname + "','" + tent + "','" + besttime + "','" + lasttime +"');");
		}
		
		state.close();
	}
	
	public String getTent(String jumpname, UUID uuid) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		ResultSet res;
				
		res = state.executeQuery("SELECT * FROM `tJump` WHERE `UUID`='" + uuid + "' AND `JUMPNAME`='" + jumpname + "'");
		
		String tent = res.getString("TENT");
		state.close();
		return tent;
	}
	
	public String getBestTime(String jumpname, UUID uuid) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		ResultSet res;
				
		res = state.executeQuery("SELECT * FROM `tJump` WHERE `UUID`='" + uuid + "' AND `JUMPNAME`='" + jumpname + "'");
		
		String besttime = res.getString("BESTTIME");
		state.close();
		return besttime;
	}
	
	public String getLastTime(String jumpname, UUID uuid) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		ResultSet res;
				
		res = state.executeQuery("SELECT * FROM `tJump` WHERE `UUID`='" + uuid + "' AND `JUMPNAME`='" + jumpname + "'");
		
		String lasttime = res.getString("LASTTIME");
		state.close();
		return lasttime;
	}
}
