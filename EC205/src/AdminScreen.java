import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class AdminScreen extends JFrame 
{
	private JPanel contentPane;
	private JList listUsers;

	/**
	 * Create the frame.
	 */
	public AdminScreen() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Screen");
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		lblNewLabel.setBounds(22, 20, 101, 16);
		panel.add(lblNewLabel);
		
		JButton btnChangeUser = new JButton("Change User");
		btnChangeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Main.changeUser();
			}
		});
		btnChangeUser.setBounds(400, 0, 117, 29);
		panel.add(btnChangeUser);
		
		JButton btnRegister = new JButton("Register New User");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.openRegisterScreen();
			}
		});
		btnRegister.setBounds(6, 48, 166, 46);
		panel.add(btnRegister);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Main.deleteUser(getSelectedUserId());
					JOptionPane.showMessageDialog(null, "User deleted");
				} catch (UnknownUserException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnDeleteUser.setBounds(351, 48, 166, 46);
		panel.add(btnDeleteUser);
		
		listUsers = new JList(getUsers());
		listUsers.setFont(new Font("Courier", Font.PLAIN, 12));
		listUsers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listUsers.setLayoutOrientation(JList.VERTICAL);
		listUsers.setVisibleRowCount(-1);
		
		JScrollPane scrollPane = new JScrollPane(listUsers);
		scrollPane.setBounds(6, 106, 511, 217);
		panel.add(scrollPane);
		
		JButton btnEditUser = new JButton("Edit User");
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// editar usuario
			}
		});
		btnEditUser.setBounds(184, 48, 155, 46);
		panel.add(btnEditUser);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        Main.Close();
		    }
		});
		
		setVisible(true);
	}
	
	private int getSelectedUserId() 
	{
		String line[] = listUsers.getSelectedValue().toString().split(",");
		return new Integer(line[0].substring(4));
	}
	
	private String[] getUsers() 
	{
		User[] users = Main.getUsersList();
		String[] r = new String[users.length];
		
		for (int i = 0; i < users.length; i++) 
		{
			r[i] = users[i].info();
		}
		
		return r;
	}
	
	@Override
	public void setVisible(boolean b) 
	{
		// reset necessary stuff
		super.setVisible(b);
	}
}
