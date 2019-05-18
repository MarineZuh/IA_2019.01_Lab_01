package implementacao.acoes;

import java.util.HashSet;
import java.util.Random;

import implementacao.EstadoImp;
import implementacao.modelo.Lado;
import implementacao.modelo.Par;
import implementacao.modelo.Pessoa;
import interfaces.Estado;

public class Atravessar extends AbstractAcao{

	private Par par;
	
	public Atravessar(Par par) {
		super();
		this.par = par;
	}
	
	public Par getPar() {
		return par;
	}
	public void setPar(Par par) {
		this.par = par;
	}
	

	@Override
	public Estado resultado(Estado e) {
		EstadoImp estadoAtual = (EstadoImp)e;
		// recolhe os dados do estado para manipulação
		HashSet<Pessoa> pessoasNoInicio = estadoAtual.getPessoasNoInicio();		
		HashSet<Pessoa> pessoasNoFim = 	estadoAtual.getPessoasNoFim();
		int tempoDercorrido = estadoAtual.getTempoDercorrido();
		Lado ladoLocalizacaoLanterna = estadoAtual.getLadoLocalizacaoLanterna();
		// executa a travessia de acordo com o lado em que a lanterna se encontra
		int t;
		if(ladoLocalizacaoLanterna == Lado.INICIO) {
			t = this.mover(pessoasNoInicio, pessoasNoFim);
			ladoLocalizacaoLanterna = Lado.FIM;
		} else {
			t = this.mover(pessoasNoFim, pessoasNoInicio);
			ladoLocalizacaoLanterna = Lado.INICIO;
		}
		
		tempoDercorrido += t;
		
		// se o tempo nao mudou, ninguem atravessou
		if(tempoDercorrido == estadoAtual.getTempoDercorrido()) {
			// retorna o mesmo estado, sem alterações
			return e;
		}
		// retorna um novo estado
		return new EstadoImp(
			tempoDercorrido, 
			pessoasNoInicio, 
			pessoasNoFim, 
			ladoLocalizacaoLanterna
		);
	}
	
	/**
	 * Executa a travessia de cada pessoa no Par da classe
	 * @return 0 se não foi possivel fazer a travessia 
	 * 	ou o maior tempo de travessia entre as pessoas que atravessaram 
	 */
	private Integer mover(HashSet<Pessoa> origem, HashSet<Pessoa> destino) {
		Pessoa p1 = this.par.getPessoa1();
		Pessoa p2 = this.par.getPessoa2();
		//se existir apenas a pessoa 1 no par
		if(p2 == null && origem.contains(p1)) {
			return this.movePessoa(origem, destino, p1);
		} else {
			//checa se eles estao na origem:
			if(origem.contains(p1) && origem.contains(p2)) {
				int tempoPessoa1 = this.movePessoa(origem, destino, p1);
				int tempoPessoa2 = this.movePessoa(origem, destino, p2);

				return (
					(tempoPessoa1 > tempoPessoa2 ? tempoPessoa1 : tempoPessoa2)
				);
			}
		}
		return 0; // não afeta o tempo dercorrido
		
	}
	
	/**
	 * Move uma pessoa da collection origem para a destino
	 * @return o tempo de travessia da pessoa
	 */
	private int movePessoa(HashSet<Pessoa> origem, HashSet<Pessoa> destino, Pessoa p) {
		origem.remove(p);
		destino.add(p);
		return p.getTempoDeTravessia();
	}
		
	
	@Override
	public String toString() {
		return "Atravessar  o par " + par.toString() + "";
	}	
	
	
}
