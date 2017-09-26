import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Main 
{
	private static HashMap <String, User> users;
	private static LoginScreen loginScreen;
	private static AdminScreen admScreen;
	private static UserScreen userScreen;
	private static RegisterScreen registerScreen;
	
	public static void main(String[] args) 
	{
		try {
			loadUsers();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something wrong loading users. " + e.getMessage());
			users = new HashMap<>();
		}
		
		loginScreen = new LoginScreen();
	}
	
	public static void Close() 
	{
		try {
			saveUsers();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error saving users. " + e.getMessage());
		}
	}
	
	private static void loadUsers() throws IOException
	{
		users = new HashMap<>();
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader("users.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Creating new.");
			return;
		}
		
		while (br.ready()) 
		{
			User u = new User(br.readLine());
			users.put(u.nome, u);
		}
		
		br.close();
	}
	
	private static void saveUsers() throws IOException 
	{
		BufferedWriter br = new BufferedWriter(new FileWriter("users.txt"));
		
		for (String s : users.keySet()) 
		{
			br.write(users.get(s).toString());
			br.newLine();
		}
		
		br.flush();
		br.close();
		
		System.out.println("All users saved correctly");
	}
	
	public static void openRegisterScreen() 
	{
		if (registerScreen == null) registerScreen = new RegisterScreen();
		else registerScreen.setVisible(true);
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
	
	public static User[] getUsersList() 
	{
		User[] r = new User[users.size()];
		int pos = 0;
		
		for (String s : users.keySet()) 
		{
			r[pos++] = users.get(s);
		}
		
		Arrays.sort(r);
		
		return r;
	}
	
	public static void deleteUser(String username) throws UnknownUserException
	{
		if (users.remove(username) == null) throw new UnknownUserException();
	}
	
	public static boolean findUser(String username, String password) 
	{
		if (users.containsKey(username))
			if (users.get(username).senha.equals(password)) return true;
		
		return false;
	}
	
	public static void registerUser(String username, User user) throws UserAlreadyRegisteredException
	{
		if (users.containsKey(username)) throw new UserAlreadyRegisteredException();
		users.put(username, user);
	}
	
	public static void changeUser() 
	{
		loginScreen.setVisible(true);
	}
}
