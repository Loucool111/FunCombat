package fr.reaamz.funcombat.player;

import java.sql.SQLException;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.reaamz.funcombat.FunCombat;
import fr.reaamz.funcombat.grades.GradeType;
import fr.reaamz.funcombat.grades.GradeUtils;

public class PlayerInfo
{
	private Player player;
	private GradeType grade;
	
	public PlayerInfo(Player player)
	{
		this.player = player;
		try
		{
			this.grade =  FunCombat.database.getGrade(this.player.getUniqueId());
		}
		catch (SQLException e){ this.grade = GradeType.JOUEUR; }
	}
	
	public static PlayerInfo get(Player player)
	{
		return new PlayerInfo(player);
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public void sendToServer(String serverName)	
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		
		out.writeUTF("Connect");
		out.writeUTF(serverName);
		
		this.player.sendPluginMessage(FunCombat.instance, "BungeeCord", out.toByteArray());
	}
	
	public GradeType getGrade()
	{
		return this.grade;
	}
	
	public int getPermissionLevel()
	{
		return GradeUtils.getPermissionLevel(this.grade);
	}
}
