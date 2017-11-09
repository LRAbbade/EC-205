package model;

@SuppressWarnings("serial")
public class MedicineAlreadyRegisteredException extends Exception
{
	@Override
	public String getMessage() {
		return "Medicamento ja registrado";
	}
}
