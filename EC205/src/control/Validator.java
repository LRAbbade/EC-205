package control;

public class Validator 
{
	
	public static boolean isValidUsername(String s) 
	{
		for (int i = 0; i < s.length(); i++) 
		{
			char c = s.charAt(i);
			if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z') && !(c == ' ')) return false;
		}
		
		return true;
	}
	
	public static boolean isValidPassword(String s) 
	{
		if (s.length() < 5) return false;
		
		for (int i = 0; i < s.length(); i++) 
		{
			char c = s.charAt(i);
			if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z') && !(c >= '0' && c <= '9')) return false;
		}
		
		return true;
	}
	
}
