package implementacao.modelo;

public class Par {
	private Pessoa pessoa1;
	private Pessoa pessoa2;

	public Par() {
	}

	public Pessoa getPessoa1() {
		return pessoa1;
	}

	public void setPessoa1(Pessoa pessoa1) {
		this.pessoa1 = pessoa1;
	}

	public Pessoa getPessoa2() {
		return pessoa2;
	}

	public void setPessoa2(Pessoa pessoa2) {
		this.pessoa2 = pessoa2;
	}

	public boolean contem(Pessoa p) {
		if (this.pessoa1 == p || this.pessoa2 == p) {
			return true;
		}
		return false;
	}

	public void addPessoa(Pessoa p) {
		if (this.pessoa1 == null) {
			this.pessoa1 = p;
			//System.out.println("p1="+this.pessoa1);
		} else if (this.pessoa2 == null) {
			this.pessoa2 = p;
			//System.out.println("p2="+this.pessoa2);
		}
	}

	@Override
	public String toString() {
		return "[" + pessoa1 + ", " + pessoa2 + "]";
	}
}
