package br.inatel.ec202.control;

@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "Invalid Password. Your password must contain only letters and numbers, and at least 5 characters.";
	}
}
