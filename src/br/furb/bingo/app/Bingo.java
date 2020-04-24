package br.furb.bingo.app;

import java.util.List;

import br.furb.bingo.ctrl.Operacoes;
import br.furb.bingo.ctrl.OperacoesImpl;
import br.furb.bingo.ctrl.PossibilidadeSorteioEsgotadasException;
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
		List<Usuario> vencedores = null;
		int rodada = 1;
		do {
			System.out.println("Rodada: " + rodada);
			try {
				operacoes.sortearPedraEIncrementarScore();
			} catch (PossibilidadeSorteioEsgotadasException e) {
				System.out.println(e.getLocalizedMessage());
				break;
			}
			vencedores = operacoes.listaVencedores();
			rodada++;
			System.out.println("");
			System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
			System.out.println("");
		} while (vencedores == null);
		
		if (vencedores != null) {
			System.out.println("Lista de vencedores:");
			vencedores.forEach(v -> System.out.println(v));
		} else {
			System.out.println("Não existem vencedores!");
		}
		System.out.println("");
		System.out.println("Números sorteados:");
		System.out.println(operacoes.numerosSortados());
	}

}
