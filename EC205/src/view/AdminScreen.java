package view;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import control.InputListenerAdmin;
import control.Main;
import model.DAO;
import model.Table;
import model.User;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class AdminScreen extends JFrame 
{
	public Table table;
	private JPanel contentPane;
	private JButton btnDeleteUser;
	private JButton btnEditUser;
	private InputListenerAdmin inputListener;
	private JScrollPane scrollPane;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public AdminScreen() 
	{
		scrollPane = new JScrollPane();
		inputListener = new InputListenerAdmin(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Screen");
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		lblNewLabel.setBounds(22, 20, 101, 16);
		panel.add(lblNewLabel);
		
		JButton btnChangeUser = new JButton("Change User");
		btnChangeUser.addActionListener(inputListener);
		btnChangeUser.setActionCommand("change user");
		btnChangeUser.setBounds(867, 7, 117, 29);
		panel.add(btnChangeUser);
		
		JButton btnRegister = new JButton("Register New User");
		btnRegister.addActionListener(inputListener);
		btnRegister.setActionCommand("register");
		btnRegister.setBounds(6, 48, 166, 46);
		panel.add(btnRegister);
		
		btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(inputListener);
		btnDeleteUser.setActionCommand("delete");
		btnDeleteUser.setBounds(351, 48, 166, 46);
		panel.add(btnDeleteUser);
	
		setTable();
		
		scrollPane.setBounds(6, 106, 978, 356);
		panel.add(scrollPane);
		
		btnEditUser = new JButton("Edit User");
		btnEditUser.addActionListener(inputListener);
		btnEditUser.setActionCommand("edit");
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
	
	public User getSelectedUser() 
	{
		Object[] info = new Object[table.getColumnCount()];
		int row = table.getSelectedRow();
		
		for (int i = 0; i < info.length; i++)
		{
			info[i] = table.getValueAt(row, i);
		}
		
		return new User(info);
	}
	
	public int getSelectedUserId() 
	{
		return new Integer((String)table.getValueAt(table.getSelectedRow(), 0));
	}
	
	private void setDeleteAndEditButtonEnable() 
	{
		boolean b = table.getRowCount() > 0;
		btnDeleteUser.setEnabled(b);
		btnEditUser.setEnabled(b);
	}
	
	public void setTable() 
    {
    		table = new Table(DAO.List("user"), User.GetAtributes());
        int[] sizes = {30, 140, 120, 120, 160, 120, 100, 80, 50, 80};
        table.setTableColumns(sizes);
        scrollPane.setViewportView(table);
        table.setFillsViewportHeight(true);
    }
	
	@Override
	public void setVisible(boolean b) 
	{
		setTable();
		setDeleteAndEditButtonEnable();
		super.setVisible(b);
	}
}
