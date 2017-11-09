package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		// TODO
	}
	
	private void edit() 
	{
		// TODO
	}
	
	private void trocarUsuario() 
	{
		userScreen.setVisible(false);
		Main.changeUser();
	}
	
	private void adicionar() 
	{
		// TODO
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("deletar")) delete();
		if (e.getActionCommand().equals("editar")) edit();
		if (e.getActionCommand().equals("trocar")) trocarUsuario();
		if (e.getActionCommand().equals("adicionar")) adicionar();
	}

}
