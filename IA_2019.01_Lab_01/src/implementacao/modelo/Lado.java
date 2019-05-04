package implementacao.modelo;

public enum Lado {
	INICIO, FIM;

	@Override
	public String toString() {
		switch (this) {
		case INICIO:
			return "Inicio";
		case FIM:
			return "Fim";
		default:
			return null;
		}
	}

}
