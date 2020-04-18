package br.furb.bingo.excecoes;

/**
 * Exceção a ser lançada em caso de uma cartela possuir mais números que o
 * permitido
 * 
 * @author Luiz
 *
 */
public class NumeroMaximoAtingidoException extends Exception {

	private static final long serialVersionUID = 7294292546385039240L;

	/** Número máximo permitido */
	private int numeroMaximo;

	/** Quantidade atual */
	private int quantidadeAtual;
	
	/** Mensagem */
	private final static String MENSAGEM = "O número de elementos na lista é de %d e a quantidade máxima é de %d";

	/**
	 * Método construtor
	 * 
	 * @param quantidadeAtual
	 * @param numeroMaximo
	 */
	public NumeroMaximoAtingidoException(int quantidadeAtual, int numeroMaximo) {
		this.quantidadeAtual = quantidadeAtual;
		this.numeroMaximo = numeroMaximo;
	}
	
	@Override
	public String getMessage() {
		return String.format(MENSAGEM, quantidadeAtual, numeroMaximo);
	}
	
	@Override
	public String toString() {
		return getMessage();
	}

}
