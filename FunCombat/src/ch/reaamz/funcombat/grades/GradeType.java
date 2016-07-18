package ch.reaamz.funcombat.grades;

public enum GradeType 
{
	ADMIN("admin"),
	DEV("dev"),
	MODO("modo"),
	JOUEUR("joueur"),
	;
	
	private String name;
	
	private GradeType(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
}