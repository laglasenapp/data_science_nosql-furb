package br.furb.bingo.model;

import java.util.HashSet;
import java.util.Set;

import br.furb.bingo.excecoes.NumeroMaximoAtingidoException;

/**
 * Classe representa uma cartela
 * 
 * @author Luiz
 *
 */
public class Cartela {
	
	/** Números na cartela */
	private Set<Integer> numeros = new HashSet<>();
	
	/** Quantidade restritiva de números em uma cartela */
	private int numeroMaximo;
	
	/**
	 * Método construtor
	 * 
	 * @param numeroMaximo
	 */
	public Cartela(short numeroMaximo) {
		this.numeroMaximo = numeroMaximo;
	}

	/**
	 * Adiciona o número a uma cartela
	 * 
	 * @param numero
	 * @return
	 * @throws NumeroMaximoAtingidoException 
	 */
	public boolean adiciona(Integer numero) throws NumeroMaximoAtingidoException {
		if (!numeros.contains(numero)) {
			numeros.add(numero);
			int quantidadeAtual = numeros.size();
			if (numeros.size() > numeroMaximo) {
				throw new NumeroMaximoAtingidoException(quantidadeAtual, numeroMaximo);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Obtem os números da cartela
	 * 
	 * @return Set<Integer>
	 */
	public Set<Integer> getNumeros(){
		return numeros;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeros == null) ? 0 : numeros.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartela other = (Cartela) obj;
		if (numeros == null) {
			if (other.numeros != null)
				return false;
		} else if (!numeros.equals(other.numeros))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cartela [numeros=" + numeros + "]";
	}
}
