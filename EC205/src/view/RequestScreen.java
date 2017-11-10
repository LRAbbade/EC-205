package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.InputListenerRequest;
import model.DAO;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RequestScreen extends JFrame 
{
	private JPanel contentPane;
	private InputListenerRequest inputListener;
	private JTextField textFieldNome;
	private JTextField textFieldQuantidade;

	public RequestScreen() 
	{
		inputListener = new InputListenerRequest(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registrar Pedido");
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 16));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(6, 6, 157, 30);
		panel.add(lblNewLabel);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(inputListener);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.setBounds(419, 6, 134, 39);
		panel.add(btnCancelar);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(176, 51, 377, 26);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome do medicamento");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(6, 56, 157, 16);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("Quantidade");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(6, 88, 157, 16);
		panel.add(label);
		
		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setColumns(10);
		textFieldQuantidade.setBounds(176, 83, 377, 26);
		panel.add(textFieldQuantidade);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(inputListener);
		btnSalvar.setActionCommand("salvar");
		btnSalvar.setBounds(176, 121, 192, 44);
		panel.add(btnSalvar);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        DAO.Close();
		    }
		});
		
		setVisible(true);
	}
	
	public String[] getFields() 
	{
		String[] r = new String[2];
		
		r[0] = textFieldNome.getText();
		r[1] = textFieldQuantidade.getText();
		
		return r;
	}
	
	@Override
	public void setVisible(boolean b) 
	{
		textFieldNome.setText("");
		textFieldQuantidade.setText("");
		super.setVisible(b);
	}

}
