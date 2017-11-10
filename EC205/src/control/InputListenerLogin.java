package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.DAO;
import model.UnknownUserException;
import view.LoginScreen;

public class InputListenerLogin implements ActionListener 
{
	private LoginScreen loginScreen;
	
	public InputListenerLogin(LoginScreen loginScreen) 
	{
		this.loginScreen = loginScreen;
	}

	private void login() 
	{
		try {
			signIn();
			loginScreen.setVisible(false);
		} catch (UnknownUserException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	private void signIn() throws UnknownUserException
	{
		String[] user = loginScreen.getTextFields();
		
		if (user[0].equals("admin") && user[1].equals("admin")) Main.openAdminScreen();
		else if (DAO.findUser(user[0], user[1])) Main.openUserScreen("medicamentos");
		else throw new UnknownUserException();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("entrar")) login();
	}	

}
