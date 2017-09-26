import javax.swing.JOptionPane;

public class User implements Comparable<User>
{
	static int id;
	public String nome, senha, cargo, rua, bairro, cidade, telefone;
	public int personalId, birthYear, cpf;
	
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
	 * Constructor for building User from file line.
	 * Parameters in line should be separated by '#'
	 * @param line
	 */
	public User(String line) 
	{
		try {
			String[] arr = line.split("#");
			
			nome = arr[0];
			senha = arr[1];
			
			if (arr.length == 2) 
			{
				cargo = rua = bairro = cidade = telefone = "";
				birthYear = cpf = 0;
			}
			else 
			{
				cargo = arr[2];
				rua = arr[3];
				bairro = arr[4];
				cidade = arr[5];
				telefone = arr[6];
				birthYear = new Integer(arr[7]);
				cpf = new Integer(arr[8]);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error building user.");
		}
	}
	
	@Override
	public String toString() 
	{
		return nome + "#" + senha + "#" + cargo + "#" + rua + "#" + bairro + "#" + cidade + "#" + telefone +
				"#" + (new Integer(birthYear)).toString() + "#" + (new Integer(cpf)).toString();
	}
	
	@Override
	public int compareTo(User o) {
		if (personalId < o.personalId) return -1;
		else return 1;
	}
}
