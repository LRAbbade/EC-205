package model;

@SuppressWarnings("serial")
public class RequestAlreadyRegisteredException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "Pedido ja registrado";
	}
}
