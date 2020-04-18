package br.furb.bingo.app;

import java.util.List;

import br.furb.bingo.ctrl.Operacoes;
import br.furb.bingo.ctrl.OperacoesImpl;
import br.furb.bingo.model.Usuario;

/**
 * Classe principal
 * 
 * @author Luiz
 *
 */
public class Bingo {
	
	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Operacoes operacoes = new OperacoesImpl();
		operacoes.gerarESalvarCartelas();
		List<Usuario> vencedores = operacoes.sortearPedraEVerificarGanhadores();
		if (vencedores != null) {
			System.out.println("Lista de vencedores:");
			vencedores.forEach(v -> System.out.println(v));
		} else {
			System.out.println("Não existem vencedores!");
		}
	}

}
