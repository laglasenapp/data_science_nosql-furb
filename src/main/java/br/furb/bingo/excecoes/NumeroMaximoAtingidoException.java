package br.furb.bingo.excecoes;

/**
 * Exce��o a ser lan�ada em caso de uma cartela possuir mais n�meros que o
 * permitido
 * 
 * @author Luiz
 *
 */
public class NumeroMaximoAtingidoException extends Exception {

	private static final long serialVersionUID = 7294292546385039240L;

	/** N�mero m�ximo permitido */
	private int numeroMaximo;

	/** Quantidade atual */
	private int quantidadeAtual;
	
	/** Mensagem */
	private final static String MENSAGEM = "O n�mero de elementos na lista � de %d e a quantidade m�xima � de %d";

	/**
	 * M�todo construtor
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
