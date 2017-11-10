package view;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import control.InputListenerLogin;
import model.DAO;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LoginScreen extends JFrame 
{
	private JPanel contentPane;
	private JTextField textFieldUser;
	private JTextField textFieldPassword;
	private InputListenerLogin inputListener;

	/**
	 * Create the frame.
	 */
	public LoginScreen() 
	{	
		inputListener = new InputListenerLogin(this);
		
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
		textFieldUser.setBounds(148, 61, 354, 26);
		panel.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(148, 99, 354, 26);
		panel.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome de usuario");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(6, 66, 130, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(33, 104, 103, 16);
		panel.add(lblNewLabel_2);
		
		JButton btnSignIn = new JButton("Entrar");
		btnSignIn.addActionListener(inputListener);
		btnSignIn.setActionCommand("entrar");
		btnSignIn.setBounds(183, 137, 170, 46);
		panel.add(btnSignIn);
		
		getRootPane().setDefaultButton(btnSignIn);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        DAO.Close();
		    }
		});
		
		setVisible(true);
	}
	
	public String[] getTextFields() 
	{
		String[] r = new String[2];
		
		r[0] = textFieldUser.getText();
		r[1] = textFieldPassword.getText();
		
		return r;
	}
	
	@Override
	public void setVisible(boolean b) 
	{
		textFieldPassword.setText("");
		textFieldUser.setText("");
		super.setVisible(b);
	}
	
}
