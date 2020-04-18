package br.furb.bingo.ctrl;

import java.util.List;

import br.furb.bingo.model.Usuario;

/**
 * Interface com operações da aplicação 
 * 
 * @author Luiz
 *
 */
public interface Operacoes {
	
	/**
	 * Gerar e salvar cartelas no banco
	 */
	public void gerarESalvarCartelas();
	
	/**
	 * Tirar pedra
	 * 
	 * @return
	 */
	public List<Usuario> sortearPedraEVerificarGanhadores();

}
