package br.furb.bingo.db;

import java.util.List;
import java.util.Set;

import br.furb.bingo.model.Usuario;

/**
 * Métodos a serem executados usando um banco de dados
 * 
 * @author Luiz
 *
 */
public interface AcoesDB {
	
	/**
	 * Obter os valores randômicos usando uma função do banco de dados
	 * 
	 * @return
	 */
	public Set<Integer> obterValoresRandomicos();

	/**
	 * Salvar os usuários no banco de dados
	 * 
	 * @param usuarios
	 */
	public void salvarUsuarios(List<Usuario> usuarios);

	/**
	 * Incrementa o score do usuário
	 * 
	 * @param listaPessoaComNumeroNaCartela
	 */
	public void incrementarScore(List<Usuario> listaPessoaComNumeroNaCartela);

	/**
	 * Obter lista de vencedores baseado no score máximo
	 * 
	 * @return
	 */
	public List<Integer> obterListaVencedores(int scoreMaximo);

}
