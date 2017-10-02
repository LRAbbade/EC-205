import javax.swing.JOptionPane;

public class User implements Comparable<User>
{
	static int id;
	private String nome, senha, cargo, rua, bairro, cidade, telefone;
	private int personalId, birthYear, cpf;
	
	public static void setId(int lastId) 
	{
		id = ++lastId;
	}
	
	public User(String nome, String senha, String cargo, String rua, String bairro, 
				String cidade, String telefone, int birthYear, int cpf) 
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
	 * Constructor for re-building the user from 'Info' String method
	 * @param info
	 */
	public User(String[] info) 
	{
		String[] arr = new String[info.length];
		
		for (int i = 0; i < info.length; i++) 
		{
			String[] aux = info[i].split(": ");
			arr[i] = aux[1].trim();
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
		cpf = new Integer(arr[9]);
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
				cargo = rua = bairro = cidade = telefone = "";
				birthYear = cpf = 0;
			}
			else 
			{
				cargo = arr[3];
				rua = arr[4];
				bairro = arr[5];
				cidade = arr[6];
				telefone = arr[7];
				birthYear = new Integer(arr[8]);
				cpf = new Integer(arr[9]);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error building user.");
		}
	}

	// Seguem 20 linhas de getters and setters INUTEIS abaixo:
	
	public String getNome() { return nome; }
	public String getSenha() { return senha; }
	public String getCargo() { return cargo; }
	public String getRua() { return rua; }
	public String getBairro() { return bairro; }
	public String getCidade() { return cidade; }
	public String getTelefone() { return telefone; }
	public int getPersonalId() { return personalId; }
	public int getBirthYear() { return birthYear; }
	public int getCpf() { return cpf; }
	
	public void setNome(String nome) { this.nome = nome; }
	public void setSenha(String senha) { this.senha = senha; }
	public void setCargo(String cargo) { this.cargo = cargo; }
	public void setRua(String rua) { this.rua = rua; }
	public void setBairro(String bairro) { this.bairro = bairro; }
	public void setCidade(String cidade) { this.cidade = cidade; }
	public void setTelefone(String telefone) { this.telefone = telefone; }
	public void setBirthYear(int birthYear) { this.birthYear = birthYear; }
	public void setCpf(int cpf) { this.cpf = cpf; }
	
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
	
	public String info() 
	{
		String[] arr = toString().split("#");

		return "ID: " + getStringWithSpaces(arr[0], 0) + 
				", nome: " + getStringWithSpaces(arr[1], 1) + 
				", senha: " + getStringWithSpaces(arr[2], 2) + 
				", cargo: " + getStringWithSpaces(arr[3], 3) + 
				", rua: " + getStringWithSpaces(arr[4], 4) + 
				", bairro: " + getStringWithSpaces(arr[5], 5) + 
				", cidade: " + getStringWithSpaces(arr[6], 6) + 
				", telefone: " + getStringWithSpaces(arr[7], 7) + 
				", ano de nascimento: " + getStringWithSpaces(arr[8], 8) + 
				", CPF: " + getStringWithSpaces(arr[9], 9);
	}
	
	private String getStringWithSpaces(String s, int pos) 
	{
		int spaces = Main.stringSizes[pos] - s.length();
		
		for (int i = 0; i < spaces; i++) 
		{
			s += " ";
		}
		
		return s;
	}
}
