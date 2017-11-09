package view;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import control.InputListenerUser;
import model.DAO;
import model.Medicine;
import model.Table;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class UserScreen extends JFrame 
{
	private JPanel contentPane;
	private InputListenerUser inputListener;
	private JButton btnEditarMedicamento;
	private JButton btnDeletarMedicamento;
	private Table table;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public UserScreen() 
	{
		inputListener = new InputListenerUser(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Funcionario");
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 16));
		lblNewLabel.setBounds(6, 6, 115, 35);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Trocar Usuario");
		btnNewButton.addActionListener(inputListener);
		btnNewButton.setActionCommand("trocar");
		btnNewButton.setBounds(860, 6, 124, 29);
		panel.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 124, 978, 338);
		panel.add(scrollPane);
		
		JLabel lblMedicamentos = new JLabel("Medicamentos");
		lblMedicamentos.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMedicamentos.setFont(new Font("Myriad Pro", Font.PLAIN, 14));
		lblMedicamentos.setBounds(6, 92, 121, 29);
		panel.add(lblMedicamentos);
		
		btnDeletarMedicamento = new JButton("Deletar Medicamento");
		btnDeletarMedicamento.addActionListener(inputListener);
		btnDeletarMedicamento.setActionCommand("deletar");
		btnDeletarMedicamento.setBounds(793, 71, 191, 41);
		panel.add(btnDeletarMedicamento);
		
		btnEditarMedicamento = new JButton("Editar Medicamento");
		btnEditarMedicamento.addActionListener(inputListener);
		btnEditarMedicamento.setActionCommand("editar");
		btnEditarMedicamento.setBounds(606, 71, 175, 41);
		panel.add(btnEditarMedicamento);
		
		JButton btnAdicionarMedicamento = new JButton("Adicionar Medicamento");
		btnAdicionarMedicamento.addActionListener(inputListener);
		btnAdicionarMedicamento.setActionCommand("adicionar");
		btnAdicionarMedicamento.setBounds(377, 71, 217, 41);
		panel.add(btnAdicionarMedicamento);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        DAO.Close();
		    }
		});
		
		setVisible(true);
	}
	
	public Object[] getSelectedRow() throws ArrayIndexOutOfBoundsException
	{
		Object[] r = new Object[table.getColumnCount()];
		int row = table.getSelectedRow();
		
		for (int i = 0; i < r.length; i++) 
		{
			r[i] = table.getValueAt(row, i);
		}
		
		return r;
	}
	
	public void setEditAndDeleteButton() 
	{
		boolean b = table.getRowCount() > 0;
		btnDeletarMedicamento.setEnabled(b);
		btnEditarMedicamento.setEnabled(b);
	}
	
	public void setTable() 
	{
		table = new Table(DAO.List("medicine"), Medicine.GetAtributes());
        int[] sizes = {30, 200, 200, 200, 200, 170};
        table.setTableColumns(sizes);
        scrollPane.setViewportView(table);
        table.setFillsViewportHeight(true);
	}
	
	@Override
	public void setVisible(boolean b) 
	{
		setTable();
		setEditAndDeleteButton();
		super.setVisible(b);
	}
}
