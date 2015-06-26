package fr.reaamz.funcombat.jump;

public enum JumpNames
{
	JUMP1("jump1"),
	JUMP2("jump2"),
	JUMP3("jump3"),
	;
	
	private String name;
	
	private JumpNames(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
}
