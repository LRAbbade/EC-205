package model;

@SuppressWarnings("serial")
public class InvalidUsernameException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "Nome invalido. Seu nome nao deve conter caracteres especiais.";
	}
}
