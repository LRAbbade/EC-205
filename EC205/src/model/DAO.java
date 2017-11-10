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
	private static HashMap<Integer, Pedido> pedidos;
	
	public static void Start() 
	{
		int lastId = -1;
		int lastMedId = -1;
		int lastPedidoId = -1;
		
		try {
			lastId = loadUsers();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar os usuarios" + e.getMessage());
			users = new HashMap<>();
		}
		
		try {
			lastMedId = loadMedicines();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar medicamentos" + e.getMessage());
			users = new HashMap<>();
		}
		
		try {
			lastPedidoId = loadPedidos();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar pedidos" + e.getMessage());
			pedidos = new HashMap<>();
		}
		
		User.setId(lastId);
		Medicine.setClassId(lastMedId);
		Pedido.setClassId(lastPedidoId);
	}
	
	public static void Close() 
	{
		try {
			saveUsers();
			saveMedicines();
			savePedidos();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar informacoes: " + e.getMessage());
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
	
	private static int loadMedicines() throws IOException
	{
		int r = -1;
		medicines = new HashMap<>();
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader("medicines.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Creating new.");
			return r;
		}
		
		while (br.ready()) 
		{
			Medicine u = new Medicine(br.readLine());
			medicines.put(u.getPersonalId(), u);
			if (u.getPersonalId() > r) r = u.getPersonalId();
		}
		
		br.close();
		return r;
	}
	
	private static int loadPedidos() throws IOException
	{
		int r = -1;
		pedidos = new HashMap<>();
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader("pedidos.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Creating new.");
			return r;
		}
		
		while (br.ready()) 
		{
			Pedido u = new Pedido(br.readLine());
			pedidos.put(u.getPersonalId(), u);
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
	
	private static void saveMedicines() throws IOException 
	{
		BufferedWriter br = new BufferedWriter(new FileWriter("medicines.txt"));
		
		for (Integer i : medicines.keySet()) 
		{
			br.write(medicines.get(i).toString());
			br.newLine();
		}
		
		br.flush();
		br.close();
		
		System.out.println("All medicines saved correctly");
	}
	
	private static void savePedidos() throws IOException 
	{
		BufferedWriter br = new BufferedWriter(new FileWriter("pedidos.txt"));
		
		for (Integer i : pedidos.keySet()) 
		{
			br.write(pedidos.get(i).toString());
			br.newLine();
		}
		
		br.flush();
		br.close();
		
		System.out.println("All requests saved correctly");
	}
	
	public static void Delete(User u) throws UnknownUserException
	{
		if (users.remove(u.getPersonalId()) == null) throw new UnknownUserException();
	}
	
	public static void Delete(Medicine m) throws UnknownMedicineException
	{
		if (medicines.remove(m.getPersonalId()) == null) throw new UnknownMedicineException();
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
	
	public static Medicine findMedicine(String nome) 
	{
		Medicine m = null;
		
		for (Integer i : medicines.keySet()) 
		{
			if (medicines.get(i).getNome().equalsIgnoreCase(nome)) 
			{
				m = medicines.get(i);
				break;
			}
		}
		
		return m;
	}
	
	public static void editUser(User u) 
	{
		Main.openRegisterScreen();
		Main.registerScreen.editUser(u);
		users.remove(u.getPersonalId());
	}
	
	public static void editMedicine(Medicine m) 
	{
		Main.openRegisterMedicineScreen();
		Main.registerMedicineScreen.Edit(m);
		medicines.remove(m.getPersonalId());
	}
	
	public static void returnEditedUser(User u) 
	{
		users.put(u.getPersonalId(), u);
	}
	
	public static void returnEditedMedicine(Medicine m) 
	{
		medicines.put(m.getPersonalId(), m);
	}
	
	public static void registerUser(User u) throws UserAlreadyRegisteredException
	{
		if (users.containsKey(u.getPersonalId())) throw new UserAlreadyRegisteredException();
		users.put(u.getPersonalId(), u);
		Main.openAdminScreen();
	}
	
	public static void registerMedicine(Medicine m) throws MedicineAlreadyRegisteredException
	{
		if (medicines.containsKey(m.getPersonalId())) throw new MedicineAlreadyRegisteredException();
		medicines.put(m.getPersonalId(), m);
		Main.openUserScreen("medicamentos");
	}
	
	public static void registerRequest(Pedido p) throws RequestAlreadyRegisteredException
	{
		if (pedidos.containsKey(p.getPersonalId())) throw new RequestAlreadyRegisteredException();
		pedidos.put(p.getPersonalId(), p);
		Main.openUserScreen("pedidos");
	}
	
	public static Object[][] List(String type) 
	{
		Object[][] r = null;
		
		if (type.equalsIgnoreCase("user")) 
		{
			r = new Object[users.keySet().size()][User.GetAtributes().length];
			int pos = 0;
			
			for (Integer i : users.keySet()) 
			{
				r[pos++] = users.get(i).getInfoAsObject();
			}
		}
		else if (type.equalsIgnoreCase("medicine")) 
		{
			r = new Object[medicines.keySet().size()][Medicine.GetAtributes().length];
			int pos = 0;
			
			for (Integer i : medicines.keySet()) 
			{
				r[pos++] = medicines.get(i).getInfoAsObject();
			}
		}
		else if (type.equalsIgnoreCase("pedidos")) 
		{
			r = new Object[pedidos.keySet().size()][Pedido.GetAtributes().length];
			int pos = 0;
			
			for (Integer i : pedidos.keySet()) 
			{
				r[pos++] = pedidos.get(i).getInfoAsObject();
			}
		}
		else 
		{
			System.out.println("invalid type string");
			return null;
		}
		
		return r;
	}
	
}
