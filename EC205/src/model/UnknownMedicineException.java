package model;

@SuppressWarnings("serial")
public class UnknownMedicineException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "Medicamento nao existe";
	}
}
