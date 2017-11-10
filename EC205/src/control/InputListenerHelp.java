package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.HelpScreen;

public class InputListenerHelp implements ActionListener
{
	private HelpScreen helpScreen;
	
	public InputListenerHelp(HelpScreen helpScreen) 
	{
		this.helpScreen = helpScreen;
	}
	
	private void voltar() 
	{
		helpScreen.setVisible(false);
		Main.openUserScreen(helpScreen.isShowingPedidos ? "pedidos" : "medicamento");
	}
	
	private void Switch() 
	{
		if (helpScreen.isShowingPedidos) helpScreen.setVisible(true, "medicamento");
		else helpScreen.setVisible(true, "pedidos");
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("voltar")) voltar();
		if (e.getActionCommand().equals("switch")) Switch();
	}

}
