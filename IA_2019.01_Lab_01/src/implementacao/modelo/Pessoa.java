package implementacao.modelo;

public class Pessoa {
	private String nome;
	private int tempoDeTravessia;
	
	public Pessoa () {
		
	}



	public Pessoa(String nome, int tempoDeTravessia) {
		this.nome = nome;
		this.tempoDeTravessia = tempoDeTravessia;
	}

	public int getTempoDeTravessia() {
		return tempoDeTravessia;
	}

	public void setTempoDeTravessia(int tempoDeTravessia) {
		this.tempoDeTravessia = tempoDeTravessia;
	}

	@Override
	public String toString() {
		return "[\'" + nome + "\', " + tempoDeTravessia + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (nome == null) {
			if (other.nome == null) return true;
			return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}




	
	
	
}
