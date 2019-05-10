package implementacao;

import interfaces.FuncaoHeuristica;

public class FuncaoHeuristicaImp implements FuncaoHeuristica{

	@Override
	public double h(Object estado) {
		EstadoImp e = (EstadoImp)estado;
		return 15 + e.getTempoDercorrido();
	}

}
