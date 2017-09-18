import java.util.HashMap;
import javax.swing.JOptionPane;

public class Main 
{
	private static HashMap <String, String> users;
	private static LoginScreen loginScreen;
	private static AdminScreen admScreen;
	private static UserScreen userScreen;
	
	public static void main(String[] args) 
	{
		loadUsers();
		
		loginScreen = new LoginScreen();
	}
	
	private static void loadUsers()
	{
		users = new HashMap<>();
		
		// read all users from a file into r
	}
	
	public static void openAdminScreen() 
	{
		if (admScreen == null) admScreen = new AdminScreen();
		else admScreen.setVisible(true);
	}
	
	public static void openUserScreen() 
	{
		if (userScreen == null) userScreen = new UserScreen();
		else userScreen.setVisible(true);
	}
	
	public static boolean findUser(String username, String password) 
	{
		if (users.containsKey(username))
			if (users.get(username).equals(password)) return true;
		
		return false;
	}
	
	public static void registerUser(String username, String password) throws UserAlreadyRegisteredException
	{
		if (users.containsKey(username)) throw new UserAlreadyRegisteredException();
		users.put(username, password);
	}
	
	public static void changeUser() 
	{
		loginScreen.setVisible(true);
	}
}
