package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.InputListenerRegisterMedicine;
import model.DAO;

@SuppressWarnings("serial")
public class RegisterMedicineScreen extends JFrame 
{
	private JPanel contentPane;
	private InputListenerRegisterMedicine inputListener;

	public RegisterMedicineScreen() 
	{
		inputListener = new InputListenerRegisterMedicine(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        DAO.Close();
		    }
		});
		
		setVisible(true);
	}
	
	@Override
	public void setVisible(boolean b) 
	{
		// TODO Auto-generated method stub
		super.setVisible(b);
	}

}
