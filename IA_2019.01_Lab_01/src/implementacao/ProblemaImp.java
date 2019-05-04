package implementacao;

import java.util.Map.Entry;
import java.util.Set;

import interfaces.Acao;
import interfaces.Estado;
import interfaces.Problema;

public class ProblemaImp implements Problema{

	@Override
	public Set<Acao> acoes(Estado arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double custo(Acao arg0, Estado arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Estado estadoInicial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estado resultado(Acao arg0, Estado arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<Acao, Estado>> sucessores(Estado arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean testaObjetivo(Estado arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
