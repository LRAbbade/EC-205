package control;

import model.*;
import view.*;

public class Main 
{
	public static LoginScreen loginScreen;
	public static AdminScreen admScreen;
	public static UserScreen userScreen;
	public static RegisterScreen registerScreen;
	
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
	
	public static void openUserScreen() 
	{
		if (userScreen == null) userScreen = new UserScreen();
		else userScreen.setVisible(true);
	}
	
	public static void changeUser() 
	{
		loginScreen.setVisible(true);
	}
	
	public static void Close() 
	{
		DAO.Close();
	}
}
