import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegisterScreen extends JFrame 
{
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
	private boolean isEditting;
	private User beingEditted;

	/**
	 * Create the frame.
	 */
	public RegisterScreen() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblRegisterNewUser = new JLabel("Register new user");
		lblRegisterNewUser.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		lblRegisterNewUser.setBounds(16, 6, 130, 26);
		panel.add(lblRegisterNewUser);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(87, 44, 353, 26);
		panel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(87, 82, 353, 26);
		panel.add(textFieldPassword);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(26, 52, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblPassword.setBounds(26, 87, 61, 16);
		panel.add(lblPassword);
		
		JButton btnRegister = new JButton("Save");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (isEditting) 
				{
					try {
						returnEdittedUser();
						JOptionPane.showMessageDialog(null, "User editted");
						setVisible(false);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					
					return;
				}
				
				try {
					if (!signUp()) JOptionPane.showMessageDialog(null, "User already registered");
					else 
					{
						JOptionPane.showMessageDialog(null, "User registered.");
						setVisible(false);
					}
				} catch (InvalidPasswordException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				} catch (InvalidUsernameException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
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
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Main.openAdminScreen();
				setVisible(false);
			}
		});
		btnCancel.setBounds(323, 4, 117, 29);
		panel.add(btnCancel);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        Main.Close();
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
		textFieldAnoNascimento.setText((new Integer(u.birthYear)).toString());
		textFieldBairro.setText(u.bairro);
		textFieldCargo.setText(u.cargo);
		textFieldCidade.setText(u.cidade);
		textFieldCpf.setText((new Integer(u.cpf)).toString());
		textFieldPassword.setText(u.senha);
		textFieldRua.setText(u.rua);
		textFieldTelefone.setText(u.telefone);
		textFieldUsername.setText(u.nome);
		isEditting = true;
		beingEditted = u;
	}
	
	private void returnEdittedUser() 
	{
		 beingEditted.nome = textFieldUsername.getText();
		 beingEditted.senha = textFieldPassword.getText();
		 beingEditted.cargo = textFieldCargo.getText();
		 beingEditted.rua = textFieldRua.getText();
		 beingEditted.bairro = textFieldBairro.getText();
		 beingEditted.cidade = textFieldCidade.getText(); 
		 beingEditted.telefone = textFieldTelefone.getText();
		 beingEditted.birthYear = new Integer(textFieldAnoNascimento.getText());
		 beingEditted.cpf = new Integer(textFieldCpf.getText());
		 
		 Main.returnEdittedUser(beingEditted);
	}

	private boolean signUp() throws InvalidPasswordException, InvalidUsernameException
	{
		String username = textFieldUsername.getText();
		String password = textFieldPassword.getText();
		
		if (username.equals("admin")) 
		{ 
			JOptionPane.showMessageDialog(null, "Cannot register as admin"); 
			return false;
		}
		
		if (!validUsername(username)) throw new InvalidUsernameException();
		if (!validPassword(password)) throw new InvalidPasswordException();
		if (Main.findUser(username, password)) return false;
		
		try {
			User u =	 new User(username, password, textFieldCargo.getText(),
												 textFieldRua.getText(), 
												 textFieldBairro.getText(),
												 textFieldCidade.getText(), 
												 textFieldTelefone.getText(),
												 new Integer(textFieldAnoNascimento.getText()),
												 new Integer(textFieldCpf.getText()));
			
			Main.registerUser(u.personalId, u); 
			
		} catch (UserAlreadyRegisteredException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao criar funcionário."); // melhorar depois
			return false;
		}
		
		return true;
	}
	
	private boolean validUsername(String s) 
	{
		for (int i = 0; i < s.length(); i++) 
		{
			char c = s.charAt(i);
			if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) return false;
		}
		
		return true;
	}
	
	private boolean validPassword(String s) 
	{
		if (s.length() < 5) return false;
		
		for (int i = 0; i < s.length(); i++) 
		{
			char c = s.charAt(i);
			if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z') && !(c >= '0' && c <= '9')) return false;
		}
		
		return true;
	}
}
