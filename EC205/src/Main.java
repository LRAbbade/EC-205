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
	private static HashMap <Integer, User> users;
	private static LoginScreen loginScreen;
	private static AdminScreen admScreen;
	private static UserScreen userScreen;
	private static RegisterScreen registerScreen;
	public static int[] stringSizes;
	
	public static void main(String[] args) 
	{
		stringSizes = new int[10];
		int lastId = -1;
		
		try {
			lastId = loadUsers();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something wrong loading users. " + e.getMessage());
			users = new HashMap<>();
		}
		
		User.setId(lastId);
		
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
	
	private static int loadUsers() throws IOException
	{
		int r = -1;
		users = new HashMap<>();
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader("users.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Creating new.");
			return r;
		}
		
		while (br.ready()) 
		{
			User u = new User(br.readLine());
			setStringSizes(u);	
			users.put(u.personalId, u);
			if (u.personalId > r) r = u.personalId;
		}
		
		br.close();
		return r;
	}
	
	private static void setStringSizes(User u) 
	{
		int idSize = (new Integer(u.personalId)).toString().length();
		
		if (idSize > stringSizes[0]) stringSizes[0] = idSize;
		if (u.nome.length() > stringSizes[1]) stringSizes[1] = u.nome.length();
		if (u.senha.length() > stringSizes[2]) stringSizes[2] = u.senha.length();
		if (u.cargo.length() > stringSizes[3]) stringSizes[3] = u.cargo.length();
		if (u.rua.length() > stringSizes[4]) stringSizes[4] = u.rua.length();
		if (u.bairro.length() > stringSizes[5]) stringSizes[5] = u.bairro.length();
		if (u.cidade.length() > stringSizes[6]) stringSizes[6] = u.cidade.length();
		if (u.telefone.length() > stringSizes[7]) stringSizes[7] = u.telefone.length();
		
		int birthSize = (new Integer(u.birthYear)).toString().length();
		int cpfSize = (new Integer(u.cpf)).toString().length();
		
		if (birthSize > stringSizes[8]) stringSizes[8] = birthSize;
		if (cpfSize > stringSizes[9]) stringSizes[9] = cpfSize;
	}
	
	private static void saveUsers() throws IOException 
	{
		BufferedWriter br = new BufferedWriter(new FileWriter("users.txt"));
		
		for (Integer i : users.keySet()) 
		{
			br.write(users.get(i).toString());
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
		
		for (Integer i : users.keySet()) 
		{
			r[pos++] = users.get(i);
		}
		
		Arrays.sort(r);
		
		return r;
	}
	
	public static void deleteUser(int id) throws UnknownUserException
	{
		if (users.remove(id) == null) throw new UnknownUserException();
	}
	
	public static User getUser(int id) throws UnknownUserException
	{
		if (!users.containsKey(id)) throw new UnknownUserException();
		return users.get(id);
	}
	
	public static boolean findUser(int id, String password) 
	{
		if (users.containsKey(id))
			if (users.get(id).senha.equals(password)) return true;
		
		return false;
	}
	
	public static void editUser(User u) 
	{
		openRegisterScreen();
		registerScreen.editUser(u);
		users.put(u.personalId, u);
	}
	
	public static void returnEdittedUser(User u) 
	{
		openAdminScreen();
		admScreen.returnEdittedUser(u);
	}
	
	public static boolean findUser(String username, String password) 
	{
		for (Integer i : users.keySet()) 
		{
			if (users.get(i).nome.equals(username) && users.get(i).senha.equals(password)) return true;
		}
		
		return false;
	}
	
	public static void registerUser(int id, User user) throws UserAlreadyRegisteredException
	{
		if (users.containsKey(id)) throw new UserAlreadyRegisteredException();
		users.put(id, user);
		admScreen.addUser(user.info());
	}
	
	public static void changeUser() 
	{
		loginScreen.setVisible(true);
	}
}
