package br.furb.bingo.model;

import java.util.Set;

	
/**
 * Classe representa uma cartela
 * 
 * @author Luiz
 *
 */
public class Cartela {
	
	/** Prefixo do nome da cartela */
	private static final String PREFIXO_CARTELA = "cartela";
	
	/** Números na cartela */
	private Set<Integer> numeros;
	
	/** Nome */
	private String nome;
	
	/**
	 * Método construtor
	 * 
	 * @param numeroMaximo
	 */
	public Cartela(Integer id) {
		this.nome = PREFIXO_CARTELA + id;
	}

	/**
	 * Adiciona o número a uma cartela
	 * 
	 * @param numero
	 * @return
	 */
	public boolean adiciona(Integer numero)  {
		if (!numeros.contains(numero)) {
			numeros.add(numero);
			return true;
		}
		return false;
	}
	
	public void adicionarNumeros(Set<Integer> numeros) {
		this.numeros = numeros;
	}
	
	/**
	 * Obtem os números da cartela
	 * 
	 * @return Set<Integer>
	 */
	public Set<Integer> getNumeros(){
		return numeros;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeros == null) {
			if (other.numeros != null)
				return false;
		} else if (!numeros.equals(other.numeros))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Numeros=" + numeros;
	}
	
}