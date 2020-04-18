package br.furb.bingo.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import br.furb.bingo.app.Configuration;
import br.furb.bingo.db.AcoesDB;
import br.furb.bingo.db.AcoesDbImpl;
import br.furb.bingo.model.Cartela;
import br.furb.bingo.model.Usuario;

/**
 * Classe que implementa a interface {@link Operacoes}  
 * 
 * @author Luiz
 *
 */
public class OperacoesImpl implements Operacoes {

	private AcoesDB acoes;
	
	private Properties properties;
	
	private SorteioNumero sorteioNumero;
	
	public OperacoesImpl() {
		properties = Configuration.getInstance().getProperties();
		acoes = new AcoesDbImpl();
		
		Integer max = Integer.parseInt(properties.getProperty("sorteio.numeroMaximo"));
		Integer min = Integer.parseInt(properties.getProperty("sorteio.numeroMinimo"));
		sorteioNumero = new SorteioNumero(max, min);
	}
	
	@Override
	public void gerarESalvarCartelas() {
		int quantidadeCartelas = Integer.parseInt(properties.getProperty("participantes"));
		List<Cartela> cartelas = new ArrayList<Cartela>(quantidadeCartelas);
		for (int i = 0; i < quantidadeCartelas; i++) {
			Set<Integer> numeros = acoes.obterValoresRandomicos();
			Cartela cartela = new Cartela("cartela" + Integer.toString(i+1));
			cartela.adicionarNumeros(numeros);
			cartelas.add(cartela);
		}
		acoes.salvarCartelas(cartelas);
	}

	@Override
	public List<Usuario> sortearPedraEVerificarGanhadores() {
		Integer numeroSorteado = sorteioNumero.sortear();
		if (numeroSorteado == null) {
			return null;
		}
		return null;
	}
	

}
