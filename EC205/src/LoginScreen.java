import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUser;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		lblNewLabel.setBounds(33, 19, 61, 16);
		panel.add(lblNewLabel);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(113, 61, 389, 26);
		panel.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(113, 99, 389, 26);
		panel.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("User");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(33, 66, 61, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(33, 104, 61, 16);
		panel.add(lblNewLabel_2);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					signIn();
					setVisible(false);
				} catch (UnknownUserException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnSignIn.setBounds(33, 137, 170, 46);
		panel.add(btnSignIn);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!signUp()) JOptionPane.showMessageDialog(null, "User already exists");
					else JOptionPane.showMessageDialog(null, "User registered.");
				} catch (InvalidPasswordException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				} catch (InvalidUsernameException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnSignUp.setBounds(332, 137, 170, 46);
		panel.add(btnSignUp);
		
		setVisible(true);
	}
	
	private void signIn() throws UnknownUserException
	{
		String username = textFieldUser.getText();
		String password = textFieldPassword.getText();
		
		if (username.equals("admin") && password.equals("admin")) Main.openAdminScreen();
		else if (Main.findUser(username, password)) Main.openUserScreen();
		else throw new UnknownUserException();
	}
	
	private boolean signUp() throws InvalidPasswordException, InvalidUsernameException
	{
		String username = textFieldUser.getText();
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
	
	@Override
	public void setVisible(boolean b) 
	{
		textFieldPassword.setText("");
		textFieldUser.setText("");
		super.setVisible(b);
	}
}
