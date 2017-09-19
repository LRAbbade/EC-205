import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Main 
{
	private static HashMap <String, String> users;
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
			String[] line = br.readLine().split(" ");
			users.put(line[0], line[1]);
		}
		
		br.close();
	}
	
	private static void saveUsers() throws IOException 
	{
		BufferedWriter br = new BufferedWriter(new FileWriter("users.txt"));
		
		for (String s : users.keySet()) 
		{
			br.write(s + " " + users.get(s));
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
	
	public static String[][] getUsersList() 
	{
		String[][] r = new String[users.size()][2];
		int pos = 0;
		
		for (String s : users.keySet()) 
		{
			r[pos][0] = s;
			r[pos++][1] = users.get(s);
		}
		
		return r;
	}
	
	public static void deleteUser(String username) throws UnknownUserException
	{
		if (users.remove(username) == null) throw new UnknownUserException();
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
