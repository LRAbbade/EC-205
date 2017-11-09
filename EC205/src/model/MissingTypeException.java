package model;

@SuppressWarnings("serial")
public class MissingTypeException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "Nenhum tipo selecionado";
	}
}
