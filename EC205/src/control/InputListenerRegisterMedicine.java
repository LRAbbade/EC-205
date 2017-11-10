package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.DAO;
import model.Medicine;
import model.MedicineAlreadyRegisteredException;
import model.MissingTypeException;
import view.RegisterMedicineScreen;

public class InputListenerRegisterMedicine implements ActionListener
{
	private RegisterMedicineScreen registerMedicineScreen;
	
	public InputListenerRegisterMedicine(RegisterMedicineScreen registerMedicineScreen) 
	{
		this.registerMedicineScreen = registerMedicineScreen;
	}
	
	private void cancelar() 
	{
		if (registerMedicineScreen.isEditing) DAO.returnEditedMedicine(registerMedicineScreen.beingEdited);
		registerMedicineScreen.setVisible(false);
		Main.openUserScreen("medicamentos");
	}
	
	private void salvar() 
	{
		if (!registerMedicineScreen.isEditing) 
		{
			try {
				Medicine m = new Medicine(registerMedicineScreen.getFields());
				DAO.registerMedicine(m);
			} catch (MedicineAlreadyRegisteredException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Erro ao criar medicamento", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("isso nao deveria acontecer");
			} catch (MissingTypeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		else 
		{
			try {
				Medicine m = new Medicine(registerMedicineScreen.getFields(), 
						  					registerMedicineScreen.beingEdited.getPersonalId());
				DAO.returnEditedMedicine(m);
			} catch (ArrayIndexOutOfBoundsException | MissingTypeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				DAO.returnEditedMedicine(registerMedicineScreen.beingEdited);
			}
		}
		
		registerMedicineScreen.setVisible(false);
		Main.openUserScreen("medicamentos");
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("cancelar")) cancelar();
		if (e.getActionCommand().equals("salvar")) salvar();
	}

}
