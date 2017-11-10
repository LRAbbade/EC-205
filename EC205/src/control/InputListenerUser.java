package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.DAO;
import model.Medicine;
import model.UnknownMedicineException;
import view.UserScreen;

public class InputListenerUser implements ActionListener
{
	private UserScreen userScreen;
	
	public InputListenerUser(UserScreen userScreen) 
	{
		this.userScreen = userScreen;
	}
	
	private void delete() 
	{
		Medicine m = getSelectedMedicine();
		if (m == null) return;
		
		try {
			DAO.Delete(m);
		} catch (UnknownMedicineException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		userScreen.setTable();
		userScreen.setEditAndDeleteButton();
	}
	
	private void edit() 
	{
		Medicine m = getSelectedMedicine();
		if (m == null) return;
		
		DAO.editMedicine(m);
		userScreen.setVisible(false);
	}
	
	private Medicine getSelectedMedicine()
	{
		Object[] info;
		
		try {
			info = userScreen.getSelectedRow();
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Nenhum medicamento selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		String[] arr = new String[info.length - 1];
		int id = new Integer((String)info[0]);
		
		for (int i = 1; i < info.length; i++) 
		{
			arr[i - 1] = (String)info[i];
		}
		
		return new Medicine(arr, id);
	}
	
	private void trocarUsuario() 
	{
		userScreen.setVisible(false);
		Main.changeUser();
	}
	
	private void adicionar() 
	{
		if (userScreen.isInMedicineScreen) 
		{
			userScreen.setVisible(false);
			Main.openRegisterMedicineScreen();
		}
		else 
		{
			userScreen.setVisible(false);
			Main.openRequestScreen();
		}
	}
	
	private void pedidos() 
	{
		if (userScreen.isInMedicineScreen) userScreen.showPedidos();
		else userScreen.showMedicamentos();
	}
	
	private void help() 
	{
		userScreen.setVisible(false);
		Main.openHelpScreen();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("deletar")) delete();
		if (e.getActionCommand().equals("editar")) edit();
		if (e.getActionCommand().equals("trocar")) trocarUsuario();
		if (e.getActionCommand().equals("adicionar")) adicionar();
		if (e.getActionCommand().equals("pedidos")) pedidos();
		if (e.getActionCommand().equals("help")) help();
	}

}
