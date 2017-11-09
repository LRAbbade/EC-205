package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import control.Main;

public class DAO 
{
	private static HashMap<Integer, User> users;
	private static HashMap<Integer, Medicine> medicines;
	
	public static void Start() 
	{
		int lastId = -1;
		
		try {
			lastId = loadUsers();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something wrong loading users. " + e.getMessage());
			users = new HashMap<>();
		}
		
		User.setId(lastId);
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
			users.put(u.getPersonalId(), u);
			if (u.getPersonalId() > r) r = u.getPersonalId();
		}
		
		br.close();
		return r;
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
	
	public static void Delete(User u) throws UnknownUserException
	{
		if (users.remove(u.getPersonalId()) == null) throw new UnknownUserException();
	}
	
	public static User getUser(int id) throws UnknownUserException
	{
		if (!users.containsKey(id)) throw new UnknownUserException();
		return users.get(id);
	}
	
	public static boolean findUser(int id, String password) 
	{
		if (users.containsKey(id))
			if (users.get(id).getSenha().equals(password)) return true;
		
		return false;
	}
	
	public static boolean findUser(String username, String password) 
	{
		for (Integer i : users.keySet()) 
		{
			if (users.get(i).getNome().equals(username) && users.get(i).getSenha().equals(password)) return true;
		}
		
		return false;
	}
	
	public static void editUser(User u) 
	{
		Main.openRegisterScreen();
		Main.registerScreen.editUser(u);
		users.remove(u.getPersonalId());
	}
	
	public static void returnEditedUser(User u) 
	{
		users.put(u.getPersonalId(), u);
	}
	
	public static void registerUser(User u) throws UserAlreadyRegisteredException
	{
		if (users.containsKey(u.getPersonalId())) throw new UserAlreadyRegisteredException();
		users.put(u.getPersonalId(), u);
		Main.openAdminScreen();
	}
	
	public static Object[][] List(String type) 
	{
		Object[][] r;
		
		if (type.equalsIgnoreCase("user")) 
		{
			r = new Object[users.keySet().size()][User.GetAtributes().length];
			int pos = 0;
			
			for (Integer i : users.keySet()) 
			{
				r[pos++] = users.get(i).getInfoAsObject();
			}
			
			return r;
		}
		else if (type.equalsIgnoreCase("medicine")) 
		{
			r = new Object[medicines.keySet().size()][Medicine.GetAtributes().length];
			int pos = 0;
			
			for (Integer i : medicines.keySet()) 
			{
				r[pos++] = medicines.get(i).getInfoAsObject();
			}
			
			return r;
		}
		else 
		{
			System.out.println("invalid type string");
			return null;
		}
	}
	
}
