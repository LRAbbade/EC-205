
public class UserAlreadyRegisteredException extends Exception
{
	@Override
	public String getMessage() {
		return "User already registered";
	}
}
