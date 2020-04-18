package br.furb.bingo.db;

import java.util.List;
import java.util.Set;

import br.furb.bingo.model.Cartela;

public interface AcoesDB {
	
	public Set<Integer> obterValoresRandomicos();

	public void salvarCartelas(List<Cartela> cartelas);

}
