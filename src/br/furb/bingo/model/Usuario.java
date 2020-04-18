package br.furb.bingo.model;

/**
 * Classe representa um usuário
 * 
 * @author Luiz
 *
 */
public class Usuario {
	
	/** Prefixo do nome da pessoa */
	private static final String PREFIXO_PESSOA = "user:";
	
	/** Nome do usuário */
	private String nome;
	
	/** {@link Cartela} do usuário*/
	private Cartela cartela;
	
	/** Id */
	private Integer id;

	public Usuario(Integer id, Cartela cartela) {
		this.id = id;
		this.cartela = cartela;
		this.nome = PREFIXO_PESSOA + id;
	}
	
	public String getNome() {
		return nome;
	}


	public Cartela getCartela() {
		return cartela;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartela == null) ? 0 : cartela.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		StringBuilder sb = new StringBuilder();
		sb.append("Nome= " + nome);
		sb.append(" Cartela [" + cartela + "]");
		return sb.toString();
	}
}
