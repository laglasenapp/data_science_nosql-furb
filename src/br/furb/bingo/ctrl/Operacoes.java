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
	 * Sortear uma pedra e incrementar o score quando necessário
	 * 
	 * @return
	 * @throws PossibilidadeSorteioEsgotadasException 
	 */
	public void sortearPedraEIncrementarScore() throws PossibilidadeSorteioEsgotadasException;
	
	/**
	 * Verifica e retorna a lista de vencedores
	 * 
	 * @return {@link List} de {@link Usuario}
	 */
	public List<Usuario> listaVencedores();

	/**
	 * Números sorteados 
	 * 
	 * @return {@link String}
	 */
	public String numerosSortados();

}
