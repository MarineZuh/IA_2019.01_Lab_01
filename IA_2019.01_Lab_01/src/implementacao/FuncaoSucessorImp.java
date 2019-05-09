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
			
			if (result.estadoValido()) { // TODO if a.preCondicoesSatisfeitas()
				tupla = new AbstractMap.SimpleEntry(a, result);
				System.out.println("=======================");
				System.out.println("estado inicial");
				System.out.println(e);
				System.out.println("acao");
				System.out.println(a.toString());
				System.out.println("estado resultado");
				System.out.println(result.toString());
				System.out.println("=======================");
				set.add(tupla);
			}
		}

		return set;
	}

	public List<Atravessar> geraAcoes(EstadoImp e) {
		List<Atravessar> acoes = new ArrayList<>();

		List<Par> l = this.comb();
		// System.out.println("pares "+l.toString());
		if (e.getLadoLocalizacaoLanterna() == Lado.FIM) {
			l.forEach(par -> {

				Pessoa p1 = new Pessoa(par.getPessoa1(), 0);
				Pessoa p2 = new Pessoa(par.getPessoa2(), 0);
				if (e.getPessoasNoFim().contains(p1) && (p2.getNome() == null || e.getPessoasNoFim().contains(p2))) {
					acoes.add(new Atravessar(par, Lado.FIM));
				}
			});
		} else {
			l.forEach(par -> {

				Pessoa p1 = new Pessoa(par.getPessoa1(), 0);
				Pessoa p2 = new Pessoa(par.getPessoa2(), 0);

				if (e.getPessoasNoInicio().contains(p1)
						&& (p2.getNome() == null || e.getPessoasNoInicio().contains(p2))) {
					acoes.add(new Atravessar(par, Lado.INICIO));
				}
			});
		}
		// System.out.println("possiveis "+acoes);
		return acoes;
	}

	public List<Par> comb() {
		List<Par> pares = new ArrayList<>();
		String[] l1 = { "A", "B", "C", "D" };
		String[] l2 = { "A", "B", "C", "D" };

		for (int i = 0; i < 4; i++) {
			Par p1 = new Par();
			p1.addPessoa(l1[i]);
			pares.add(p1);
			for (int j = i + 1; j < 4; j++) {
				Par p2 = new Par();
				p2.addPessoa(l1[i]);
				p2.addPessoa(l2[j]);
				pares.add(p2);
			}
		}
		// System.out.println(pares.toString());
		return pares;
	}

	public static void main(String[] args) {
		FuncaoSucessorImp f = new FuncaoSucessorImp();
		Pessoa p1 = new Pessoa("A", 1);
		Pessoa p2 = new Pessoa("B", 2);
		Pessoa p3 = new Pessoa("C", 3);
		Pessoa p4 = new Pessoa("D", 4);
		HashSet<Pessoa> pi = new HashSet<>();
		HashSet<Pessoa> pf = new HashSet<>();
		// pi.add(p1);
		// pi.add(p2);
		pi.add(p3);
		pi.add(p4);

		pf.add(p1);
		pf.add(p2);
		// pf.add(p3);
		// pf.add(p4);

		Estado e = new EstadoImp(0, pi, pf, Lado.INICIO);
		// f.comb();
		// f.acoes(e);
		f.sucessores(e);
	}
}
