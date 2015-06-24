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
}
