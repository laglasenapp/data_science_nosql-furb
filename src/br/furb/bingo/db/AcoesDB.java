package br.furb.bingo.db;

import java.util.List;
import java.util.Set;

import br.furb.bingo.model.Usuario;

/**
 * M�todos a serem executados usando um banco de dados
 * 
 * @author Luiz
 *
 */
public interface AcoesDB {
	
	/**
	 * Obter os valores rand�micos usando uma fun��o do banco de dados
	 * 
	 * @return
	 */
	public Set<Integer> obterValoresRandomicos();

	/**
	 * Salvar os usu�rios no banco de dados
	 * 
	 * @param usuarios
	 */
	public void salvarUsuarios(List<Usuario> usuarios);

	/**
	 * Incrementa o score do usu�rio
	 * 
	 * @param listaPessoaComNumeroNaCartela
	 */
	public void incrementarScore(List<Usuario> listaPessoaComNumeroNaCartela);

	/**
	 * Obter lista de vencedores baseado no score m�ximo
	 * 
	 * @return
	 */
	public List<Integer> obterListaVencedores(int scoreMaximo);

}
