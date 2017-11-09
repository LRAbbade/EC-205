package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import control.InputListenerRegisterMedicine;
import model.DAO;
import model.Medicine;
import model.MissingTypeException;
import model.TipoMedicamento;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class RegisterMedicineScreen extends JFrame 
{
	private JPanel contentPane;
	private InputListenerRegisterMedicine inputListener;
	private JTextField textFieldNome;
	private JTextField textFieldFabricante;
	private JTextField textFieldPrincipioAtivo;
	private JTextField textFieldPreco;
	private ButtonGroup buttonGroupTipo;
	private JRadioButton rdbtnComum;
	private JRadioButton rdbtnControlado;
	private JRadioButton rdbtnGenerico;
	private JRadioButton rdbtnManipulado;
	public boolean isEditing;
	public Medicine beingEdited;

	public RegisterMedicineScreen() 
	{
		inputListener = new InputListenerRegisterMedicine(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registrar novo medicamento");
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 16));
		lblNewLabel.setBounds(6, 6, 220, 29);
		panel.add(lblNewLabel);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(96, 47, 370, 26);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(23, 52, 61, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblFabricante = new JLabel("Fabricante");
		lblFabricante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFabricante.setBounds(6, 90, 78, 16);
		panel.add(lblFabricante);
		
		textFieldFabricante = new JTextField();
		textFieldFabricante.setColumns(10);
		textFieldFabricante.setBounds(96, 85, 370, 26);
		panel.add(textFieldFabricante);
		
		JLabel lblPrincipioAtivo = new JLabel("Principio ativo");
		lblPrincipioAtivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrincipioAtivo.setBounds(6, 123, 129, 16);
		panel.add(lblPrincipioAtivo);
		
		textFieldPrincipioAtivo = new JTextField();
		textFieldPrincipioAtivo.setColumns(10);
		textFieldPrincipioAtivo.setBounds(147, 118, 319, 26);
		panel.add(textFieldPrincipioAtivo);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(23, 155, 61, 16);
		panel.add(lblNewLabel_2);
		
		rdbtnComum = new JRadioButton("Comum");
		rdbtnComum.setBounds(96, 151, 141, 23);
		panel.add(rdbtnComum);
		
		rdbtnControlado = new JRadioButton("Controlado");
		rdbtnControlado.setBounds(96, 177, 141, 23);
		panel.add(rdbtnControlado);
		
		rdbtnGenerico = new JRadioButton("Generico");
		rdbtnGenerico.setBounds(96, 203, 141, 23);
		panel.add(rdbtnGenerico);
		
		rdbtnManipulado = new JRadioButton("Manipulado");
		rdbtnManipulado.setBounds(96, 229, 141, 23);
		panel.add(rdbtnManipulado);
		
		buttonGroupTipo = new ButtonGroup();
		
		buttonGroupTipo.add(rdbtnComum);
		buttonGroupTipo.add(rdbtnControlado);
		buttonGroupTipo.add(rdbtnGenerico);
		buttonGroupTipo.add(rdbtnManipulado);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setBounds(96, 264, 370, 26);
		panel.add(textFieldPreco);
		textFieldPreco.setColumns(10);
		
		JLabel lblPreco = new JLabel("Preco em R$");
		lblPreco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreco.setBounds(6, 269, 78, 16);
		panel.add(lblPreco);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(inputListener);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.setBounds(349, 4, 117, 29);
		panel.add(btnCancelar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(inputListener);
		btnSalvar.setActionCommand("salvar");
		btnSalvar.setBounds(145, 302, 182, 43);
		panel.add(btnSalvar);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        DAO.Close();
		    }
		});
		
		setVisible(true);
	}
	
	public void Edit(Medicine m) 
	{
		beingEdited = m;
		isEditing = true;
		
		textFieldFabricante.setText(m.getFabricante());
		textFieldNome.setText(m.getNome());
		textFieldPreco.setText(Float.toString(m.getPreco()));
		textFieldPrincipioAtivo.setText(m.getPrincipioAtivo());
		
		if (m.getTipoMedicamento() == TipoMedicamento.COMUM) rdbtnComum.setSelected(true);
		else if (m.getTipoMedicamento() == TipoMedicamento.CONTROLADO) rdbtnControlado.setSelected(true);
		else if (m.getTipoMedicamento() == TipoMedicamento.GENERICO) rdbtnGenerico.setSelected(true);
		else rdbtnManipulado.setSelected(true);
	}
	
	public String[] getFields() throws MissingTypeException
	{
		String[] r = new String[5];
		
		r[0] = textFieldNome.getText();
		r[1] = textFieldFabricante.getText();
		r[2] = textFieldPrincipioAtivo.getText();
		
		if (rdbtnComum.isSelected()) r[3] = "COMUM";
		else if (rdbtnControlado.isSelected()) r[3] = "CONTROLADO";
		else if (rdbtnGenerico.isSelected()) r[3] = "GENERICO";
		else if (rdbtnManipulado.isSelected()) r[3] = "MANIPULADO";
		else throw new MissingTypeException();
		
		r[4] = textFieldPreco.getText();
		
		return r;
	}
	
	@Override
	public void setVisible(boolean b) 
	{
		isEditing = false;
		textFieldFabricante.setText("");
		textFieldNome.setText("");
		textFieldPreco.setText("");
		textFieldPrincipioAtivo.setText("");
		super.setVisible(b);
	}
}
