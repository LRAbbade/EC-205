package br.inatel.ec202.control;

@SuppressWarnings("serial")
public class UserAlreadyRegisteredException extends Exception
{
	@Override
	public String getMessage() {
		return "User already registered";
	}
}
