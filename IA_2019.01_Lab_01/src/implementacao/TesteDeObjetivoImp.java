package implementacao;

import java.util.HashSet;

import implementacao.modelo.Lado;
import implementacao.modelo.Pessoa;
import interfaces.Estado;
import interfaces.TesteDeObjetivo;

public class TesteDeObjetivoImp implements TesteDeObjetivo{
	
	private EstadoImp objetivo;
	
	public TesteDeObjetivoImp() {
		Pessoa p1 = new Pessoa("A", 1);
		Pessoa p2 = new Pessoa("B", 2);
		Pessoa p3 = new Pessoa("C", 5);
		Pessoa p4 = new Pessoa("D", 8);
		HashSet<Pessoa> pi = new HashSet<>();
		HashSet<Pessoa> pf = new HashSet<>();
		pf.add(p1);
		pf.add(p2);
		pf.add(p3);
		pf.add(p4);
    	
		this.objetivo = new EstadoImp(15, pi, pf, Lado.FIM);
	}

	@Override
	public boolean ehObjetivo(Estado e) {
		return this.objetivo.equals(e);
	}

}
