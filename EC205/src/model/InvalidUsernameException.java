package model;

@SuppressWarnings("serial")
public class InvalidUsernameException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "Invalid username. Your username must not contain special characters.";
	}
}
