package br.inatel.ec202.control;

@SuppressWarnings("serial")
public class UnknownUserException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "User does not exist";
	}
}
