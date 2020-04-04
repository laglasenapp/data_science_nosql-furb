package br.furb.bingo.app;

import br.furb.bingo.db.AcoesDB;
import br.furb.bingo.db.AcoesDbImpl;

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
		AcoesDB acoes = new AcoesDbImpl();
		acoes.obterValoresRandomicos();
		
	}

}
