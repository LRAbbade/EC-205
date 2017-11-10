package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.DAO;
import model.Medicine;
import model.Pedido;
import model.RequestAlreadyRegisteredException;
import view.RequestScreen;

public class InputListenerRequest implements ActionListener
{
	private RequestScreen requestScreen;
	
	public InputListenerRequest(RequestScreen requestScreen) 
	{
		this.requestScreen = requestScreen;
	}
	
	private void salvar() 
	{
		String[] fields = requestScreen.getFields();
		Medicine m;
		if ((m = DAO.findMedicine(fields[0])) == null) 
		{
			JOptionPane.showMessageDialog(null, "Medicamento nao encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else 
		{
			int quantidade = new Integer(fields[1]);
			float total = quantidade * m.getPreco();
			
			try {
				DAO.registerRequest(new Pedido(m.getNome(), quantidade, total));
			} catch (RequestAlreadyRegisteredException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
			Main.openUserScreen("pedidos");
			requestScreen.setVisible(false);
		}
	}
	
	private void cancelar() 
	{
		requestScreen.setVisible(false);
		Main.openUserScreen("pedidos");
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("cancelar")) cancelar();
		if (e.getActionCommand().equals("salvar")) salvar();
	}

}
