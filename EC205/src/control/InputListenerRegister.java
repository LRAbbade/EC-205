package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.DAO;
import model.InvalidPasswordException;
import model.InvalidUsernameException;
import model.User;
import model.UserAlreadyRegisteredException;
import view.RegisterScreen;

public class InputListenerRegister implements ActionListener
{
	private RegisterScreen registerScreen;
	
	public InputListenerRegister(RegisterScreen registerScreen) 
	{
		this.registerScreen = registerScreen;
	}
	
	private void salvar() 
	{
		if (registerScreen.isEditting) 
		{
			try {
				registerScreen.doEditUser();
				DAO.returnEditedUser(registerScreen.beingEditted);
				Main.openAdminScreen();
				JOptionPane.showMessageDialog(null, "Usuario editado");
				registerScreen.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
			return;
		}
		
		try {
			if (!signUp()) JOptionPane.showMessageDialog(null, "Usuario ja registrado");
			else 
			{
				JOptionPane.showMessageDialog(null, "Usuario registrado");
				registerScreen.setVisible(false);
			}
		} catch (InvalidPasswordException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (InvalidUsernameException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	private boolean signUp() throws InvalidPasswordException, InvalidUsernameException
	{
		String[] textFields = registerScreen.getTextFields();
		
		String username = textFields[0];
		String password = textFields[1];
		
		if (username.equals("admin")) 
		{ 
			JOptionPane.showMessageDialog(null, "Cannot register as admin"); 
			return false;
		}
		
		if (!Validator.isValidUsername(username)) throw new InvalidUsernameException();
		if (!Validator.isValidPassword(password)) throw new InvalidPasswordException();
		if (DAO.findUser(username, password)) return false;
		
		try {
			User u =	 new User(username, password, textFields[2],
												 textFields[3], 
												 textFields[4],
												 textFields[5], 
												 textFields[6],
												 new Integer(textFields[7]),
												 textFields[8]);
			
			DAO.registerUser(u);
			
		} catch (UserAlreadyRegisteredException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Ano de nascimento deve ser um numero", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao criar funcionario.", "Erro", JOptionPane.ERROR_MESSAGE);
			System.out.println("Erro em InputListenerRegister, line 87");
			System.out.println(ex.getClass().toString());
			System.out.println(ex.getMessage());
			return false;
		}
		
		return true;
	}
	
	private void cancelar() 
	{
		int op = JOptionPane.showConfirmDialog(null, "Cancelar registro?", "Cancelar", 
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if (op == JOptionPane.YES_OPTION) 
		{
			if (registerScreen.isEditting) DAO.returnEditedUser(registerScreen.beingEditted);
			Main.openAdminScreen();
			registerScreen.setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("salvar")) salvar();
		if (e.getActionCommand().equals("cancelar")) cancelar();
	}

}
