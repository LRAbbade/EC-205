package view;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import control.InputListenerRegister;
import model.DAO;
import model.User;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class RegisterScreen extends JFrame 
{
	public boolean isEditting;
	public User beingEditted;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldCargo;
	private JTextField textFieldAnoNascimento;
	private JTextField textFieldCpf;
	private JTextField textFieldRua;
	private JTextField textFieldBairro;
	private JTextField textFieldCidade;
	private JTextField textFieldTelefone;
	private InputListenerRegister inputListener;

	/**
	 * Create the frame.
	 */
	public RegisterScreen() 
	{
		inputListener = new InputListenerRegister(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblRegisterNewUser = new JLabel("Registrar novo usuario");
		lblRegisterNewUser.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		lblRegisterNewUser.setBounds(16, 6, 140, 26);
		panel.add(lblRegisterNewUser);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(87, 44, 353, 26);
		panel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(87, 82, 353, 26);
		panel.add(textFieldPassword);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(26, 52, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Senha");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblPassword.setBounds(26, 87, 61, 16);
		panel.add(lblPassword);
		
		JButton btnRegister = new JButton("Salvar");
		btnRegister.addActionListener(inputListener);
		btnRegister.setActionCommand("salvar");
		btnRegister.setBounds(135, 362, 167, 39);
		panel.add(btnRegister);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCargo.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblCargo.setBounds(26, 125, 61, 16);
		panel.add(lblCargo);
		
		textFieldCargo = new JTextField();
		textFieldCargo.setText("");
		textFieldCargo.setColumns(10);
		textFieldCargo.setBounds(87, 120, 353, 26);
		panel.add(textFieldCargo);
		
		JLabel lblAnoDeNascimento = new JLabel("Ano de Nascimento");
		lblAnoDeNascimento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnoDeNascimento.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblAnoDeNascimento.setBounds(16, 161, 107, 16);
		panel.add(lblAnoDeNascimento);
		
		textFieldAnoNascimento = new JTextField();
		textFieldAnoNascimento.setBounds(135, 153, 305, 26);
		panel.add(textFieldAnoNascimento);
		textFieldAnoNascimento.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblCpf.setBounds(16, 197, 107, 16);
		panel.add(lblCpf);
		
		textFieldCpf = new JTextField();
		textFieldCpf.setColumns(10);
		textFieldCpf.setBounds(135, 189, 305, 26);
		panel.add(textFieldCpf);
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRua.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblRua.setBounds(26, 230, 61, 16);
		panel.add(lblRua);
		
		textFieldRua = new JTextField();
		textFieldRua.setText("");
		textFieldRua.setColumns(10);
		textFieldRua.setBounds(87, 225, 353, 26);
		panel.add(textFieldRua);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBairro.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblBairro.setBounds(26, 263, 61, 16);
		panel.add(lblBairro);
		
		textFieldBairro = new JTextField();
		textFieldBairro.setText("");
		textFieldBairro.setColumns(10);
		textFieldBairro.setBounds(87, 258, 353, 26);
		panel.add(textFieldBairro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCidade.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblCidade.setBounds(26, 296, 61, 16);
		panel.add(lblCidade);
		
		textFieldCidade = new JTextField();
		textFieldCidade.setText("");
		textFieldCidade.setColumns(10);
		textFieldCidade.setBounds(87, 291, 353, 26);
		panel.add(textFieldCidade);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setText("");
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(87, 324, 353, 26);
		panel.add(textFieldTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefone.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblTelefone.setBounds(26, 329, 61, 16);
		panel.add(lblTelefone);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(inputListener);
		btnCancel.setActionCommand("cancelar");
		btnCancel.setBounds(323, 4, 117, 29);
		panel.add(btnCancel);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
		    {
		        DAO.Close();
		    }
		});
		
		setVisible(true);
	}
	
	@Override
	public void setVisible(boolean b) 
	{
		isEditting = false;
		textFieldPassword.setText("");
		textFieldUsername.setText("");
		textFieldCargo.setText("");
		textFieldRua.setText("");
		textFieldBairro.setText("");
		textFieldCidade.setText("");
		textFieldTelefone.setText("");
		textFieldAnoNascimento.setText("");
		textFieldCpf.setText("");
		super.setVisible(b);
	}
	
	public void editUser(User u) 
	{
		textFieldAnoNascimento.setText((new Integer(u.getBirthYear())).toString());
		textFieldBairro.setText(u.getBairro());
		textFieldCargo.setText(u.getCargo());
		textFieldCidade.setText(u.getCidade());
		textFieldCpf.setText((new Integer(u.getCpf())).toString());
		textFieldPassword.setText(u.getSenha());
		textFieldRua.setText(u.getRua());
		textFieldTelefone.setText(u.getTelefone());
		textFieldUsername.setText(u.getNome());
		isEditting = true;
		beingEditted = u;
	}
	
	public void doEditUser() 
	{
		 beingEditted.setNome(textFieldUsername.getText());
		 beingEditted.setSenha(textFieldPassword.getText());
		 beingEditted.setCargo(textFieldCargo.getText());
		 beingEditted.setRua(textFieldRua.getText());
		 beingEditted.setBairro(textFieldBairro.getText());
		 beingEditted.setCidade(textFieldCidade.getText()); 
		 beingEditted.setTelefone(textFieldTelefone.getText());
		 beingEditted.setBirthYear(new Integer(textFieldAnoNascimento.getText()));
		 beingEditted.setCpf(textFieldCpf.getText());
	}
	
	public String[] getTextFields() 
	{
		String[] r = new String[9];
		
		r[0] = textFieldUsername.getText();
		r[1] = textFieldPassword.getText();
		r[2] = textFieldCargo.getText();
		r[3] = textFieldRua.getText();
		r[4] = textFieldBairro.getText();
		r[5] = textFieldCidade.getText();
		r[6] = textFieldTelefone.getText();
		r[7] = textFieldAnoNascimento.getText();
		r[8] = textFieldCpf.getText();
		
		return r;
	}
	
}
