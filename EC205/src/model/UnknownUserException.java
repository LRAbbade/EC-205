package model;

@SuppressWarnings("serial")
public class UnknownUserException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "Usuario nao existe";
	}
}
