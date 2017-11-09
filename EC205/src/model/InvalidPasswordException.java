package model;

@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "Senha invalida. Sua senha deve conter apenas letras e numeros e pelo menos 5 caracteres.";
	}
}
