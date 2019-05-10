package implementacao;

import java.util.HashSet;
import java.util.Map.Entry;

import java.util.Set;

import implementacao.modelo.Lado;
import implementacao.modelo.Pessoa;
import interfaces.Acao;
import interfaces.Estado;
import interfaces.Problema;

public class ProblemaImp implements Problema {

	private final FuncaoSucessorImp fs;
	private final EstadoImp inicio;
	private final TesteDeObjetivoImp testObjetivo;

	public ProblemaImp() {
		Pessoa p1 = new Pessoa("A", 1);
		Pessoa p2 = new Pessoa("B", 2);
		Pessoa p3 = new Pessoa("C", 5);
		Pessoa p4 = new Pessoa("D", 8);
		HashSet<Pessoa> pi = new HashSet<>();
		HashSet<Pessoa> pf = new HashSet<>();
		pi.add(p1);
		pi.add(p2);
		pi.add(p3);
		pi.add(p4);

		this.fs = new FuncaoSucessorImp();
		this.inicio = new EstadoImp(0, pi, pf, Lado.INICIO);
		this.testObjetivo = new TesteDeObjetivoImp();
	}

	@Override
	public Set<Acao> acoes(Estado e) {
		return this.fs.acoes(e);
	}

	@Override
	public double custo(Acao a, Estado e) {
		  return a.custo(e);
	}

	@Override
	public Estado estadoInicial() {
		 return this.inicio;
	}

	@Override
	public Estado resultado(Acao a, Estado e) {
		  return a.resultado(e);
	}

	@Override
	public Set<Entry<Acao, Estado>> sucessores(Estado e) {
		return this.fs.sucessores(e);
	}

	@Override
	public boolean testaObjetivo(Estado e) {
		  return this.testObjetivo.ehObjetivo(e);
	}

}
