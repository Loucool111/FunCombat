package fr.reaamz.funcombat.grades;

public class GradeUtils
{
	public static GradeType getGradeFromString(String grade)
	{
		if (grade.equals("admin")) return GradeType.ADMIN;
		else if (grade.equals("dev")) return GradeType.DEV;
		else if (grade.equals("modo")) return GradeType.MODO;
		else return GradeType.JOUEUR;
	}
	
	public static int getPermissionLevel(GradeType grade)
	{
		if (grade.equals(GradeType.ADMIN)) return 2;
		else if (grade.equals(GradeType.DEV)) return 2;
		else if (grade.equals(GradeType.MODO)) return 1;
		else return 0; 
	}
}
