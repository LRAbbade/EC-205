package model;

import javax.swing.JOptionPane;

public class Medicine 
{
	static int id;
	private int personalId;
	private String nome;
	private String fabricante;
	private String principioAtivo;
	private TipoMedicamento tipoMedicamento;
	private float preco;
	
	public int getPersonalId() { return personalId; }
	public String getNome() { return nome; }
	public String getFabricante() { return fabricante; }
	public String getPrincipioAtivo() { return principioAtivo; }
	public TipoMedicamento getTipoMedicamento() { return tipoMedicamento; }
	public float getPreco() { return preco; }
	
	public void setPersonalId(int id) { personalId = id; }
	public void setNome(String nome) { this.nome = nome; }
	public void setFabricante(String fabricante) { this.fabricante = fabricante; }
	public void setPrincipioAtivo(String principioAtivo) { this.principioAtivo = principioAtivo; }
	public void setTipoMedicamento(TipoMedicamento tipoMedicamento) { this.tipoMedicamento = tipoMedicamento; }
	public void setPreco(float preco) { this.preco = preco; }
	
	public Medicine(String nome, String fabricante, String principioAtivo, 
			TipoMedicamento tipoMedicamento, float preco) 
	{
		this(id++, nome, fabricante, principioAtivo, tipoMedicamento, preco);
	}
	
	public Medicine(int idd, String nome, String fabricante, String principioAtivo, 
			TipoMedicamento tipoMedicamento, float preco) 
	{
		personalId = idd;
		this.nome = nome;
		this.fabricante = fabricante;
		this.principioAtivo = principioAtivo;
		this.tipoMedicamento = tipoMedicamento;
		this.preco = preco;
	}
	
	public Medicine(String line) 
	{
		String[] arr = line.split("#");
		
		personalId = new Integer(arr[0]);
		nome = arr[1];
		fabricante = arr[2];
		principioAtivo = arr[3];
		preco = new Float(arr[5]);
		
		if (arr[4].equals("CONTROLADO")) tipoMedicamento = TipoMedicamento.CONTROLADO;
		else if (arr[4].equals("COMUM")) tipoMedicamento = TipoMedicamento.COMUM;
		else if (arr[4].equals("GENERICO")) tipoMedicamento = TipoMedicamento.GENERICO;
		else tipoMedicamento = TipoMedicamento.MANIPULADO;
	}
	
	public Medicine(int idd, String nome) 
	{
		personalId = idd;
		this.nome = nome;
		fabricante = principioAtivo = "";
		tipoMedicamento = null;
		preco = 0;
	}
	
	public Medicine(String[] arr) throws ArrayIndexOutOfBoundsException
	{
		this(arr, ++id);
	}
	
	public Medicine(String[] arr, int idd) throws ArrayIndexOutOfBoundsException
	{
		personalId = idd;
		nome = arr[0];
		fabricante = arr[1];
		principioAtivo = arr[2];
		
		if (arr[3].equals("COMUM")) tipoMedicamento = TipoMedicamento.COMUM;
		else if (arr[3].equals("CONTROLADO")) tipoMedicamento = TipoMedicamento.CONTROLADO;
		else if (arr[3].equals("GENERICO")) tipoMedicamento = TipoMedicamento.GENERICO;
		else tipoMedicamento = TipoMedicamento.MANIPULADO;
		
		try {
			preco = new Float(arr[4]);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Preco incorreto", "Erro", JOptionPane.ERROR_MESSAGE);
			preco = 0;
		}
	}
	
	public Object[] getInfoAsObject() 
	{
		return (Object[])toString().split("#");
	}
	
	public static void setClassId(int id) 
	{
		Medicine.id = id;
	}
	
	public static Object[] GetAtributes() 
	{
		Object[] r = {"ID", "Nome", "Fabricante", "Principio Ativo", 
					"Tipo", "Preco"};
		
		return r;
	}
	
	@Override
	public String toString() 
	{
		return (Integer.toString(personalId)) + "#" +
				nome + "#" +
				fabricante + "#" +
				principioAtivo + "#" +
				tipoMedicamento.toString() + "#" +
				(Float.toString(preco));
	}
}
