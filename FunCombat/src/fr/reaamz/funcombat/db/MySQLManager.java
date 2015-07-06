package fr.reaamz.funcombat.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.bukkit.DyeColor;
import org.bukkit.Location;

import code.husky.mysql.MySQL;
import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.Utils;
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
		
		state.executeUpdate("CREATE TABLE IF NOT EXISTS `tJumpLoc` (`JUMPNAME` varchar(16), `ZONESTART` varchar(128), `BLOCKSTART` varchar(128), `BLOCKEND` varchar(128))");
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
	
	public void updateJumpLoc(String jumpName, Location startZone, Location startBlock, Location endBlock) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		String startZoneSer = null, startBlockSer = null, endBlockSer = null;
		
		try { startZoneSer = startZone.serialize().toString(); } catch (NullPointerException e) { Utils.logInfo("startZoneSer = null"); } 
		
		try { startBlockSer = startBlock.serialize().toString(); } catch (NullPointerException e) { Utils.logInfo("startBlockSer = null"); }
		
		try { endBlockSer = endBlock.serialize().toString(); } catch (NullPointerException e) { Utils.logInfo("endBlockSer = null"); }
		
		if (getStartZone(jumpName) != null && getStartBlock(jumpName) != null && getEndBlock(jumpName) != null)
		{
			state.executeUpdate("UPDATE `tJumpLoc` SET `ZONESTART`='" + startZoneSer + "' WHERE `JUMPNAME`='" + jumpName + "';");
		}
		else if (getStartBlock(jumpName) != null && getEndBlock(jumpName) != null)
		{
			state.executeUpdate("UPDATE `tJumpLoc` SET `BLOCKSTART`='" + startBlockSer + "' WHERE `JUMPNAME`='" + jumpName + "' AND `ZONESTART`='" + startZoneSer + "';");
		}
		else if (getEndBlock(jumpName) != null)
		{
			state.executeUpdate("UPDATE `tJumpLoc` SET `BLOCKEND`='" + endBlockSer + "' WHERE `JUMPNAME`='" + jumpName + "' AND `ZONESTART`='" + startZoneSer + "';");
		}
		else
		{
			state.executeUpdate("INSERT INTO `tJumpLoc` (`JUMPNAME`,`ZONESTART`,`BLOCKSTART`,`BLOCKEND`) VALUES ('" + jumpName + "','" + startZoneSer + "','" + startBlockSer + "','" + endBlockSer + "')");			
		}
		
		state.close();
	}
	
	public String getStartZone(String jumpName) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		ResultSet res;
		
		res = state.executeQuery("SELECT * FROM `tJumpLoc` WHERE `JUMPNAME`='" + jumpName + "'");
		String loc;
//		try
//		{
//			loc = res.getString("ZONESTART");
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//			loc = null;
//		}
		
		if (res.next())
		{
			loc = res.getString("ZONESTART");
			if (loc.equals(null) || loc.equalsIgnoreCase("null"))
			{
				Utils.logInfo("-----------------------UNABLE TO GET ZONESTART #1-----------------------");
				loc = null;
			}
		}
		else
		{
			Utils.logInfo("-----------------------UNABLE TO GET ZONESTART #2-----------------------");
			loc = null;
		}
		
		state.close();
		return loc;
	}
	
	public String getStartBlock(String jumpName) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		ResultSet res;
		
		res = state.executeQuery("SELECT * FROM `tJumpLoc` WHERE `JUMPNAME`='" + jumpName + "'");
		String loc;
//		try
//		{
//			loc = res.getString("BLOCKSTART");
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//			loc = null;
//		}
		
		if (res.next())
		{			
			loc = res.getString("BLOCKSTART");
			if (loc.equals(null) || loc.equals("null"))
			{
				Utils.logInfo("-----------------------UNABLE TO GET BLOCKSTART #1-----------------------");
				loc = null;
			}
		}
		else
		{
			Utils.logInfo("-----------------------UNABLE TO GET BLOCKSTART #2-----------------------");
			loc = null;			
		}
		
		state.close();
		return loc;
	}
	
	public String getEndBlock(String jumpName) throws SQLException
	{
		Statement state = this.db.getConnection().createStatement();
		
		ResultSet res;
		
		res = state.executeQuery("SELECT * FROM `tJumpLoc` WHERE `JUMPNAME`='" + jumpName + "'");
		String loc;
//		try
//		{
//			loc = res.getString("BLOCKEND");
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//			loc = null;
//		}
		
		if (res.next())
		{
			loc = res.getString("BLOCKEND");
			if (loc.equals(null) || loc.equals("null"))
			{
				Utils.logInfo("-----------------------UNABLE TO GET BLOCKEND #1-----------------------");
				loc = null;
			}
		}
		else
		{
			Utils.logInfo("-----------------------UNABLE TO GET BLOCKEND #2-----------------------");
			loc = null;			
		}
		
		state.close();
		return loc;
	}
}
