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

	@Override
	public Set<Acao> acoes(Estado e) {
		Set<Acao> acoesValidas = new HashSet();
		for (Acao a : this.geraAcoes((EstadoImp) e)) {

			if (a.resultado(e).estadoValido()) {
				// System.out.println("acao valida: "+a.toString());
				acoesValidas.add(a);
			}
		}

		return acoesValidas;
	}

	@Override
	public double custo(Acao a, Estado e) {
		return a.custo(e);
	}

	@Override
	public Set<Entry<Acao, Estado>> sucessores(Estado e) {
		Set<Map.Entry<Acao, Estado>> set = new HashSet<>();
		Map.Entry<Acao, Estado> tupla = null;

		for (Acao a : this.geraAcoes((EstadoImp) e)) {
			Estado result = a.resultado(e);
			
			if (result.estadoValido()) {
				tupla = new AbstractMap.SimpleEntry(a, result);
				/*
				System.out.println("=======================");
				System.out.println("estado inicial");
				System.out.println(e);
				System.out.println("acao");
				System.out.println(a.toString());
				System.out.println("estado resultado");
				System.out.println(result.toString());
				System.out.println("=======================");
				*/
				set.add(tupla);
			}
		}

		return set;
	}

	public List<Atravessar> geraAcoes(EstadoImp e) {
		List<Atravessar> acoes = new ArrayList<>();
		List<Par> l = this.gerarParesTravessia();
		l.forEach(p -> acoes.add(new Atravessar(p)));
		return acoes;
	}

	public List<Par> gerarParesTravessia() {
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
		// System.out.println(pares.toString());
		return pares;
	}
/*
	public static void main(String[] args) {
		FuncaoSucessorImp f = new FuncaoSucessorImp();
		Pessoa p1 = new Pessoa("A", 1);
		Pessoa p2 = new Pessoa("B", 2);
		Pessoa p3 = new Pessoa("C", 5);
		Pessoa p4 = new Pessoa("D", 8);
		HashSet<Pessoa> pi = new HashSet<>();
		HashSet<Pessoa> pf = new HashSet<>();
		pi.add(p1);
		pi.add(p2);
		//pi.add(p3);
		pi.add(p4);

		//pf.add(p1);
		// pf.add(p2);
		pf.add(p3);
		//pf.add(p4);

		Estado e = new EstadoImp(0, pi, pf, Lado.FIM);
		// f.comb();
		// f.acoes(e);
		f.sucessores(e);
	}
	*/
}
