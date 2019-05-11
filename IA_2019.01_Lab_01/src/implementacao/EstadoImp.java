package implementacao;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import implementacao.modelo.Lado;
import implementacao.modelo.Pessoa;
import interfaces.Estado;

public class EstadoImp implements Estado{
	
	private int tempoDercorrido; // em minutos
	private HashSet<Pessoa> pessoasNoInicio;
	private HashSet<Pessoa> pessoasNoFim;
	private Lado ladoLocalizacaoLanterna;
	
	public EstadoImp(int tempoDercorrido, HashSet<Pessoa> pessoasNoInicio, HashSet<Pessoa> pessoasNoFim,
			Lado ladoLocalizacaoLanterna) {
		this.tempoDercorrido = tempoDercorrido;
		this.pessoasNoInicio = pessoasNoInicio;
		this.pessoasNoFim = pessoasNoFim;
		this.ladoLocalizacaoLanterna = ladoLocalizacaoLanterna;
	}
	
	public int getTempoDercorrido() {
		return tempoDercorrido;
	}
	public void setTempoDercorrido(int tempoDercorrido) {
		this.tempoDercorrido = tempoDercorrido;
	}

	public HashSet<Pessoa> getPessoasNoInicio() {
		return (HashSet<Pessoa>) pessoasNoInicio.clone();
	}
	public void setPessoasNoInicio(HashSet<Pessoa> pessoasNoInicio) {
		this.pessoasNoInicio = pessoasNoInicio;
	}

	public HashSet<Pessoa> getPessoasNoFim() {
		return (HashSet<Pessoa>) pessoasNoFim.clone();
	}
	public void setPessoasNoFim(HashSet<Pessoa> pessoasNoFim) {
		this.pessoasNoFim = pessoasNoFim;
	}

	public Lado getLadoLocalizacaoLanterna() {
		return ladoLocalizacaoLanterna;
	}
	public void setLadoLocalizacaoLanterna(Lado ladoLocalizacaoLanterna) {
		this.ladoLocalizacaoLanterna = ladoLocalizacaoLanterna;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ladoLocalizacaoLanterna == null) ? 0 : ladoLocalizacaoLanterna.hashCode());
		result = prime * result + ((pessoasNoFim == null) ? 0 : pessoasNoFim.hashCode());
		result = prime * result + ((pessoasNoInicio == null) ? 0 : pessoasNoInicio.hashCode());
		result = prime * result + tempoDercorrido;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.igual((EstadoImp)obj);
	}

	@Override
	public boolean estadoValido() {
		boolean numeroDePessoasValido = (
			(this.pessoasNoFim.size() + this.pessoasNoInicio.size())  ==  4
		);
		boolean pessoasDiferentesNosLados = Collections.disjoint(this.pessoasNoFim, this.pessoasNoInicio);		
		boolean tempoValido = (this.tempoDercorrido <= 15); 
		
		return (
			numeroDePessoasValido &&
			pessoasDiferentesNosLados &&
			tempoValido
		);
	}

	@Override
	public boolean igual(Estado e) {
		EstadoImp estado = (EstadoImp)e;
		return (
			estado.ladoLocalizacaoLanterna == this.ladoLocalizacaoLanterna &&
			estado.pessoasNoFim.containsAll(this.pessoasNoFim) &&
			estado.pessoasNoInicio.containsAll(this.pessoasNoInicio) &&
			estado.tempoDercorrido == this.tempoDercorrido
		);
	}

	@Override
	public String toString() {
		return "[\n\ttempoDercorrido=" + tempoDercorrido + ", \n\tpessoasNoInicio=" + pessoasNoInicio.toString()
				+ ", \n\tpessoasNoFim=" + pessoasNoFim.toString() + ", \n\tladoLocalizacaoLanterna=" + ladoLocalizacaoLanterna.toString() + "\n]";
	}
	
	

}
