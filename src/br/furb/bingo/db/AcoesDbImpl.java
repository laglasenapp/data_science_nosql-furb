package br.furb.bingo.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import br.furb.bingo.app.Configuration;
import br.furb.bingo.model.Cartela;
import redis.clients.jedis.Jedis;

public class AcoesDbImpl implements AcoesDB {

	private Jedis jedis;

	private Properties properties;

	private String memberIntervalKey;

	public AcoesDbImpl() {
		this.properties = Configuration.getInstance().getProperties();
		this.jedis = RedisConnection.getInstance().get();
		memberIntervalKey = properties.getProperty("memberKey");
		prepararDados();
	}

	private void prepararDados() {
		jedis.del(memberIntervalKey);
		int min = Integer.parseInt(properties.getProperty("sorteio.numeroMinimo"));
		System.out.println("Valor minímo: " + min);
		int max = Integer.parseInt(properties.getProperty("sorteio.numeroMaximo"));
		System.out.println("Valor máximo: " + max);
		for (int i = min; i < max; i++) {
			jedis.sadd(memberIntervalKey, String.valueOf(i));
		}
		System.out.println("Lista foi criada para a chave: " + memberIntervalKey);
	}

	@Override
	public Set<Integer> obterValoresRandomicos() {
		int count = Integer.parseInt(properties.getProperty("cartela.quantidadeMaxPermitida"));
		System.out.println("Quantidade máxima de números permitidos em uma cartela: " + count);
		Set<String> resultadoStr = randomR(memberIntervalKey, count);
		Set<Integer> resultadoInt = resultadoStr.stream().map(Integer::parseInt).collect(Collectors.toSet());
		return resultadoInt;
	}

	private Set<String> randomR(String key, int count) {
		List<String> resultado = jedis.srandmember(key, count);
		Set<String> resultadoSet = new HashSet<String>(resultado);
		if (resultadoSet.size() != count) {
			return randomR(key, count);
		}
		return resultadoSet;
	}

	@Override
	public void salvarCartelas(List<Cartela> cartelas) {
		for (Cartela cartela : cartelas) {
			Set<Integer> numeros = cartela.getNumeros();
			String listaNumeros = converterNumeros(numeros);
			jedis.hset(cartela.getNome(), "numeros", listaNumeros);
		}
	}

	private String converterNumeros(Set<Integer> numeros) {
		StringBuilder sb = new StringBuilder();
		List<Integer> lista = new ArrayList<>(numeros);
		for (int i = 0; i < lista.size(); i++) {
			sb.append(lista.get(i));
			if (i != (numeros.size() - 1)){
				sb.append(",");
			}
		}
		return sb.toString();
	}

}
