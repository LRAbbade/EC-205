package control;

import model.*;
import view.*;

public class Main 
{
	public static LoginScreen loginScreen;
	public static AdminScreen admScreen;
	public static UserScreen userScreen;
	public static RegisterScreen registerScreen;
	public static RegisterMedicineScreen registerMedicineScreen;
	public static RequestScreen requestScreen;
	public static HelpScreen helpScreen;
	
	public static void main(String[] args) 
	{	
		DAO.Start();
		loginScreen = new LoginScreen();
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
	
	public static void openUserScreen(String type) 
	{
		if (userScreen == null) userScreen = new UserScreen();
		else userScreen.setVisible(true);
		
		if (type.equals("pedidos")) userScreen.showPedidos();
	}
	
	public static void openRegisterMedicineScreen() 
	{
		if (registerMedicineScreen == null) registerMedicineScreen = new RegisterMedicineScreen();
		else registerMedicineScreen.setVisible(true);
	}
	
	public static void openRequestScreen() 
	{
		if (requestScreen == null) requestScreen = new RequestScreen();
		else requestScreen.setVisible(true);
	}
	
	public static void openHelpScreen() 
	{
		if (helpScreen == null) helpScreen = new HelpScreen();
		else helpScreen.setVisible(true);
	}
	
	public static void changeUser() 
	{
		loginScreen.setVisible(true);
	}
}
