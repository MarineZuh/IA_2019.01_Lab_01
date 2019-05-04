package implementacao.acoes;

import java.util.HashSet;
import java.util.Random;

import implementacao.EstadoImp;
import implementacao.modelo.Lado;
import implementacao.modelo.Pessoa;
import interfaces.Estado;

public class Atravessar extends AbstractAcao{

	public Atravessar() {
		super();
	}

	@Override
	public Estado resultado(Estado e) {
		EstadoImp estadoAtual = (EstadoImp)e;
		
		HashSet<Pessoa> pessoasNoInicio = estadoAtual.getPessoasNoInicio();
		if(pessoasNoInicio.size() == 0 ) {
			return estadoAtual;
		}
		
		HashSet<Pessoa> pessoasNoFim = 	estadoAtual.getPessoasNoFim();
		int tempoDercorrido = estadoAtual.getTempoDercorrido();
		Lado ladoLocalizacaoLanterna = estadoAtual.getLadoLocalizacaoLanterna();
		
		int numPessoasInicio = pessoasNoInicio.size();

		if(numPessoasInicio == 1) {
			Pessoa pessoa = this.getPessoa(pessoasNoInicio, 0);
			this.movePessoa(pessoasNoInicio, pessoasNoFim, pessoa);
			
			tempoDercorrido += pessoa.getTempoDeTravessia();
		} else if(numPessoasInicio >= 2){
			int indexAleatorio = new Random().nextInt(numPessoasInicio);
			
			Pessoa pessoa1 = this.getPessoa(pessoasNoInicio, indexAleatorio);
			this.movePessoa(pessoasNoInicio, pessoasNoFim, pessoa1);
			
			numPessoasInicio = pessoasNoInicio.size();
			indexAleatorio = new Random().nextInt(numPessoasInicio);
			
			Pessoa pessoa2 = this.getPessoa(pessoasNoInicio, indexAleatorio);
			this.movePessoa(pessoasNoInicio, pessoasNoFim, pessoa2);
			
			tempoDercorrido += (
				(pessoa1.getTempoDeTravessia() > pessoa2.getTempoDeTravessia()) ? 
						pessoa1.getTempoDeTravessia() : 
						pessoa2.getTempoDeTravessia()
			);
		}
		ladoLocalizacaoLanterna = Lado.FIM;			
		EstadoImp novoEstado = new EstadoImp(
			tempoDercorrido, 
			pessoasNoInicio, 
			pessoasNoFim, 
			ladoLocalizacaoLanterna
		);
		return super.resultado(novoEstado);
	}
	
	private void movePessoa(HashSet<Pessoa> inicio, HashSet<Pessoa> fim, Pessoa pessoa) {
		inicio.remove(pessoa);
		fim.add(pessoa);	
	}
	
	private Pessoa getPessoa(HashSet<Pessoa> set, int index) {
		return (Pessoa)set.toArray()[index];
	}	
}
