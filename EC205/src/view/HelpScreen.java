package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.InputListenerHelp;
import control.Main;
import model.DAO;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class HelpScreen extends JFrame 
{
	private JPanel contentPane;
	private JButton btnVoltar;
	private JButton btnSwitch;
	private InputListenerHelp inputListener;
	private JLabel lblImagem;
	private JLabel lblTela;
	public boolean isShowingPedidos;

	public HelpScreen() 
	{
		inputListener = new InputListenerHelp(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1022, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblHelp = new JLabel("Ajuda");
		lblHelp.setFont(new Font("Myriad Pro", Font.PLAIN, 16));
		lblHelp.setVerticalAlignment(SwingConstants.TOP);
		lblHelp.setBounds(6, 6, 136, 39);
		panel.add(lblHelp);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(inputListener);
		btnVoltar.setActionCommand("voltar");
		btnVoltar.setBounds(891, 6, 117, 40);
		panel.add(btnVoltar);
		
		btnSwitch = new JButton("");
		btnSwitch.addActionListener(inputListener);
		btnSwitch.setActionCommand("switch");
		btnSwitch.setBounds(400, 6, 200, 40);
		panel.add(btnSwitch);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 103, 1002, 502);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblImagem = new JLabel("");
		panel_1.add(lblImagem, BorderLayout.CENTER);
		
		lblTela = new JLabel("Tela");
		lblTela.setFont(new Font("Myriad Pro", Font.PLAIN, 16));
		lblTela.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTela.setBounds(6, 57, 224, 34);
		panel.add(lblTela);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        DAO.Close();
		    }
		});
		
		setVisible(true, Main.userScreen.isInMedicineScreen ? "medicamento" : "pedidos");
	}
	
	private void showPedido() 
	{
		isShowingPedidos = true;
		btnSwitch.setText("Ajuda Medicamentos");
		lblTela.setText("Tela de pedidos:");
		
		// VAI DAR PAU EM OUTRO PC
		lblImagem.setIcon(new ImageIcon("/Users/LRAbbade/eclipse-workspace/EC205/images/Help_Pedidos.png"));
	}
	
	private void showMedicamento() 
	{
		isShowingPedidos = false;
		btnSwitch.setText("Ajuda Pedidos");
		lblTela.setText("Tela de medicamentos:");
		
		// VAI DAR PAU EM OUTRO PC
		lblImagem.setIcon(new ImageIcon("/Users/LRAbbade/eclipse-workspace/EC205/images/Help_Medicamentos.png"));
	}
	
	public void setVisible(boolean b, String type) 
	{
		if (type.equalsIgnoreCase("pedidos")) showPedido();
		else showMedicamento();
		
		super.setVisible(b);
	}
}
