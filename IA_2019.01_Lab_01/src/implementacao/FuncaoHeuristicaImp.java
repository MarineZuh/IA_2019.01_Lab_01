package implementacao;

import interfaces.FuncaoHeuristica;

public class FuncaoHeuristicaImp implements FuncaoHeuristica{
	/**
	 * Numero de pessoas no inicio da ponte
	 */
	@Override
	public double h(Object estado) {
		EstadoImp e = (EstadoImp)estado;
		return e.getPessoasNoInicio().size();
	}

}
