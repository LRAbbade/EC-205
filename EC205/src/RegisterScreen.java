import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class RegisterScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen frame = new RegisterScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 214);
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
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(26, 52, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
		lblPassword.setBounds(26, 87, 61, 16);
		panel.add(lblPassword);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnRegister.setBounds(124, 120, 167, 39);
		panel.add(btnRegister);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        Main.Close();
		    }
		});
	}
	
	@Override
	public void setVisible(boolean b) {
		textFieldPassword.setText("");
		textFieldUsername.setText("");
		super.setVisible(b);
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
			Main.registerUser(username, password);
		} catch (UserAlreadyRegisteredException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
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
