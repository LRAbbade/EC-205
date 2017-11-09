package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.DAO;
import model.UnknownUserException;
import view.AdminScreen;

public class InputListenerAdmin implements ActionListener
{
	private AdminScreen admScreen;
	
	public InputListenerAdmin(AdminScreen admScreen) 
	{
		this.admScreen = admScreen;
	}
	
	private void delete()
	{
		try {
			DAO.Delete(admScreen.getSelectedUser());
			admScreen.setTable();
			admScreen.setDeleteAndEditButtonEnable();
			JOptionPane.showMessageDialog(null, "Usuario deletado");
		} catch (UnknownUserException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "Nenhum usuario selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (ArrayIndexOutOfBoundsException e1) {
			JOptionPane.showMessageDialog(null, "Nenhum usuario selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void edit() 
	{
		DAO.editUser(admScreen.getSelectedUser());
		admScreen.setVisible(false);
	}
	
	private void changeUser() 
	{
		Main.changeUser();
		admScreen.setVisible(false);
	}
	
	private void register() 
	{
		Main.openRegisterScreen();
		admScreen.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("delete")) delete();
		else if (e.getActionCommand().equals("edit")) edit();
		else if (e.getActionCommand().equals("change user")) changeUser();
		else if (e.getActionCommand().equals("register")) register();
	}
	
}
