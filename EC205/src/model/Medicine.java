package model;

public class Medicine 
{
	static int id;
	private int personalId;
	private String nome;
	private String fabricante;
	private String principioAtivo;
	private TipoMedicamento tipoMedicamento;
	private float preco;
	
	public int getId() { return personalId; }
	public String getNome() { return nome; }
	public String getFabricante() { return fabricante; }
	public String getPrincipioAtivo() { return principioAtivo; }
	public TipoMedicamento getTipoMedicamento() { return tipoMedicamento; }
	public float getPreco() { return preco; }
	
	public void setId(int id) { personalId = id; }
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
	
	public Medicine(String nome) 
	{
		this(id++, nome);
	}
	
	public Medicine(int idd, String nome) 
	{
		personalId = idd;
		this.nome = nome;
		fabricante = principioAtivo = "";
		tipoMedicamento = null;
		preco = 0;
	}
	
	public Object[] getInfoAsObject() 
	{
		// TODO
		return null;
	}
	
	public static void setClassId(int id) 
	{
		Medicine.id = id;
	}
	
	public static Object[] GetAtributes() 
	{
		// TODO
		return null;
	}
	
	@Override
	public String toString() 
	{
		// TODO
		return null;
	}
}
