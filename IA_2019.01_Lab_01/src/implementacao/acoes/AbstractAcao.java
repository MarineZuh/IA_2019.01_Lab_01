package implementacao.acoes;

import interfaces.Acao;
import interfaces.Estado;

public class AbstractAcao implements Acao{
	
	private String id = "NoOp";
    
    public AbstractAcao(){
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public boolean equals(AbstractAcao a) {
		return this.id.equalsIgnoreCase(a.getId());
	}
    
    @Override
    public String toString(){
        return id;
    }

	@Override
	public double custo(Estado arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean preCondicoesSatisfeitas(Estado arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Estado resultado(Estado arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
