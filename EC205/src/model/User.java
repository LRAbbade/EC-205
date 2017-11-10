package model;
import javax.swing.JOptionPane;

public class User implements Comparable<User>
{
	static int id;
	private String nome, senha, cargo, rua, bairro, cidade, telefone, cpf;
	private int personalId, birthYear;
	
	public static void setId(int lastId) 
	{
		id = ++lastId;
	}
	
	public User(String nome, String senha, String cargo, String rua, String bairro, 
				String cidade, String telefone, int birthYear, String cpf) 
	{
		personalId = id++;
		this.nome = nome;
		this.senha = senha;
		this.cargo = cargo;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.telefone = telefone;
		this.birthYear = birthYear;
		this.cpf = cpf;
	}
	
	/**
	 * Construtor para re-criar o usuario a partir da linha da tabela
	 * @param info informacoes do usuario
	 */
	public User(Object[] info) 
	{
		String[] arr = new String[info.length];
		
		for (int i = 0; i < info.length; i++) 
		{
			arr[i] = (String)info[i];
		}
		
		personalId = new Integer(arr[0]);
		nome = arr[1];
		senha = arr[2];
		cargo = arr[3];
		rua = arr[4];
		bairro = arr[5];
		cidade = arr[6];
		telefone = arr[7];
		birthYear = new Integer(arr[8]);
		cpf = arr[9];
	}
	
	/**
	 * Constructor for building User from file line.
	 * Parameters in line should be separated by '#'
	 * @param line
	 */
	public User(String line) 
	{
		try {
			String[] arr = line.split("#");
			
			personalId = new Integer(arr[0]);
			nome = arr[1];
			senha = arr[2];
			
			if (arr.length == 3) 
			{
				cargo = rua = bairro = cidade = telefone = cpf = "";
				birthYear = 0;
			}
			else 
			{
				cargo = arr[3];
				rua = arr[4];
				bairro = arr[5];
				cidade = arr[6];
				telefone = arr[7];
				birthYear = new Integer(arr[8]);
				cpf = arr[9];
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error building user.");
		}
	}
	
	public String getNome() { return nome; }
	public String getSenha() { return senha; }
	public String getCargo() { return cargo; }
	public String getRua() { return rua; }
	public String getBairro() { return bairro; }
	public String getCidade() { return cidade; }
	public String getTelefone() { return telefone; }
	public int getPersonalId() { return personalId; }
	public int getBirthYear() { return birthYear; }
	public String getCpf() { return cpf; }
	
	public void setNome(String nome) { this.nome = nome; }
	public void setSenha(String senha) { this.senha = senha; }
	public void setCargo(String cargo) { this.cargo = cargo; }
	public void setRua(String rua) { this.rua = rua; }
	public void setBairro(String bairro) { this.bairro = bairro; }
	public void setCidade(String cidade) { this.cidade = cidade; }
	public void setTelefone(String telefone) { this.telefone = telefone; }
	public void setBirthYear(int birthYear) { this.birthYear = birthYear; }
	public void setCpf(String cpf) { this.cpf = cpf; }
	
	@Override
	public String toString() 
	{
		return (new Integer(personalId)).toString() + "#" + nome + "#" + senha + "#" + cargo + "#" + 
				rua + "#" + bairro + "#" + cidade + "#" + telefone +
				"#" + (new Integer(birthYear)).toString() + "#" + (new Integer(cpf)).toString();
	}
	
	@Override
	public int compareTo(User o) {
		if (personalId < o.personalId) return -1;
		else return 1;
	}
	
	public Object[] getInfoAsObject() 
	{
		Object[] r = (Object[])toString().split("#");
		return r;
	}
	
	public static Object[] GetAtributes() 
	{
		Object[] r = {"ID", "Nome", "Senha", "Cargo", "Rua", 
				"Bairro", "Cidade", "Telefone", "Nascido", "CPF"};
		
		return r;
	}
}
