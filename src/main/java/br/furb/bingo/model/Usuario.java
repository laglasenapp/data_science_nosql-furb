package br.furb.bingo.model;

/**
 * Classe representa um usuário
 * 
 * @author Luiz
 *
 */
public class Usuario {
	
	/** Nome do usuário */
	private String nome;
	
	/** {@link Cartela} do usuário*/
	private Cartela cartela;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cartela getCartela() {
		return cartela;
	}

	public void setCartela(Cartela cartela) {
		this.cartela = cartela;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartela == null) ? 0 : cartela.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cartela == null) {
			if (other.cartela != null)
				return false;
		} else if (!cartela.equals(other.cartela))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", cartela=" + cartela + "]";
	}
	
}
