package implementacao;

import java.util.Map.Entry;

import implementacao.acoes.Atravessar;
import implementacao.modelo.Lado;
import implementacao.modelo.Par;
import implementacao.modelo.Pessoa;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import interfaces.Acao;
import interfaces.Estado;
import interfaces.FuncaoSucessor;

public class FuncaoSucessorImp implements FuncaoSucessor {

	private static List<Atravessar> possiveisAcoes;

	public FuncaoSucessorImp() {
		if (this.possiveisAcoes == null) {
			this.possiveisAcoes = this.geraAcoes();
		}
	}

	/**
	 * @return lista de acoes com os pares possiveis
	 */
	private List<Atravessar> geraAcoes() {
		List<Atravessar> acoes = new ArrayList<>();
		List<Par> l = this.gerarParesTravessia();
		l.forEach(p -> acoes.add(new Atravessar(p)));
		return acoes;
	}

	/**
	 * @return lista de pares possiveis para atravessar
	 */
	private List<Par> gerarParesTravessia() {
		List<Par> pares = new ArrayList<>();
		List<Pessoa> pessoas = new ArrayList<>();
		pessoas.add(new Pessoa("A", 1));
		pessoas.add(new Pessoa("B", 2));
		pessoas.add(new Pessoa("C", 5));
		pessoas.add(new Pessoa("D", 8));

		for (int i = 0; i < pessoas.size(); i++) {
			Par p1 = new Par();
			p1.addPessoa(pessoas.get(i));
			pares.add(p1);
			for (int j = i + 1; j < pessoas.size(); j++) {
				Par p2 = new Par();
				p2.addPessoa(pessoas.get(i));
				p2.addPessoa(pessoas.get(j));
				pares.add(p2);
			}
		}

		return pares;
	}

	@Override
	public Set<Acao> acoes(Estado e) {
		Set<Acao> acoesValidas = new HashSet();
		for (Acao a : this.possiveisAcoes) {
			if (a.resultado(e).estadoValido()) {
				acoesValidas.add(a);
			}
		}
		return acoesValidas;
	}

	@Override
	public Set<Entry<Acao, Estado>> sucessores(Estado e) {
		Set<Map.Entry<Acao, Estado>> set = new HashSet<>();
		Map.Entry<Acao, Estado> tupla = null;

		for (Acao a : this.possiveisAcoes) {
			Estado result = a.resultado(e);
			if (result.estadoValido()) {
				tupla = new AbstractMap.SimpleEntry(a, result);
				set.add(tupla);
			}
		}

		return set;
	}

	@Override
	public double custo(Acao a, Estado e) {
		return a.custo(e);
	}

}
