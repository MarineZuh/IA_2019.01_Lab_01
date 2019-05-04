package implementacao.modelo;

public class Pessoa {
	private char nome;
	private int tempoDeTravessia;
	
	public Pessoa () {
		
	}

	public Pessoa(char nome, int tempoDeTravessia) {
		this.nome = nome;
		this.tempoDeTravessia = tempoDeTravessia;
	}

	public char getId() {
		return nome;
	}

	public void setId(char nome) {
		this.nome = nome;
	}

	public int getTempoDeTravessia() {
		return tempoDeTravessia;
	}

	public void setTempoDeTravessia(int tempoDeTravessia) {
		this.tempoDeTravessia = tempoDeTravessia;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + nome + ", tempoDeTravessia=" + tempoDeTravessia + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nome;
		result = prime * result + tempoDeTravessia;
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
		if (nome != other.nome)
			return false;
		if (tempoDeTravessia != other.tempoDeTravessia)
			return false;
		return true;
	}

	
	
	
}
