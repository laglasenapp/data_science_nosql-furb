package br.furb.bingo.ctrl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Classe responsável por sortear um número único
 * 
 * @author Luiz
 *
 */
public final class SorteioNumero {

	private int max;
	
	private int min;
	
	private Set<Integer> numerosSorteados = new HashSet<Integer>();

	public SorteioNumero(int max, int min) {
		this.max = max;
		this.min = min;
	}

	public synchronized Integer sortear() {
		if (numerosSorteados.size() == max) {
			System.out.println("Todos os números possíveis já foram sorteados! Valor máximo: " + max);
			return null;
		}
		Integer numero = new Random().nextInt(max);
		if (numero < min || numerosSorteados.contains(numero)) {
			return sortear();
		}
		numerosSorteados.add(numero);
		return numero;
	}
	
	
}
