package br.furb.bingo.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import br.furb.bingo.app.Configuration;
import br.furb.bingo.model.Cartela;
import br.furb.bingo.model.Usuario;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * Classe que implementa a interface {@link AcoesDB}
 * 
 * @author Luiz
 *
 */
public class AcoesDbImpl implements AcoesDB {

	private Jedis jedis;

	private Properties properties;

	private String memberIntervalKey;

	/**
	 * Método construtor
	 */
	public AcoesDbImpl() {
		this.properties = Configuration.getInstance().getProperties();
		this.jedis = RedisConnection.getInstance().get();
		memberIntervalKey = properties.getProperty("memberKey");
		prepararDados();
	}

	/**
	 * Prepara os dados a serem utilizados na função do banco
	 */
	private void prepararDados() {
		jedis.flushAll();
		int min = Integer.parseInt(properties.getProperty("sorteio.numeroMinimo"));
		System.out.println("Valor minímo: " + min);
		int max = Integer.parseInt(properties.getProperty("sorteio.numeroMaximo"));
		System.out.println("Valor máximo: " + max);
		for (int i = min; i < max; i++) {
			jedis.sadd(memberIntervalKey, String.valueOf(i));
		}
	}

	@Override
	public Set<Integer> obterValoresRandomicos() {
		int count = Integer.parseInt(properties.getProperty("cartela.quantidadeMaxPermitida"));
		Set<String> resultadoStr = randomR(memberIntervalKey, count);
		Set<Integer> resultadoInt = resultadoStr.stream().map(Integer::parseInt).collect(Collectors.toSet());
		return resultadoInt;
	}

	/**
	 * Método recursivo para obter um set do tipo {@link String}
	 * 
	 * @param key
	 * @param count
	 * @return
	 */
	private Set<String> randomR(String key, int count) {
		List<String> resultado = jedis.srandmember(key, count);
		Set<String> resultadoSet = new HashSet<String>(resultado);
		if (resultadoSet.size() != count) {
			return randomR(key, count);
		}
		return resultadoSet;
	}

	@Override
	public void salvarUsuarios(List<Usuario> usuarios) {
		System.out.println("Salvando os valores da cartela e do usuário");
		for (Usuario usuario : usuarios) {
			Cartela cartela = usuario.getCartela();
			Set<Integer> numeros = cartela.getNumeros();

			String keyCartela = cartela.getNome();
			String[] numerosStr = converter2String(numeros);
			jedis.rpush(keyCartela, numerosStr);
			
			String keyUsuario = usuario.getNome();
			String nomeUsuario = usuario.getNome();
			jedis.hset(keyUsuario, "name", nomeUsuario);
			jedis.hset(keyUsuario, "cartela", cartela.getNome());
			
			jedis.hset(keyUsuario, "bscore", "bscore-"+usuario.getId());
			
			jedis.zadd("bscore", 0d, Integer.toString(usuario.getId()));
		}
	}

	/**
	 * Converte um set de {@link Integer} para {@link String}
	 * 
	 * @param numeros
	 * @return
	 */
	private String[] converter2String(Set<Integer> numeros) {
		String[] vetor = new String[numeros.size()];
		int index = 0;
		for (Integer numero : numeros) {
			vetor[index] = new String(Integer.toString(numero));
			index++;
		}
		return vetor;
	}

	@Override
	public void incrementarScore(List<Usuario> listaPessoaComNumeroNaCartela) {
		for (Usuario usuario : listaPessoaComNumeroNaCartela) {
			Double novoScore = jedis.zincrby("bscore", 1d, Integer.toString(usuario.getId()));
			System.out.println("Aumentando o score do usuário " + usuario.getNome() + " novo score: " + novoScore);
		}
	}

	@Override
	public List<Integer> obterListaVencedores(int scoreMaximo) {
		List<Integer> vencedores = new ArrayList<Integer>();
		
		Set<Tuple> result = jedis.zrangeByScoreWithScores("bscore", scoreMaximo, scoreMaximo);
		Iterator<Tuple> iterator = result.iterator();
		while (iterator.hasNext()) {
			Tuple tuple = iterator.next();
			if (tuple.getScore() == scoreMaximo) {
				vencedores.add(Integer.parseInt(tuple.getElement()));
			} else {
				break;
			}
		}
		return vencedores;
	}


}
