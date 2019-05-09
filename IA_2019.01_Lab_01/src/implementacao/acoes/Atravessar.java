package implementacao.acoes;

import java.util.HashSet;
import java.util.Random;

import implementacao.EstadoImp;
import implementacao.modelo.Lado;
import implementacao.modelo.Par;
import implementacao.modelo.Pessoa;
import interfaces.Estado;

public class Atravessar extends AbstractAcao{
	// set de pessoas
	private Par par;
	// lado torcha?
	private Lado lado;
	
	public Atravessar(Par par, Lado lado) {
		super();
		this.par = par;
		this.lado = lado;
	}
	
	public Par getPar() {
		return par;
	}
	public void setPar(Par par) {
		this.par = par;
	}
	
	public Lado getLado() {
		return lado;
	}
	public void setLado(Lado lado) {
		this.lado = lado;
	}

	@Override
	public Estado resultado(Estado e) {
		EstadoImp estadoAtual = (EstadoImp)e;
		
		HashSet<Pessoa> pessoasNoInicio = estadoAtual.getPessoasNoInicio();		
		HashSet<Pessoa> pessoasNoFim = 	estadoAtual.getPessoasNoFim();
		int tempoDercorrido = estadoAtual.getTempoDercorrido();
		Lado ladoLocalizacaoLanterna = estadoAtual.getLadoLocalizacaoLanterna();
		
		if(this.lado == Lado.INICIO) {
			int t = this.movePessoaIF(pessoasNoInicio, pessoasNoFim);
			tempoDercorrido += t;
			ladoLocalizacaoLanterna = Lado.FIM;
		} else {
			int t = this.movePessoaFI(pessoasNoInicio, pessoasNoFim);
			tempoDercorrido += t;
			ladoLocalizacaoLanterna = Lado.INICIO;
		}
		
		EstadoImp novoEstado = new EstadoImp(
			tempoDercorrido, 
			pessoasNoInicio, 
			pessoasNoFim, 
			ladoLocalizacaoLanterna
		);
		return novoEstado;
	}
	
	private int movePessoaIF(HashSet<Pessoa> inicio, HashSet<Pessoa> fim) {
		Pessoa p1 = this.getPessoa(inicio, this.par.getPessoa1());
		Pessoa p2= this.getPessoa(inicio, this.par.getPessoa2());
		int tempo = 0;
		if(p1 != null) {
			inicio.remove(p1);
			fim.add(p1);
			tempo = p1.getTempoDeTravessia();
		}
		if(p2 != null) {
			inicio.remove(p2);
			fim.add(p2);
			tempo = tempo < p2.getTempoDeTravessia() ? p2.getTempoDeTravessia() : tempo;
		}
		return tempo;
		
	}
	private int movePessoaFI(HashSet<Pessoa> inicio, HashSet<Pessoa> fim) {
		Pessoa p1 = this.getPessoa(fim, this.par.getPessoa1());
		Pessoa p2= this.getPessoa(fim, this.par.getPessoa2());
		int tempo = 0;
		if(p1 != null) {
			fim.remove(p1);
			inicio.add(p1);	
			tempo = p1.getTempoDeTravessia();
			
		}
		if(p2 != null) {
			fim.remove(p2);
			inicio.add(p2);	
			tempo = tempo < p2.getTempoDeTravessia() ? p2.getTempoDeTravessia() : tempo;
		}
		return tempo;
	}
	
	private Pessoa getPessoa(HashSet<Pessoa> set, String nome) {
		if (nome == null) return null;	
		return set.stream().filter(p -> p.getNome() == nome).findFirst().orElse(null);
	
	}

	@Override
	public String toString() {
		return "Atravessar [\n \tpar=" + par.toString() + ", \n\tlado=" + lado.toString() + "\n]";
	}	
	
	
}
