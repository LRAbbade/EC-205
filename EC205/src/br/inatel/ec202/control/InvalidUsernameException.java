package br.inatel.ec202.control;

@SuppressWarnings("serial")
public class InvalidUsernameException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "Invalid username. Your username must not contain special characters.";
	}
}
