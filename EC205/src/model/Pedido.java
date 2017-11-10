package model;

public class Pedido 
{
	static int id;
	private int personalId;
	private int quantidade;
	private String nomeMedicamento;
	private float total;
	
	public Pedido(String line) 
	{
		String[] arr = line.split("#");
		
		personalId = new Integer(arr[0]);
		nomeMedicamento = arr[1];
		quantidade = new Integer(arr[2]);
		total = new Float(arr[3]);
	}
	
	public Pedido(String nome, int quantidade, float total) 
	{
		personalId = ++id;
		nomeMedicamento = nome;
		this.quantidade = quantidade;
		this.total = total;
	}
	
	public void setPersonalId(int idd) { personalId = idd; }
	public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
	public void setNomeMedicamento(String nomeMedicamento) { this.nomeMedicamento = nomeMedicamento; }
	public void setTotal(float total) { this.total = total; }
	
	public int getPersonalId() { return personalId; }
	public int getQuantidade() { return quantidade; }
	public String getNomeMedicamento() { return nomeMedicamento; }
	public float getTotal() { return total; }
	
	public static void setClassId(int idd) 
	{
		id = idd;
	}
	
	public Object[] getInfoAsObject() 
	{
		return (Object[])toString().split("#");
	}
	
	public static Object[] GetAtributes() 
	{
		Object[] r = { "ID", "Nome do medicamento", "Quantidade", "Total em reais" };
		return r;
	}
	
	@Override
	public String toString() 
	{
		return Integer.toString(personalId) + "#" +
				nomeMedicamento + "#" + 
				Integer.toString(quantidade) + "#" + 
				Float.toString(total);
	}
}
