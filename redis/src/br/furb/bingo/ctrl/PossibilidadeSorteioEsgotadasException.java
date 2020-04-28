package br.furb.bingo.ctrl;

public class PossibilidadeSorteioEsgotadasException extends Exception {

	private static final long serialVersionUID = 1122109982147483636L;
	
	public PossibilidadeSorteioEsgotadasException(int quantidade) {
		super("Quantidade de possibilidades esgotadas. Quantidade alcançada: " + quantidade);
	}

}
