import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class AdminScreen extends JFrame 
{
	private JPanel contentPane;
	private JList<String> listUsers;
	private DefaultListModel<String> listModel;
	private JButton btnDeleteUser;
	private JButton btnEditUser;

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
		
		btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Main.deleteUser(getSelectedUserId());
					deleteSelectedFromList();
					JOptionPane.showMessageDialog(null, "User deleted");
				} catch (UnknownUserException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Invalid selection");
				}
			}
		});
		btnDeleteUser.setBounds(351, 48, 166, 46);
		panel.add(btnDeleteUser);
		
		listUsers = new JList<String>((listModel = getUsers()));
		listUsers.setFont(new Font("Courier", Font.PLAIN, 12));
		listUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listUsers.setLayoutOrientation(JList.VERTICAL);
		listUsers.setVisibleRowCount(-1);
		
		JScrollPane scrollPane = new JScrollPane(listUsers);
		scrollPane.setBounds(6, 106, 511, 217);
		panel.add(scrollPane);
		
		btnEditUser = new JButton("Edit User");
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				editUser();
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
	
	public void addUser(String u) 
	{
		listModel.addElement(u);
		listUsers.ensureIndexIsVisible(listModel.getSize() - 1);
		setDeleteAndEditButtonEnable();
	}
	
	private void editUser() 
	{
		User u = new User(listUsers.getSelectedValue().toString().split(","));
		Main.editUser(u);
	}
	
	public void returnEdittedUser(User u) 
	{
		int index = listUsers.getSelectedIndex();
		deleteSelectedFromList();
		listModel.add(index, u.info());
		listUsers.setSelectedIndex(index);
		listUsers.ensureIndexIsVisible(index);
		setDeleteAndEditButtonEnable();
	}
	
	private void deleteSelectedFromList() throws ArrayIndexOutOfBoundsException
	{
		int index = listUsers.getSelectedIndex();
		listModel.remove(index);
		setDeleteAndEditButtonEnable();
	}
	
	private int getSelectedUserId() 
	{
		String line[] = listUsers.getSelectedValue().toString().split(",");
		return new Integer(line[0].substring(4));
	}
	
	private DefaultListModel<String> getUsers() 
	{
		User[] users = Main.getUsersList();
		DefaultListModel<String> r = new DefaultListModel<>();
		
		for (int i = 0; i < users.length; i++) 
		{
			r.addElement(users[i].info());
		}
		
		return r;
	}
	
	private void setDeleteAndEditButtonEnable() 
	{
		boolean b = listModel.getSize() > 0;
		btnDeleteUser.setEnabled(b);
		btnEditUser.setEnabled(b);
	}
	
	@Override
	public void setVisible(boolean b) 
	{
		setDeleteAndEditButtonEnable();
		super.setVisible(b);
	}
}
